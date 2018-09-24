package com.example.pooria.mydrinkshop.Database.Local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.pooria.mydrinkshop.Database.ModelDB.Cart;

@Database(entities = {Cart.class},version = 1)
public abstract class CartDatabase extends RoomDatabase {
    public abstract CartDAO cartDAO();

    private static CartDatabase instance;

    public static CartDatabase getInstance(Context context) {
        if (instance == null)
            instance = Room.databaseBuilder(context, CartDatabase.class, "DrinkShop")
                    .allowMainThreadQueries()
                    .build();
            return instance;

    }
}
