package com.teksystems.machine.intr;

import com.teksystems.machine.enm.Coins;
import com.teksystems.machine.util.CartService;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ProductStock {
     boolean checkAvaliable(CartService cartBO);
     boolean removeProducts(CartService cartBO);
     boolean addNewProductsIntoStock(Map<Product, Integer> productsMap);
     void addProduct(Product product,Integer pCount);
     boolean hasProducts();
     Set<Product> getAllProducts();
     boolean isProductAvailable(Product product,int quantity);
     boolean removeAllProducts();
     //boolean addCoinsInMachine(List<Coin> listCoins);

}
