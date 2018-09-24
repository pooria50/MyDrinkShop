package com.example.pooria.mydrinkshop.Retrofit;

import com.example.pooria.mydrinkshop.Model.Banner;
import com.example.pooria.mydrinkshop.Model.Category;
import com.example.pooria.mydrinkshop.Model.Drink;
import com.example.pooria.mydrinkshop.Model.User;
import com.example.pooria.mydrinkshop.Model.checkUserResponse;

import java.util.List;
import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface IDrinkShopAPI {

    @FormUrlEncoded
    @POST("checkuser.php")
    Call<checkUserResponse> checkUserExist(@Field("phone") String phone);


    @FormUrlEncoded
    @POST("register.php")
    Call<User>registerNewUser(@Field("phone") String phone,
                              @Field("name") String name,
                              @Field("address") String address,
                              @Field("birthdate") String birthdate);
    @FormUrlEncoded
    @POST("getuser.php")
    Call<User>getUserInformation(@Field("phone") String phone);

    @FormUrlEncoded
    @POST("getdrink.php")
    Observable<List<Drink>>getDrink(@Field("menuid") String menuID);

    @GET("getbanner.php")
    Observable<List<Banner>> getBanners();


    @GET("getmenu.php")
    Observable<List<Category>> getMenu();

    @Multipart
    @POST("upload.php")
    Call<String>uploadFile(@Part("phone")String phone, @Part MultipartBody.Part file);


}
