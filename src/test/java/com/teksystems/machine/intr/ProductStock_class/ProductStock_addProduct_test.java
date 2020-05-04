package com.teksystems.machine.intr.ProductStock_class;

import com.teksystems.machine.impl.ProductStockImpl;
import com.teksystems.machine.impl.product.Cola;
import com.teksystems.machine.intr.ProductStock;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

public class ProductStock_addProduct_test {

    @After
    public void removeAllProducts(){
        ProductStock productStock= ProductStockImpl.getInstance();
        productStock.removeAllProducts();
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_addNewProductsIntoStock_check_argumentException_1(){
        ProductStock productStock=ProductStockImpl.getInstance();
        productStock.addProduct(null,null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_addNewProductsIntoStock_check_argumentException_2(){
        ProductStock productStock=ProductStockImpl.getInstance();
        productStock.addProduct(new Cola(),null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_addNewProductsIntoStock_check_argumentException_3(){
        ProductStock productStock=ProductStockImpl.getInstance();
        productStock.addProduct(null,5);
    }


    @Test
    public void test_addNewProductsIntoStock_check_less_product_than_Added(){
        ProductStock productStock=ProductStockImpl.getInstance();
        productStock.addProduct(new Cola(),1);
        productStock.addProduct(new Cola(),2);
        productStock.addProduct(new Cola(),3);
        Assert.assertEquals(true,productStock.isProductAvailable(new Cola(),5));

    }

    @Test
    public void test_addNewProductsIntoStock_check_exactly_same_number(){
        ProductStock productStock=ProductStockImpl.getInstance();
        productStock.addProduct(new Cola(),1);
        productStock.addProduct(new Cola(),2);
        productStock.addProduct(new Cola(),3);
        Assert.assertEquals(true,productStock.isProductAvailable(new Cola(),6));
    }


    @Test
    public void test_addNewProductsIntoStock_check_more_products_than_added(){
        ProductStock productStock=ProductStockImpl.getInstance();
        productStock.addProduct(new Cola(),1);
        productStock.addProduct(new Cola(),2);
        productStock.addProduct(new Cola(),3);
        Assert.assertEquals(false,productStock.isProductAvailable(new Cola(),7));
    }

}
