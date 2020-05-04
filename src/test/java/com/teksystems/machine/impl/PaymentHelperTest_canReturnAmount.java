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
                {0.5,true},
                {0.65,true},
                {0.25,true},
                {0.05,true},
                {0.04,false},
                {0.01,false}
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
            Assert.assertTrue(false);
        }
    }


}
