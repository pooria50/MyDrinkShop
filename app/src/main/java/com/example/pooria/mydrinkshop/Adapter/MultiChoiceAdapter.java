package com.example.pooria.mydrinkshop.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.example.pooria.mydrinkshop.Model.Drink;
import com.example.pooria.mydrinkshop.R;
import com.example.pooria.mydrinkshop.Utils.Common;

import java.util.List;

public class MultiChoiceAdapter extends RecyclerView.Adapter<MultiChoiceAdapter.MultiChoiceViewHolder> {

    Context context;
    List<Drink>optionList;

    public MultiChoiceAdapter(Context context, List<Drink> optionList) {
        this.context = context;
        this.optionList = optionList;
    }

    @NonNull
    @Override
    public MultiChoiceViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.multi_check_layout, null);
        return new MultiChoiceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MultiChoiceViewHolder holder, final int position) {
        holder.checkBox.setText(optionList.get(position).Name);
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Common.toppingAdded.add(buttonView.getText().toString());
                    Common.toppingPrice += Double.parseDouble(optionList.get(position).Price);
                }
                else {
                    Common.toppingAdded.remove(buttonView.getText().toString());
                    Common.toppingPrice -= Double.parseDouble(optionList.get(position).Price);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return optionList.size();
    }

    class MultiChoiceViewHolder extends RecyclerView.ViewHolder {

        CheckBox checkBox;
        public MultiChoiceViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.ckb_topping);

        }
    }
}
