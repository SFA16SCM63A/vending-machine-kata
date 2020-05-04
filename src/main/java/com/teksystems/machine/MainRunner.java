package com.teksystems.machine;

import com.teksystems.machine.impl.Coin.Dimes;
import com.teksystems.machine.impl.Coin.Nickels;
import com.teksystems.machine.impl.Coin.Quarters;
import com.teksystems.machine.util.CartService;
import com.teksystems.machine.impl.product.Candy;
import com.teksystems.machine.impl.product.Chips;
import com.teksystems.machine.impl.product.Cola;
import com.teksystems.machine.intr.Coin;
import com.teksystems.machine.intr.Product;
import com.teksystems.machine.intr.ProductStock;
import com.teksystems.machine.impl.PaymentHelperImpl;
import com.teksystems.machine.impl.ProductStockImpl;

import java.util.*;

public class MainRunner {


    public static void main(String args[]){
        ProductStock productStockService= ProductStockImpl.getInstance();
        Map<Product,Integer> newProductStock=loadProducts();
        productStockService.addNewProductsIntoStock(newProductStock);

        List<Product> products= new ArrayList<>(productStockService.getAllProducts());
        Scanner sc=new Scanner(System.in);
        Integer selectedChoice=8;
        while(productStockService.hasProducts()){
           boolean addMoreProduct=true;
           CartService cartBO=new CartService();
           while(addMoreProduct){
                 for(int itr=0;itr<products.size();itr++){
                     System.out.println(" "+(itr+1)+". "+products.get(itr).products.toString());
                 }
                 System.out.println("8. Abort");
                 System.out.println("9. Make Payment");
                 System.out.println("Enter number: ");
                 selectedChoice=sc.nextInt();
                 if(selectedChoice==8||selectedChoice==9){
                     addMoreProduct=false;
                 }else{
                     Product product=products.get(selectedChoice);
                     if(product==null){
                         System.err.println("Please enter proper choice!!!");
                         continue;
                     }
                     Integer addedCount=cartBO.getTotalProductsPresent(product);
                     if(productStockService.isProductAvailable(product,addedCount+1)){
                         cartBO.addToCart(product);
                     }else{
                         System.err.println("Failed to add "+product);
                         continue;
                     }
                 }
           }
           if(selectedChoice==8){
              return;
           }
            PaymentHelperImpl paymentHelper=new PaymentHelperImpl(productStockService);
            paymentHelper.totalPaymentReceived(getCoins(sc));
            paymentHelper.makePayment(cartBO);
        }
    }

    private static List<Coin> getCoins(Scanner scanner) {
        List<Coin> list=new ArrayList<>();

        while(scanner.hasNext()){
            System.out.println("Select coin you want to add: ");
            System.out.println("1. Dimes");
            System.out.println("2. Nickels");
            System.out.println("3. Quarters");
            System.out.println("9. Exit");
            Integer val=scanner.nextInt();
            switch (val){
                case 1: list.add(new Dimes());break;
                case 2: list.add(new Nickels());break;
                case 3: list.add(new Quarters());break;
                case 9: return list;
                default: System.err.println("Invalid choice");
            }
        }

        return list;
    }

    private static Map<Product, Integer> loadProducts() {
        Map<Product,Integer> map=new HashMap<>();
        map.put(new Chips(),10);
        map.put(new Candy(),10);
        map.put(new Cola(),20);
        return map;
    }


}
