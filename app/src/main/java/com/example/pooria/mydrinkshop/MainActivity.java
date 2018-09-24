 package com.example.pooria.mydrinkshop;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.pooria.mydrinkshop.Model.User;
import com.example.pooria.mydrinkshop.Model.checkUserResponse;
import com.example.pooria.mydrinkshop.Retrofit.IDrinkShopAPI;
import com.example.pooria.mydrinkshop.Utils.Common;
import com.facebook.accountkit.Account;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.AccountKitCallback;
import com.facebook.accountkit.AccountKitError;
import com.facebook.accountkit.AccountKitLoginResult;
import com.facebook.accountkit.ui.AccountKitActivity;
import com.facebook.accountkit.ui.AccountKitConfiguration;
import com.facebook.accountkit.ui.LoginType;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.szagurskii.patternedtextwatcher.PatternedTextWatcher;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

 public class MainActivity extends AppCompatActivity {

     private static final int REQUEST_PERMISSION = 500;
     private IDrinkShopAPI mService;
     private static final int REQUEST_CODE = 3000;
     private Button btn_continue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_continue = findViewById(R.id.btn_continue);
        printKeyHash();


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE
            },REQUEST_PERMISSION);
        }

        mService = Common.getAPI();
        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startLoginPage(LoginType.PHONE);

            }
        });

        if (AccountKit.getCurrentAccessToken() != null) {
            AccountKit.getCurrentAccount(new AccountKitCallback<Account>() {
                @Override
                public void onSuccess(final Account account) {
                    mService.checkUserExist(account.getPhoneNumber().toString())
                            .enqueue(new Callback<checkUserResponse>() {
                                @Override
                                public void onResponse(Call<checkUserResponse> call, Response<checkUserResponse> response) {
                                    checkUserResponse body = response.body();
                                    if (body.isExists()) {
                                        mService.getUserInformation(account.getPhoneNumber().toString())
                                                .enqueue(new Callback<User>() {
                                                    @Override
                                                    public void onResponse(Call<User> call, Response<User> response) {
                                                        //alertDialog.dismiss();
                                                        Common.currentUser = response.body();
                                                        startActivity(new Intent(MainActivity.this, HomeActivity.class));
                                                        finish();
                                                    }

                                                    @Override
                                                    public void onFailure(Call<User> call, Throwable t) {
                                                        Toast.makeText(MainActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
                                                    }
                                                });

                                    }
                                    else {
                                        //alertDialog.dismiss();
                                        showRegisterDialog(account.getPhoneNumber().toString());

                                    }
                                }

                                @Override
                                public void onFailure(Call<checkUserResponse> call, Throwable t) {

                                }
                            });
                }

                @Override
                public void onError(AccountKitError accountKitError) {
                    Log.d("ERROR", accountKitError.getErrorType().getMessage().toString());
                }
            });

        }

    }

     private void startLoginPage(LoginType loginType) {
         Intent intent = new Intent(this, AccountKitActivity.class);
         AccountKitConfiguration.AccountKitConfigurationBuilder builder = new AccountKitConfiguration.AccountKitConfigurationBuilder(loginType, AccountKitActivity.ResponseType.TOKEN);
         intent.putExtra(AccountKitActivity.ACCOUNT_KIT_ACTIVITY_CONFIGURATION, builder.build());
         startActivityForResult(intent, REQUEST_CODE);
     }

     private void printKeyHash() {
         try {
             PackageInfo info = getPackageManager().getPackageInfo("com.example.pooria.mydrinkshop", PackageManager.GET_SIGNATURES);
             for (Signature signature : info.signatures) {
                 MessageDigest md = MessageDigest.getInstance("SHA") ;
                 md.update(signature.toByteArray());
                 Log.d("KEYHASH", Base64.encodeToString(md.digest(), Base64.DEFAULT));
             }
         } catch (PackageManager.NameNotFoundException e) {
             e.printStackTrace();

         } catch (NoSuchAlgorithmException e) {
             e.printStackTrace();
         }
    }


     @Override
     protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
         super.onActivityResult(requestCode, resultCode, data);
         if (requestCode == REQUEST_CODE) {
             AccountKitLoginResult result = data.getParcelableExtra(AccountKitLoginResult.RESULT_KEY);
             if (result.getError() != null) {
                 Toast.makeText(this, result.getError().toString(), Toast.LENGTH_SHORT).show();
             } else if (result.wasCancelled()) {
                 Toast.makeText(this, "Cancel", Toast.LENGTH_SHORT).show();
             }
             if (result.getAccessToken() != null) {
                 final SpotsDialog alertDialog = new SpotsDialog(MainActivity.this);
                 alertDialog.show();
                 alertDialog.setMessage("Please Waiting :)");

                 AccountKit.getCurrentAccount(new AccountKitCallback<Account>() {
                     @Override
                     public void onSuccess(final Account account) {
                         mService.checkUserExist(account.getPhoneNumber().toString())
                                 .enqueue(new Callback<checkUserResponse>() {
                                     @Override
                                     public void onResponse(Call<checkUserResponse> call, Response<checkUserResponse> response) {
                                         checkUserResponse body = response.body();
                                         if (body.isExists()) {
                                             mService.getUserInformation(account.getPhoneNumber().toString())
                                             .enqueue(new Callback<User>() {
                                                 @Override
                                                 public void onResponse(Call<User> call, Response<User> response) {
                                                     alertDialog.dismiss();
                                                     startActivity(new Intent(MainActivity.this, HomeActivity.class));
                                                     finish();
                                                 }

                                                 @Override
                                                 public void onFailure(Call<User> call, Throwable t) {
                                                     Toast.makeText(MainActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
                                                 }
                                             });

                                         }
                                         else {
                                             alertDialog.dismiss();
                                             showRegisterDialog(account.getPhoneNumber().toString());

                                         }
                                     }

                                     @Override
                                     public void onFailure(Call<checkUserResponse> call, Throwable t) {

                                     }
                                 });
                     }

                     @Override
                     public void onError(AccountKitError accountKitError) {
                         Log.d("ERROR", accountKitError.getErrorType().getMessage().toString());
                     }
                 });

             }
         }
     }

     private void showRegisterDialog(final String phone) {
         final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
         builder.setTitle("Register");
         AlertDialog dialog = null;
         LayoutInflater inflater = this.getLayoutInflater();
         View register_layout = inflater.inflate(R.layout.register_layout, null);
         final MaterialEditText edt_name = register_layout.findViewById(R.id.edt_name);
         final MaterialEditText edt_address= register_layout.findViewById(R.id.edt_address);
         final MaterialEditText edt_birthdate = register_layout.findViewById(R.id.edt_birthdate);
         Button btn_register = register_layout.findViewById(R.id.btn_register);
         edt_birthdate.addTextChangedListener(new PatternedTextWatcher("####-##-##"));
         builder.setView(register_layout);
         dialog = builder.create();

         final AlertDialog finalDialog = dialog;
         btn_register.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 finalDialog.dismiss();

                 if (TextUtils.isEmpty(edt_address.getText().toString())) {
                     Toast.makeText(MainActivity.this, "Plz Enter Your Name ", Toast.LENGTH_SHORT).show();
                     return;
                 }
                 if (TextUtils.isEmpty(edt_name.getText().toString())) {
                     Toast.makeText(MainActivity.this, "Plz Enter Your Name ", Toast.LENGTH_SHORT).show();
                     return;
                 }
                 if (TextUtils.isEmpty(edt_birthdate.getText().toString())) {
                     Toast.makeText(MainActivity.this, "Plz Enter Your BirthDate ", Toast.LENGTH_SHORT).show();
                     return;
                 }

                 final SpotsDialog waitingDialog = new SpotsDialog(MainActivity.this);
                 waitingDialog.show();
                 waitingDialog.setMessage("Please Waiting :)");
                 mService.registerNewUser(phone,
                         edt_name.getText().toString(),
                         edt_address.getText().toString(),
                         edt_birthdate.getText().toString())
                 .enqueue(new Callback<User>() {
                     @Override
                     public void onResponse(Call<User> call, Response<User> response) {
                         waitingDialog.dismiss();
                         User user = response.body();
                         if (TextUtils.isEmpty(user.getError_msg())) {
                             Toast.makeText(MainActivity.this, "User Register successsfully", Toast.LENGTH_SHORT).show();
                             Common.currentUser = response.body();
                             startActivity(new Intent(MainActivity.this,HomeActivity.class));
                             finish();
                         }
                     }

                     @Override
                     public void onFailure(Call<User> call, Throwable t) {
                         waitingDialog.dismiss();
                     }
                 });
             }
         });
         dialog.show();
     }


     @Override
     public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
         super.onRequestPermissionsResult(requestCode, permissions, grantResults);
         switch (requestCode) {
             case REQUEST_PERMISSION:
                 if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                     Toast.makeText(this, "Permisssoin Grandted", Toast.LENGTH_SHORT).show();
                 }else{
                     Toast.makeText(this, "Permission Denied !", Toast.LENGTH_SHORT).show();
                 }
                 break;
                 default:
                     break;
         }
     }
 }
