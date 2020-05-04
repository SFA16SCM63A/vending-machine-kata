package com.teksystems.machine.intr.PaymentHelper_class;

import com.teksystems.machine.impl.Coin.Dimes;
import com.teksystems.machine.impl.Coin.Nickels;
import com.teksystems.machine.impl.Coin.Quarters;
import com.teksystems.machine.impl.PaymentHelperImpl;
import com.teksystems.machine.impl.ProductStockImpl;
import com.teksystems.machine.intr.Coin;
import com.teksystems.machine.intr.PaymentHelper;
import com.teksystems.machine.intr.ProductStock;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class PaymentHelper_totalPaymentReceived_test {
    List<Coin> coins;
    double expectedAmount;

    public PaymentHelper_totalPaymentReceived_test(List<Coin> coins,double expectedAmount) {
        this.coins = coins;
        this.expectedAmount=expectedAmount;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> getParameters(){
        return Arrays.asList(new Object[][]{
                {Arrays.asList(new Coin[]{new Quarters()}),0.25 } ,
                {Arrays.asList(new Coin[]{new Nickels()}),0.05 } ,
                {Arrays.asList(new Coin[]{new Dimes()}),0.10 } ,
                {Arrays.asList(new Coin[]{new Quarters(),new Nickels()}),0.3 } ,
                {Arrays.asList(new Coin[]{new Quarters(),new Dimes()}),0.35} ,
                {Arrays.asList(new Coin[]{new Nickels(),new Dimes()}),0.15 } ,
                {Arrays.asList(new Coin[]{new Quarters(),new Nickels(),new Dimes()}),0.4} ,
        });
    }

    @Test
    public void test(){
        ProductStock productStock= ProductStockImpl.getInstance();
        PaymentHelper paymentHelper=new PaymentHelperImpl(productStock);
        double resAmount=paymentHelper.totalPaymentReceived(coins);
        Assert.assertEquals(expectedAmount,resAmount,0.00002);
    }

}
