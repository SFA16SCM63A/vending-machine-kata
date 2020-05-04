package com.teksystems.machine.intr.PaymentHelper_class;

import com.teksystems.machine.intr.PaymentHelper;
import com.teksystems.machine.intr.ProductStock;
import com.teksystems.machine.util.CartService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class PaymentHelper_makePayment_exceptionhandling_test extends Helper {

    ProductStock productStock;

    @After
    public void removeAllProducts(){
        if(productStock!=null){
            productStock.removeAllProducts();
        }
    }

    @Test(expected = Exception.class)
    public void test_return_all_money_because_insufficient_amount_paid(){
        int chipsInStock=10,colaInStock=10,candyInStock=10;
        int dimes=1,nickes=1,quarter=1;
        productStock=getProductStock(chipsInStock,colaInStock,candyInStock);
        PaymentHelper paymentHelper=getPaymentHelper(productStock,dimes,nickes,quarter);
        double amountReturned= paymentHelper.makePayment(null);
        double totalAmount=totalAmount(dimes,nickes,quarter);
        Assert.assertEquals(totalAmount,amountReturned,0);
    }

}
