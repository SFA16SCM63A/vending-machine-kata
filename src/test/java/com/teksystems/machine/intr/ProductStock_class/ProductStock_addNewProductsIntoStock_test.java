package com.teksystems.machine.intr.ProductStock_class;

import com.teksystems.machine.impl.ProductStockImpl;
import com.teksystems.machine.impl.product.Candy;
import com.teksystems.machine.impl.product.Chips;
import com.teksystems.machine.impl.product.Cola;
import com.teksystems.machine.intr.Product;
import com.teksystems.machine.intr.ProductStock;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ProductStock_addNewProductsIntoStock_test {

    @After
    public void removeAllProducts(){
        ProductStock productStock=ProductStockImpl.getInstance();
        productStock.removeAllProducts();
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_addNewProductsIntoStock_check_argumentException(){
        ProductStock productStock=ProductStockImpl.getInstance();
        productStock.addNewProductsIntoStock(null);
    }

    @Test
    public void test_addNewProductsIntoStock_addProducts(){
        ProductStock productStock=ProductStockImpl.getInstance();
        boolean res=productStock.addNewProductsIntoStock(new HashMap<>());
        Assert.assertTrue(res);
    }


    @Test
    public void test_addNewProductsIntoStock(){
        Map<Product, Integer> map=new HashMap<>();
        map.put(new Cola(),5);
        map.put(new Chips(),5);
        map.put(new Candy(),5);

        ProductStock productStock=ProductStockImpl.getInstance();
        boolean res=productStock.addNewProductsIntoStock(map);
        boolean expectedOutput=false;
        if(res){
            if(productStock.isProductAvailable(new Cola(),5)&&
            productStock.isProductAvailable(new Chips(),5)&&
            productStock.isProductAvailable(new Candy(),5)){
                expectedOutput=true;
            }
        }
        Assert.assertTrue(expectedOutput);
    }


}
