package com.teksystems.machine.intr.PaymentHelper_class;
import com.teksystems.machine.impl.PaymentHelperImpl;
import com.teksystems.machine.impl.ProductStockImpl;
import com.teksystems.machine.intr.PaymentHelper;
import com.teksystems.machine.intr.ProductStock;
import com.teksystems.machine.util.CartService;
import org.junit.Assert;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class PaymentHelper_removeProductFromStock_test extends Helper {

    @Test(expected = Exception.class)
    public void test_removeProductFromStock_check_exception(){
         ProductStock  productStock=mock(ProductStockImpl.class);
        when(productStock.removeAllProducts()).thenReturn(true);
        PaymentHelper paymentHelper=new PaymentHelperImpl(productStock);
        boolean res= paymentHelper.removeProductFromStock(null);
    }


    //More product in cart than in stock
    @Test
    public void test_true_response(){
        ProductStock  productStock=mock(ProductStockImpl.class);
        when(productStock.removeAllProducts()).thenReturn(false);
        PaymentHelper paymentHelper=new PaymentHelperImpl(productStock);
        CartService cartService=mock(CartService.class);
        boolean res= paymentHelper.removeProductFromStock(cartService);
        Assert.assertEquals(false,res);
    }


    @Test
    public void test_false_response(){
        ProductStock  productStock=mock(ProductStockImpl.class);
        when(productStock.removeAllProducts()).thenReturn(false);
        PaymentHelper paymentHelper=new PaymentHelperImpl(productStock);
        CartService cartService=mock(CartService.class);
        boolean res= paymentHelper.removeProductFromStock(cartService);
        Assert.assertEquals(false,res);
    }
}
