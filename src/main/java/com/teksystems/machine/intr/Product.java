package com.teksystems.machine.intr;

import com.teksystems.machine.enm.Products;

import java.util.Objects;

public abstract class Product {

    //Price is in dollar
    public final double price;
    public final Products products;

    public Product(double price, Products products){
        this.price=price;
        this.products=products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product that = (Product) o;
        return price==that.price &&
                products == that.products;
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, products);
    }

    @Override
    public String toString() {
        return "Product{" +
                "price=" + price +
                ", products=" + products +
                '}';
    }
}
