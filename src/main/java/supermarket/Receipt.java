package supermarket;

import java.util.ArrayList;
import java.util.Date;

public class Receipt {

  private static int counter = 0;
  private static ArrayList<Receipt> receipts = new ArrayList<Receipt>();
  private int id;
  private BonusCustomer customer;
  private Date date;
  private double cost;
  private ArrayList<int[]> basket = new ArrayList<int[]>();
  private int items;

  public Receipt(SimpleCustomer cust, ArrayList<int[]> bskt) {
    receipts.add(this);
    counter++;
    setId(counter);
    setDate(new Date());
    setBonusCustomer(cust);
    setBasket(bskt);
    calculateCost();

  }

  public void setBonusCustomer(SimpleCustomer cust) {
    if (cust instanceof BonusCustomer) {
      customer = (BonusCustomer) cust;
    } else {
      customer = null;
    }
  }

  public BonusCustomer getBonusCustomer() {
    return customer;
  }

  public void setId(int iden) {
    id = iden;
  }

  public int getId() {
    return id;
  }

  public void setDate(Date dt) {
    date = dt;
  }

  public Date getDate() {
    return date;
  }

  public void setBasket(ArrayList<int[]> bskt) {
    basket = bskt;
  }

  public ArrayList<int[]> getBasket() {
    return basket;
  }

  public void setItems(int it) {
    items = it;
  }

  public int getItems() {
    return items;
  }

  public void calculateCost() {
    ArrayList<Product> products = Product.getProducts();
    double price;
    double cost = 0;
    int quantity;
    for (int[] purchase : basket) {
      for (Product product : products) {
        if (product.getId() == purchase[0]) {
          price = product.getPrice();
          quantity = purchase[1];
          cost += price * quantity;
          break;
        }
      }
    }
    setCost(cost);
  }

  public void setCost(double cst) {
    cost = Math.round(cst * 100.0) / 100.0;
  }

  public double getCost() {
    return cost;
  }

  public static ArrayList<Receipt> getReceipts() {
    return receipts;
  }

  public void printReceipt() {
    System.out.printf("Receipt #%d#\n",getId());
    System.out.println("Date of issue: " + getDate());
    for (int[] purchase : basket) {
      String name = Product.getNameById(purchase[0]);
      String price = Double.toString(Product.getPriceById(purchase[0]));
      int space = name.length() + price.length() + 6;
      int border = 50;
      int leave = border - space;
      System.out.printf("%-3s%s%7s","|",name,price);
      System.out.printf("%" + leave + "s\n","|");
      String quantity = Integer.toString(purchase[1]);
      String cost = Double.toString(Double.parseDouble(price) * purchase[1]);
      space = cost.length() + 12;
      leave = border - space;
      System.out.printf("%-8sx%-3s%s","|",quantity,cost);
      System.out.printf("%" + leave + "s\n","|");
    }
    String total = Double.toString(getCost());
    int space = total.length() + 9;
    int border = 50;
    int leave = border - space;
    System.out.printf("%-3sCost: %s","|",total);
    System.out.printf("%" + leave + "s\n","|");
  }

  public String toString() {
    return String.format("Date of issue: %s, Total Cost: %f, "
            + "Bought items: %d", getDate(), getCost(), getItems());
  }

}