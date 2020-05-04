package com.teksystems.machine.intr.ProductStock_class;

import com.teksystems.machine.impl.ProductStockImpl;
import com.teksystems.machine.intr.PaymentHelper_class.Helper;
import com.teksystems.machine.intr.ProductStock;
import com.teksystems.machine.util.CartService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

public class ProductStock_checkAvailable extends Helper {
    @After
    public void removeAllProducts(){
        ProductStock productStock= ProductStockImpl.getInstance();
        productStock.removeAllProducts();
    }

    @Test
    public void test_checkAvailable_exactly_same_product_in_Stock_and_cart(){
        ProductStock productStock=getProductStock(10,10,10);
        CartService cartService=getCartservice(10,10,10);
        boolean res=productStock.checkAvaliable(cartService);
        Assert.assertEquals(true,res);
    }

    @Test
    public void test_checkAvailable_exactly_more_product_in_Stock_than_in_cart(){
        ProductStock productStock=getProductStock(12,12,12);
        CartService cartService=getCartservice(10,10,10);
        boolean res=productStock.checkAvaliable(cartService);
        Assert.assertEquals(true,res);
    }
    @Test
    public void test_checkAvailable_exactly_less_product_in_Stock_than_in_cart_chips(){
        ProductStock productStock=getProductStock(5,10,10);
        CartService cartService=getCartservice(10,10,10);
        boolean res=productStock.checkAvaliable(cartService);
        Assert.assertEquals(false,res);
    }
    @Test
    public void test_checkAvailable_exactly_less_product_in_Stock_than_in_cart_cola(){
        ProductStock productStock=getProductStock(10,5,10);
        CartService cartService=getCartservice(10,10,10);
        boolean res=productStock.checkAvaliable(cartService);
        Assert.assertEquals(false,res);
    }
    @Test
    public void test_checkAvailable_exactly_less_product_in_Stock_than_in_cart_candy(){
        ProductStock productStock=getProductStock(10,10,5);
        CartService cartService=getCartservice(10,10,10);
        boolean res=productStock.checkAvaliable(cartService);
        Assert.assertEquals(false,res);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_checkAvailable_check_exception(){
        ProductStock productStock=getProductStock(10,10,10);
        boolean res=productStock.checkAvaliable(null);
        Assert.assertEquals(true,res);
    }
}
