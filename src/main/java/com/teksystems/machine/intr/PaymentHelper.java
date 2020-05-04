package com.teksystems.machine.intr;

import com.teksystems.machine.util.CartService;

import java.util.List;

public interface PaymentHelper {

     Double totalAmountRequired(CartService cartService);
     Double totalPaymentReceived(List<Coin> coins);
     double makePayment(CartService cartService);
     boolean removeProductFromStock(CartService cartService);
}
