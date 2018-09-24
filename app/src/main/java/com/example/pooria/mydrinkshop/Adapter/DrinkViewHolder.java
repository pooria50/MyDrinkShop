package com.example.pooria.mydrinkshop.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pooria.mydrinkshop.Interface.IItemClickListener;
import com.example.pooria.mydrinkshop.R;

public class DrinkViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


    public void setItemClickListener(IItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
    IItemClickListener itemClickListener;
    ImageView img_product;
    TextView txt_price, txt_drink_name;
    Button btn_add_to_Cart;
    public DrinkViewHolder(@NonNull View itemView) {
        super(itemView);
        img_product = itemView.findViewById(R.id.img_product);
        txt_price = itemView.findViewById(R.id.txt_price);
        txt_drink_name = itemView.findViewById(R.id.txt_drink_name);
        btn_add_to_Cart = itemView.findViewById(R.id.btn_add_cart);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        itemClickListener.OnClick(v);
    }
}
