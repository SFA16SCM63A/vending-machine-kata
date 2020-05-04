package com.teksystems.machine.intr;

import java.util.Objects;

public abstract class  Coin {

      //Amount is in Dollar
      public final double amount;

      //Weight is in ounces
      public final  double weight;

      //Size is in milli-meter
      public final  double size;

    public Coin(double amount, double weight, double size) {
        this.amount = amount;
        this.weight = weight;
        this.size = size;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coin coin = (Coin) o;
        return Double.compare(coin.amount, amount) == 0 &&
                Double.compare(coin.weight, weight) == 0 &&
                Double.compare(coin.size, size) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, weight, size);
    }


    @Override
    public String toString() {
        return "Coin{" +
                "amount=" + amount +
                ", weight=" + weight +
                ", size=" + size +
                '}';
    }
}
