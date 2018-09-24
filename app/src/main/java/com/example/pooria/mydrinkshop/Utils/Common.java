package com.example.pooria.mydrinkshop.Utils;

import com.example.pooria.mydrinkshop.Database.DataSource.CartRepository;
import com.example.pooria.mydrinkshop.Database.Local.CartDatabase;
import com.example.pooria.mydrinkshop.Model.Category;
import com.example.pooria.mydrinkshop.Model.Drink;
import com.example.pooria.mydrinkshop.Model.User;
import com.example.pooria.mydrinkshop.Retrofit.IDrinkShopAPI;
import com.example.pooria.mydrinkshop.Retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

public class Common {
    public static final String BASE_URL = "http://192.168.1.10/MyDrinkShop/";

    public static final String TOPPING_MENU_ID="5";

    public static User currentUser = null;
    public static Category currentCategory = null;


    public static List<Drink> toppingList = new ArrayList<>();


    public static double toppingPrice= 0.0;
    public static List<String> toppingAdded = new ArrayList<>();


    public static int sizeOfCup = -1;
    public static int sugar = -1;
    public static int ice = -1;



    public static IDrinkShopAPI getAPI(){

        return RetrofitClient.getClient(BASE_URL).create(IDrinkShopAPI.class);
    }


    //Database

    public static CartDatabase cartDatabase;
    public static CartRepository cartRepository;
}
