package com.example.pooria.mydrinkshop.Database.DataSource;

import com.example.pooria.mydrinkshop.Database.ModelDB.Cart;

import java.util.List;

import io.reactivex.Flowable;

public interface ICartDataSource {

    Flowable<List<Cart>> getCartItems();
    Flowable<List<Cart>>getCartItemById(int cartItemid);
    int countCartItems();
    void emptyCart();
    void insertToCart(Cart... carts);
    void updateCart(Cart... carts);
    void deleteCartItem(Cart cart);

}
