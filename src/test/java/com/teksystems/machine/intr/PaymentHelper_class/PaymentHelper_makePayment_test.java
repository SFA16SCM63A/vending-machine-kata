package com.teksystems.machine.intr.PaymentHelper_class;

import com.teksystems.machine.intr.PaymentHelper;
import com.teksystems.machine.intr.ProductStock;
import com.teksystems.machine.util.CartService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

public class PaymentHelper_makePayment_test extends Helper {

    ProductStock productStock;

    @After
    public void removeAllProducts(){
        if(productStock!=null){
            productStock.removeAllProducts();
        }
    }

    @Test
    public void test_return_all_money_because_insufficient_amount_paid(){
        int chipsInStock=10,colaInStock=10,candyInStock=10;
        int chipsInCart=10,colaInCart=10,candyInCart=10;
        int dimes=1,nickes=1,quarter=1;
        productStock=getProductStock(chipsInStock,colaInStock,candyInStock);
        PaymentHelper paymentHelper=getPaymentHelper(productStock,dimes,nickes,quarter);
        CartService cartService=getCartservice(chipsInCart,colaInCart,candyInCart);
        double amountReturned= paymentHelper.makePayment(cartService);
        double totalAmount=totalAmount(dimes,nickes,quarter);

        Assert.assertEquals(totalAmount,amountReturned,0);
    }


    @Test
    public void test_pay_exact_amount_return_0(){
        int chipsInStock=10,colaInStock=10,candyInStock=10;
        int chipsInCart=10,colaInCart=10,candyInCart=10;
        int dimes=0,nickes=0,quarter=86;
        productStock=getProductStock(chipsInStock,colaInStock,candyInStock);
        PaymentHelper paymentHelper=getPaymentHelper(productStock,dimes,nickes,quarter);
        CartService cartService=getCartservice(chipsInCart,colaInCart,candyInCart);
        double amountReturned= paymentHelper.makePayment(cartService);
        Assert.assertEquals(0,amountReturned,0);
    }


    @Test
    public void test_product_unavalibale_return_all_money(){
        //ProductStock has only 9 Candy, but required 10 Candy
        int chipsInStock=10,colaInStock=10,candyInStock=9;
        int chipsInCart=10,colaInCart=10,candyInCart=10;
        int dimes=0,nickes=0,quarter=86;
        productStock=getProductStock(chipsInStock,colaInStock,candyInStock);
        PaymentHelper paymentHelper=getPaymentHelper(productStock,dimes,nickes,quarter);
        CartService cartService=getCartservice(chipsInCart,colaInCart,candyInCart);
        double amountReturned= paymentHelper.makePayment(cartService);
        double totalAmount=totalAmount(dimes,nickes,quarter);
        Assert.assertEquals(totalAmount,amountReturned,0);
    }

}
