package com.teksystems.machine.impl;

import com.teksystems.machine.util.CartService;
import com.teksystems.machine.impl.Coin.Dimes;
import com.teksystems.machine.impl.Coin.Nickels;
import com.teksystems.machine.impl.Coin.Quarters;
import com.teksystems.machine.intr.Coin;
import com.teksystems.machine.intr.PaymentHelper;
import com.teksystems.machine.intr.Product;
import com.teksystems.machine.intr.ProductStock;

import java.util.*;

public class PaymentHelperImpl implements PaymentHelper {

    ProductStock productStockService;
    Set<Coin> validCoins;
    double amountReceived;
    public PaymentHelperImpl(ProductStock productStockService){
        this.productStockService=productStockService;
        validCoins=new LinkedHashSet(Arrays.asList(new Coin[]{new Quarters(),new Dimes(),new Nickels()}));
        amountReceived=0;
    }
    @Override
    public Double totalAmountRequired(CartService cartBO) {
        Map<Product,Integer> productMap= cartBO.getMapProducts();
        double totalAmount=0;
        for(Map.Entry<Product,Integer> entry:productMap.entrySet()){
            totalAmount+=entry.getKey().price*entry.getValue();
        }
        return totalAmount;
    }

    @Override
    public Double totalPaymentReceived(List<Coin> coins) {
        if(coins==null)throw new IllegalArgumentException();
        for(Coin coin:coins){
            if(!isCoinValid(coin)){
                System.err.println("Invalid coin found "+coin);
                continue;
            }
            amountReceived+=coin.amount;
        }
        return amountReceived;
    }

    private boolean isCoinValid(Coin coin) {
        if(coin==null)throw new IllegalArgumentException();
        if(validCoins.contains(coin))return true;
        return false;
    }

    @Override
    public double makePayment(CartService cartBO) {
        if(cartBO==null)throw new IllegalArgumentException();
        double amountRequired=totalAmountRequired(cartBO);
        if(amountRequired>amountReceived){
                System.err.println("Total amount required: "+amountRequired);
                System.err.println("Total amount received: "+amountReceived);
                System.out.println("Need extra money "+(amountRequired-amountReceived));
        }else {
            double extraAmount = amountReceived - amountRequired;
            if (!canReturnAmount(extraAmount)) {
                System.out.println("Can not return extra amount received " + extraAmount);
            }else{
                amountReceived -= amountRequired;
                if (removeProductFromStock(cartBO)) {
                    System.out.println("Competed Transaction Successfully!!!");
                } else {
                    amountReceived += amountRequired;
                    System.err.println("Sorry, failed to process transaction because of stock unavailable at this movement.");
                }
            }
        }
        if (amountReceived > 0) {
            System.out.println("Returning money: " + amountReceived);
        }
        return amountReceived;
    }

    @Override
    public boolean removeProductFromStock(CartService cartBO) {
        if(cartBO==null)throw new IllegalArgumentException();
        return  productStockService.removeProducts(cartBO);
    }


    private boolean canReturnAmount(double extraAmount) {
        for(Coin coin:validCoins){
            extraAmount=extraAmount%coin.amount;
            if(extraAmount==0)return true;
        }
        return false;
    }


}
