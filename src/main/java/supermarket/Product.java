package supermarket;

import java.util.ArrayList;

public class Product {

  private static int counter = 0;
  private static ArrayList<Product> products = new ArrayList<Product>();
  private int id;
  private String name;
  private double price;
  private int quantity;
  

  public Product(String nm, double prc, int q) {
    products.add(this);
    counter++;
    setId(counter);
    setName(nm);
    setPrice(prc);
    setQuantity(q);
  }

  public void setId(int iden) {
    id = iden;
  }

  public int getId() {
    return id;
  }

  public void setName(String nm) {
    name = nm;
  }

  public String getName() {
    return name;
  }

  public static String getNameById(int id) {
    for (Product product : products) {
      if (product.getId() == id) {
        return product.getName();
      }
    }
    return null;
  }

  public void setPrice(double prc) {
    price = prc;
  }

  public double getPrice() {
    return price;
  }

  public static double getPriceById(int id) {
    for (Product product : products) {
      if (product.getId() == id) {
        return product.getPrice();
      }
    }
    return -1;
  }

  public void setQuantity(int q) {
    quantity = q;
  }

  public int getQuantity() {
    return quantity;
  }

  public static ArrayList<Product> getProducts() {
    return products;
  }

  public static Product checkAvailability(int iden) {
    for (Product product : products) {
      if (product.getId() == iden) {
        return product;
      }
    }
    return null;
  }

  public String toString() {
    return String.format("Product [id=%d, name=%s, price=%.2f, available quantity=%d]",
        getId(),getName(), getPrice(), getQuantity());
  }

}