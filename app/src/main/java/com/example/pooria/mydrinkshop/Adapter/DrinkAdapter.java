package com.example.pooria.mydrinkshop.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.pooria.mydrinkshop.Database.ModelDB.Cart;
import com.example.pooria.mydrinkshop.Interface.IItemClickListener;
import com.example.pooria.mydrinkshop.Model.Drink;
import com.example.pooria.mydrinkshop.R;
import com.example.pooria.mydrinkshop.Utils.Common;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import java.util.List;

public class DrinkAdapter extends RecyclerView.Adapter<DrinkViewHolder> {

    Context context;
    List<Drink>drinkList;
    @NonNull
    @Override
    public DrinkViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.drink_item_layout, null);
        return new DrinkViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull DrinkViewHolder holder, final int i) {


        holder.txt_price.setText(new StringBuilder("$").append(drinkList.get(i).Price).toString());
        holder.txt_drink_name.setText(drinkList.get(i).Name);
        Picasso.with(context)
                .load(drinkList.get(i).Link)
                .into(holder.img_product);

        holder.btn_add_to_Cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddToCartDialog(i);
            }
        });

        holder.setItemClickListener(new IItemClickListener() {
            @Override
            public void OnClick(View v) {
                Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showAddToCartDialog(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context)
                .inflate(R.layout.add_to_cart_layout, null);
        ImageView img_product_dialog = view.findViewById(R.id.img_cart_product);
        final ElegantNumberButton txt_count = view.findViewById(R.id.txt_count);
        final TextView txt_product_dialog = view.findViewById(R.id.txt_cart_product_name);
        EditText edt_comment = view.findViewById(R.id.edt_comment);
        RadioButton rdi_sizeM = view.findViewById(R.id.rdi_sizeM);
        RadioButton rdi_sizeL = view.findViewById(R.id.rdi_sizeL);


        rdi_sizeM.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Common.sizeOfCup=0;
                }
            }
        });


        rdi_sizeL.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Common.sizeOfCup=1;
                }
            }
        });


        RadioButton rdi_sugar_100 = view.findViewById(R.id.rdi_sugar_100);
        RadioButton rdi_sugar_70 = view.findViewById(R.id.rdi_sugar_70);
        RadioButton rdi_sugar_50 = view.findViewById(R.id.rdi_sugar_50);
        RadioButton rdi_sugar_30 = view.findViewById(R.id.rdi_sugar_30);
        RadioButton rdi_sugar_free = view.findViewById(R.id.rdi_sugar_free);


        rdi_sugar_30.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Common.sugar=30;
                }
            }
        });

        rdi_sugar_50.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Common.sugar=50;
                }
            }
        });


        rdi_sugar_70.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Common.sugar=70;
                }
            }
        });


        rdi_sugar_100.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Common.sugar=100;
                }
            }
        });

        rdi_sugar_free.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Common.sugar=0;
                }
            }
        });



        RadioButton rdi_ice_100 = view.findViewById(R.id.rdi_ice_100);
        RadioButton rdi_ice_70 = view.findViewById(R.id.rdi_ice_70);
        RadioButton rdi_ice_50 = view.findViewById(R.id.rdi_ice_50);
        RadioButton rdi_ice_30 = view.findViewById(R.id.rdi_ice_30);
        RadioButton rdi_ice_free = view.findViewById(R.id.rdi_ice_free);


        rdi_ice_30.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Common.ice = 30;
                }
            }
        });

        rdi_ice_50.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Common.ice = 50;
                }
            }
        });

        rdi_ice_70.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Common.ice =70;
                }
            }
        });

        rdi_ice_100.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Common.ice = 100;
                }
            }
        });

        rdi_ice_free.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Common.ice = 0;
                }
            }
        });


        RecyclerView recycler_topping = view.findViewById(R.id.recycler_topping);
        recycler_topping.setLayoutManager(new LinearLayoutManager(context));
        recycler_topping.setHasFixedSize(true);

        MultiChoiceAdapter adapter = new MultiChoiceAdapter(context, Common.toppingList);
        recycler_topping.setAdapter(adapter);

        //settingg date

        Picasso.with(context)
                .load(drinkList.get(position).Link).into(img_product_dialog);
        txt_product_dialog.setText(drinkList.get(position).Name);
        builder.setView(view);
        builder.setNegativeButton("Add To Cart", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


                if (Common.sizeOfCup == -1) {
                    Toast.makeText(context, "Plz Choose Size OF Your Cup", Toast.LENGTH_SHORT).show();
                }
                if (Common.sugar == -1) {
                    Toast.makeText(context, "Plz Choose How Much Sugar Do U Need", Toast.LENGTH_SHORT).show();
                }
                if (Common.ice == -1) {
                    Toast.makeText(context, "Plz Choose How Much Ice Do U Need", Toast.LENGTH_SHORT).show();
                }


                showConfirmDialog(position, txt_count.getNumber());
                dialog.dismiss();


            }
        });

        builder.show();
    }

    private void showConfirmDialog(int position, final String number) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context)
                .inflate(R.layout.confirm_add_to_cart_layout, null);

        ImageView img_product_dialog = view.findViewById(R.id.img_product);
        final TextView txt_product_dialog = view.findViewById(R.id.txt_cart_product_name);
        final TextView txt_product_price = view.findViewById(R.id.txt_cart_product_price);
        TextView txt_sugar = view.findViewById(R.id.txt_sugar);
        TextView txt_ice = view.findViewById(R.id.txt_ice);
        final TextView txt_topping_extra = view.findViewById(R.id.txt_topping_extra);

        Picasso.with(context).load(drinkList.get(position).Link).into(img_product_dialog);

        txt_product_dialog.setText(new StringBuilder(drinkList.get(position).Name).append(" x")
                .append(number)
                .append(Common.sizeOfCup == 0 ? "Size M" : "Size L ").toString());

        txt_ice.setText(new StringBuilder("Ice :").append(Common.ice).append("%").toString());
        txt_ice.setText(new StringBuilder("Sugar :").append(Common.sugar).append("%").toString());


        double price = Double.parseDouble(drinkList.get(position).Price)
                * Double.parseDouble(number) + Common.toppingPrice;

        if (Common.sizeOfCup == 1) {
            price += 3.0;
        }

        txt_product_price.setText(new StringBuilder("$").append(price));
        StringBuilder topping_final_comment = new StringBuilder("");
        for (String line : Common.toppingAdded)
            topping_final_comment.append(line).append("\n");
            txt_topping_extra.setText(topping_final_comment);

        final double finalPrice = price;
        builder.setNegativeButton("CONFIRM", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();

                    try {
                        //add to database Room
                        Cart cart = new Cart();
                        cart.name = txt_product_dialog.getText().toString();
                        cart.amount = Integer.parseInt(number);
                        cart.ice = Common.ice;
                        cart.sugar = Common.sugar;
                        cart.price = finalPrice;
                        cart.toppingExtras = txt_topping_extra.getText().toString();

                        Common.cartRepository.insertToCart(cart);
                        Log.d("log", new Gson().toJson(cart));
                        Toast.makeText(context, "Insert Compeleted", Toast.LENGTH_SHORT).show();
                    } catch (Exception ex) {
                        Log.d("log", ex.getMessage().toString());
                        Toast.makeText(context, ex.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        builder.setView(view);
        builder.show();
    }

    @Override
    public int getItemCount() {
        return drinkList.size();
    }

    public DrinkAdapter(Context context, List<Drink> drinkList) {
        this.context = context;
        this.drinkList = drinkList;
    }

}
