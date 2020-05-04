package com.teksystems.machine.intr.ProductStock_class;


import com.teksystems.machine.enm.Products;
import com.teksystems.machine.impl.product.Candy;
import com.teksystems.machine.impl.product.Chips;
import com.teksystems.machine.impl.product.Cola;
import com.teksystems.machine.intr.PaymentHelper;
import com.teksystems.machine.intr.PaymentHelper_class.Helper;
import com.teksystems.machine.intr.Product;
import com.teksystems.machine.intr.ProductStock;
import com.teksystems.machine.util.CartService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.util.Set;

public class RemoveProducts_removeProducts_Test extends Helper {


    ProductStock productStock;

    @After
    public void removeAllProducts(){
        if(productStock!=null){
            productStock.removeAllProducts();
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_removeProducts_check_exception(){
        int chipsInStock=10,colaInStock=10,candyInStock=10;
        int dimes=1,nickes=1,quarter=1;
        productStock=getProductStock(chipsInStock,colaInStock,candyInStock);
        PaymentHelper paymentHelper=getPaymentHelper(productStock,dimes,nickes,quarter);
        boolean res= paymentHelper.removeProductFromStock(null);
    }



    //More product in cart than in stock
    @Test
    public void test_removeProducts_more_product_in_cart(){
        //Just 5 candy available in Stock. But in cart it has 10 candies;
        int chipsInStock=10,colaInStock=10,candyInStock=5;
        int chipsInCart=10,colaInCart=10,candyInCart=10;
        productStock=getProductStock(chipsInStock,colaInStock,candyInStock);
        CartService cartService=getCartservice(candyInCart,colaInCart,chipsInCart);
        boolean res= productStock.removeProducts(cartService);
        Assert.assertEquals(false,res);
    }


    @Test
    public void test_removeProducts_remove_products_from_stock(){
        //Just 5 candy available in Stock. But in cart it has 10 candies;
        int chipsInStock=10,colaInStock=10,candyInStock=10;
        int chipsInCart=10,colaInCart=10,candyInCart=10;
        productStock=getProductStock(chipsInStock,colaInStock,candyInStock);
        CartService cartService=getCartservice(candyInCart,colaInCart,chipsInCart);
        boolean res= productStock.removeProducts(cartService);
        if(!res)Assert.assertTrue(false);
        Set<Product> set=productStock.getAllProducts();
        Assert.assertEquals(0,set.size());
    }


    @Test
    public void test_removeProducts_should_candy_left_in_stock(){
        //Just 5 candy available in Stock. But in cart it has 10 candies;
        int chipsInStock=10,colaInStock=10,candyInStock=10;
        int chipsInCart=10,colaInCart=10,candyInCart=5;
        productStock=getProductStock(chipsInStock,colaInStock,candyInStock);
        CartService cartService=getCartservice(candyInCart,colaInCart,chipsInCart);
        boolean res= productStock.removeProducts(cartService);
        if(!res)Assert.assertTrue(false);
        boolean expectedResult=false;
        Set<Product> set=productStock.getAllProducts();
        if(set!=null&&set.size()==1){
            Product product=set.iterator().next();
            if(product.products== Products.Candy){
                expectedResult=true;
            }
        }
        Assert.assertEquals(true,expectedResult);
    }

    @Test
    public void test_removeProducts_should_all_product_in_stock(){
        //Just 5 candy available in Stock. But in cart it has 10 candies;
        int chipsInStock=10,colaInStock=10,candyInStock=10;
        int chipsInCart=5,colaInCart=5,candyInCart=5;
        productStock=getProductStock(chipsInStock,colaInStock,candyInStock);
        CartService cartService=getCartservice(candyInCart,colaInCart,chipsInCart);
        boolean res= productStock.removeProducts(cartService);
        if(!res)Assert.assertTrue(false);
        boolean expectedResult=false;
        Set<Product> set=productStock.getAllProducts();
        if(set!=null&&set.size()==3){
            if(productStock.isProductAvailable(new Candy(),5) &&
                    productStock.isProductAvailable(new Chips(),5) &&
                    productStock.isProductAvailable(new Cola(),5) ){
                expectedResult=true;
            }
        }
        Assert.assertEquals(true,expectedResult);
    }

}
