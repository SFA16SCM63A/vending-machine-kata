package com.teksystems.machine.impl;

import com.teksystems.machine.enm.Coins;
import com.teksystems.machine.intr.Coin;
import com.teksystems.machine.util.CartService;
import com.teksystems.machine.intr.Product;
import com.teksystems.machine.intr.ProductStock;

import java.util.*;

public class ProductStockImpl implements ProductStock {

    private Map<Product,Integer> productStackMap;
    private static ProductStockImpl productStack;
    private static Object obj=new Object();
    private ProductStockImpl(){
        productStackMap=new HashMap<>();
    }

    public static ProductStockImpl getInstance(){
        ProductStockImpl tempStack=productStack;
        if(tempStack==null){
            synchronized (obj){
                productStack=new ProductStockImpl();
            }
        }
        return productStack;
    }

    @Override
    public boolean checkAvaliable(CartService cartBO){
        if(productStackMap==null||cartBO==null)throw new IllegalArgumentException();
        Map<Product,Integer> cartProducts=cartBO.getMapProducts();
        for(Map.Entry<Product,Integer> entry:cartProducts.entrySet()){
            Integer stockProductCount=productStackMap.get(entry.getKey());
            if(stockProductCount==null|| stockProductCount<entry.getValue()){
                System.err.println("Not enough " + entry.getKey() + " present. Just " + ((stockProductCount == null) ? 0 : stockProductCount) + " available");
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean removeProducts(CartService cartBO){
        if(productStackMap==null||cartBO==null)throw new IllegalArgumentException();
        Map<Product,Integer> cartProductsMap=cartBO.getMapProducts();

        /*Check if all products in Cart are present in Stock.*/
        if(!checkAvaliable(cartBO)){
            return false;
        }

        /*Remove products in Cart from Stock*/
        for(Map.Entry<Product,Integer> entry:cartProductsMap.entrySet()){
            Integer stockProductCount=productStackMap.get(entry.getKey());
            stockProductCount-=entry.getValue();
            if(stockProductCount<0)throw new IllegalStateException();
            if(stockProductCount==0){
                productStackMap.remove(entry.getKey());
            }else{
                productStackMap.put(entry.getKey(),entry.getValue());
            }
        }
        return true;
    }

    @Override
    public boolean addNewProductsIntoStock(Map<Product, Integer> productsMap){
            if(productsMap==null) throw new IllegalArgumentException();
            productsMap.forEach((k,v)->{
                addProduct(k,v);
            });
            return true;
    }

    @Override
    public void addProduct(Product product,Integer pCount){
        if(product==null||pCount==null)throw new IllegalArgumentException();
        Integer count=productStackMap.get(product);
        if(count==null){
            count=0;
        }
        count+=pCount;
        productStackMap.put(product,count);
    }

    @Override
    public boolean hasProducts() {
        return !productStackMap.isEmpty();
    }

    @Override
    public Set<Product> getAllProducts() {
        return productStackMap.keySet();
    }

    @Override
    public boolean isProductAvailable(Product product,int quantity){
        Integer quantityAvailable=productStackMap.get(product);
        if(quantityAvailable>=quantity)return true;
        System.err.println("Only "+quantityAvailable+" available for "+product.products.toString());
        return false;
    }

    @Override
    public boolean removeAllProducts() {
        productStackMap=new HashMap<>();
        return true;
    }

}
