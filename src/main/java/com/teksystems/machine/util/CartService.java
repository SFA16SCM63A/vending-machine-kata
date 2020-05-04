package com.teksystems.machine.util;


import com.teksystems.machine.intr.Product;

import java.util.HashMap;
import java.util.Map;

public class CartService {
    private Map<Product,Integer> mapProducts;
    public CartService(){
        mapProducts=new HashMap<>();
    }

    public boolean addToCart(Product product){
        Integer totalProducts=mapProducts.get(product);
        if(totalProducts==null){
            totalProducts=0;
        }
        totalProducts++;
        mapProducts.put(product,totalProducts);
        return true;
    }

    public Map<Product,Integer> getMapProducts(){
        return mapProducts;
    }

    public Integer getTotalProductsPresent(Product product){
        return mapProducts.get(product);
    }

}
