package com.example.pooria.mydrinkshop.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pooria.mydrinkshop.DrinkActivity;
import com.example.pooria.mydrinkshop.Interface.IItemClickListener;
import com.example.pooria.mydrinkshop.Model.Category;
import com.example.pooria.mydrinkshop.R;
import com.example.pooria.mydrinkshop.Utils.Common;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryViewHolder> {

    List<Category>categories;
    Context context;
    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.menu_item_layout, null);
        return new CategoryViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, final int position) {
        Picasso.with(context)
                .load(categories.get(position).Link)
                .into(holder.img_product);
        holder.txt_menu_name.setText(categories.get(position).Name);
        holder.setItemClickListener(new IItemClickListener() {
            @Override
            public void OnClick(View v) {
                Common.currentCategory = categories.get(position);
                context.startActivity(new Intent(context, DrinkActivity.class));

            }
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }


    public CategoryAdapter(Context context,List<Category> categories ) {
        this.categories = categories;
        this.context = context;
    }
}
