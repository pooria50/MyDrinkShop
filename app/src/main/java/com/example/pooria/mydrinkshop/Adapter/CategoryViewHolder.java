package com.example.pooria.mydrinkshop.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pooria.mydrinkshop.Interface.IItemClickListener;
import com.example.pooria.mydrinkshop.R;

import org.w3c.dom.Text;

public class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public void setItemClickListener(IItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    IItemClickListener itemClickListener;
    ImageView img_product;
    TextView txt_menu_name;

    public CategoryViewHolder(@NonNull View itemView) {
        super(itemView);
        img_product = itemView.findViewById(R.id.img_product);
        txt_menu_name = itemView.findViewById(R.id.txt_menu_name);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        itemClickListener.OnClick(v);
    }
}
