package com.example.pooria.mydrinkshop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.example.pooria.mydrinkshop.Adapter.DrinkAdapter;
import com.example.pooria.mydrinkshop.Model.Drink;
import com.example.pooria.mydrinkshop.Retrofit.IDrinkShopAPI;
import com.example.pooria.mydrinkshop.Utils.Common;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class DrinkActivity extends AppCompatActivity {
    IDrinkShopAPI mService;
    RecyclerView recycler_drink;
    TextView txt_banner_name;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);
        mService = Common.getAPI();
        recycler_drink = findViewById(R.id.rc_drinks);
        recycler_drink.setLayoutManager(new GridLayoutManager(this,2));
        recycler_drink.setHasFixedSize(true);
        txt_banner_name = findViewById(R.id.txt_menu_name);
        loadListDrink(Common.currentCategory.ID);
    }

    private void loadListDrink(String menuId) {
        compositeDisposable.add(mService.getDrink(menuId)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<List<Drink>>() {
            @Override
            public void accept(List<Drink> drinks) throws Exception {
                Log.d("tag", drinks.toString());
                displayDrinkList(drinks);
            }
        }));
    }

    private void displayDrinkList(List<Drink> drinks) {
        DrinkAdapter drinkAdapter = new DrinkAdapter(this, drinks);
        recycler_drink.setAdapter(drinkAdapter);
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.dispose();
        super.onDestroy();

    }
}
