package com.teksystems.machine.intr.PaymentHelper_class;

import com.teksystems.machine.impl.Coin.Dimes;
import com.teksystems.machine.impl.Coin.Nickels;
import com.teksystems.machine.impl.Coin.Quarters;
import com.teksystems.machine.impl.PaymentHelperImpl;
import com.teksystems.machine.impl.ProductStockImpl;
import com.teksystems.machine.impl.product.Candy;
import com.teksystems.machine.impl.product.Chips;
import com.teksystems.machine.impl.product.Cola;
import com.teksystems.machine.intr.Coin;
import com.teksystems.machine.intr.PaymentHelper;
import com.teksystems.machine.intr.Product;
import com.teksystems.machine.intr.ProductStock;
import com.teksystems.machine.util.CartService;

import java.util.ArrayList;
import java.util.List;

public abstract class Helper {
    public CartService getCartservice(int totalCandy, int totalCola, int totalChips){
        CartService cartService=new CartService();
        addProduct(cartService,totalCandy,new Candy());
        addProduct(cartService,totalCola,new Cola());
        addProduct(cartService,totalChips,new Chips());
        return cartService;
    }

    public PaymentHelper getPaymentHelper(ProductStock productStock, int totalDimes, int totalNickels, int totalQuarters){
        PaymentHelper paymentHelper=new PaymentHelperImpl(productStock);
        List<Coin> coins=new ArrayList<>();
        addCoins(coins,totalDimes,new Dimes());
        addCoins(coins,totalNickels,new Nickels());
        addCoins(coins,totalQuarters,new Quarters());
        paymentHelper.totalPaymentReceived(coins);
        return paymentHelper;
    }

    public ProductStock getProductStock(int totalChips,int totalCola,int totalCandy){
        ProductStock productStock= ProductStockImpl.getInstance();
        productStock.addProduct(new Chips(),totalChips);
        productStock.addProduct(new Cola(),totalCola);
        productStock.addProduct(new Candy(),totalCandy);
        return productStock;
    }

    private void addCoins(List<Coin> coins, int totalCoins,Coin coin) {
        for(int i=0;i<totalCoins;i++){
            coins.add(coin);
        }
    }

    private void addProduct(CartService cartService, int count, Product product){
        for(int i=0;i<count;i++){
            cartService.addToCart(product);
        }
    }

    public double totalAmount(int dimes,int nickles,int quarters){
        return dimes*0.1+nickles*0.05+quarters*0.25;
    }

}
