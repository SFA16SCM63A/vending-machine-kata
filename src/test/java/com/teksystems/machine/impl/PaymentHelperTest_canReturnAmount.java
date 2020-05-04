package com.teksystems.machine.impl;

import com.teksystems.machine.intr.ProductStock;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class PaymentHelperTest_canReturnAmount {

    public final double extraAmount;
    public final boolean expectedResult;
    public PaymentHelperTest_canReturnAmount(double extraAmount,boolean expectedResult){
        this.expectedResult=expectedResult;
        this.extraAmount=extraAmount;
    }



    @Parameterized.Parameters
    public static Collection<Object[]> getParameters(){
        return Arrays.asList(new Object[][]{
                {100,true},
                {200,true},
                {10,true},
                {5,true},
                {13,false},
                {5.5,false}
        });
    }


    @Test
    public void test()  {
        ProductStock productStockService= ProductStockImpl.getInstance();
        PaymentHelperImpl paymentHelper=new PaymentHelperImpl(productStockService);
        try{
            Class c = paymentHelper.getClass();
            Method m=c.getDeclaredMethod("canReturnAmount",new Class[]{double.class});
            m.setAccessible(true);
            boolean res= (boolean) m.invoke(paymentHelper,extraAmount);
            Assert.assertEquals(expectedResult,res);
        }catch (Exception e){
            e.printStackTrace();
        }
        Assert.assertTrue(false);
    }


}
