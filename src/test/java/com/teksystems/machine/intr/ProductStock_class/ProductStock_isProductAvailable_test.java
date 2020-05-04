package com.teksystems.machine.intr.ProductStock_class;

import com.teksystems.machine.impl.ProductStockImpl;
import com.teksystems.machine.impl.product.Candy;
import com.teksystems.machine.intr.Product;
import com.teksystems.machine.intr.ProductStock;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

public class ProductStock_isProductAvailable_test {


    @Test
    public void test_exactly_same_product(){
        ProductStock productStock= ProductStockImpl.getInstance();
        productStock.addProduct(new Candy(),20);
        boolean res= productStock.isProductAvailable(new Candy(),20);
        Assert.assertEquals(true,res);
    }

    @Test
    public void test_less_product_than_Available(){
        ProductStock productStock= ProductStockImpl.getInstance();
        productStock.addProduct(new Candy(),20);
        boolean res= productStock.isProductAvailable(new Candy(),10);
        Assert.assertEquals(true,res);
    }


    @Test
    public void test_more_product_than_available(){
        ProductStock productStock= ProductStockImpl.getInstance();
        productStock.addProduct(new Candy(),20);
        boolean res= productStock.isProductAvailable(new Candy(),30);
        Assert.assertEquals(false,res);
    }

}
