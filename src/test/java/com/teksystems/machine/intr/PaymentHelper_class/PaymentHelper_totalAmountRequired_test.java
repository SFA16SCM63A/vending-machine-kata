package com.teksystems.machine.intr.PaymentHelper_class;

import com.teksystems.machine.impl.PaymentHelperImpl;
import com.teksystems.machine.intr.PaymentHelper;
import com.teksystems.machine.util.CartService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class PaymentHelper_totalAmountRequired_test extends Helper {

    int chipsInCart;
    int colaInCart;
    int candyInCart;
    double expectedAmount;

    public PaymentHelper_totalAmountRequired_test(int chipsInCart, int colaInCart, int candyInCart, double expectedAmount) {
        this.chipsInCart = chipsInCart;
        this.colaInCart = colaInCart;
        this.candyInCart = candyInCart;
        this.expectedAmount = expectedAmount;
    }


    @Parameterized.Parameters
    public static Collection<Object[]> getParameters(){
        return Arrays.asList(new Object[][]{
                {1,1,1,2.15},
                {1,0,0,0.65},
                {0,1,0,1.00},
                {0,0,1,0.50},
                {10,10,10,21.5}
        });
    }

    @Test
    public void test_totalPaymentReceived(){
        PaymentHelper paymentHelper=new PaymentHelperImpl(null);
        CartService cartService=getCartservice(chipsInCart,colaInCart,candyInCart);
        double amountReturned= paymentHelper.totalAmountRequired(cartService);
        Assert.assertEquals(expectedAmount,amountReturned,0);
    }
}
