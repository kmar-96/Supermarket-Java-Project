package supermarket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Menu {

  public static void welcomeMessage() {
    System.out.printf("---DMST SUPERMARKET---\n"
               + "Log in to continue\n");
  }

  public static String[] getCredentials() {
    Scanner input = new Scanner(System.in);
    String[] credentials = new String[2];
    System.out.print("Username: ");
    credentials[0] = input.nextLine();
    System.out.printf("Password: ");
    credentials[1] = input.nextLine();
    return credentials;
  }

  public static SimpleCustomer checkCredentials(String username, String password) {
    ArrayList<SimpleCustomer> customers = SimpleCustomer.getCustomers();
    for (SimpleCustomer customer : customers) {
      if (customer.getUsername().equals(username)
                 && customer.getPassword().equals(password)) {
        return customer;
      }
    }
    return null;
  }

  public static void getSimpleMenu(SimpleCustomer sc) {
    Scanner input = new Scanner(System.in);
    System.out.printf("** Menu **\n"
            + "1. Change Password\n"
            + "2. Print Products\n"
            + "3. Buy Products\n"
            + "Make a choice: ");
    int choice = input.nextInt();
    System.out.println();

    switch (choice) {
      case 1:
        changeUserPassword(sc);
        break;
      case 2:
        printProductsSimple();
        break;
      case 3:
        buyProducts(sc);
        break;
      default:
        System.out.println("Not a valid choice.");
    }
  }

  public static void getBonusMenu(BonusCustomer bc) {
    Scanner input = new Scanner(System.in);
    System.out.print("** Menu **\n"
            + "1. Change Password\n"
            + "2. Print Bonus Points\n"
            + "3. Print Available Products\n"
            + "4. Buy Products\n"
            + "5. Print Previous Transactions\n"
            + "Make a choice: ");
    int choice  = input.nextInt();
    System.out.println();

    switch (choice) {
      case 1:
        changeUserPassword(bc);
        break;
      case 2:
        printBonusPoints(bc);
        break;
      case 3:
        printProductsBonus();
        break;
      case 4:
        buyProducts(bc);
        break;
      case 5:
        printPrevTransactions(bc);
        break;
      default:
        System.out.println("Not a valid choice.");
    }
  }

  public static void changeUserPassword(SimpleCustomer sc) {
    Scanner input = new Scanner(System.in);
    System.out.print("Type new password: ");
    String password1 = input.nextLine();
    System.out.print("Type it again: ");
    String password2 = input.nextLine();
    sc.changePassword(password1, password2);    
  }

  public static void printBonusPoints(BonusCustomer bc) {
    int points = bc.getCard().getPoints();
    int needed = 200 - points;
    System.out.printf("You have %d points ",points);
    System.out.printf("you need %d to get 20%% discount\n",needed);
  }

  public static void printProductsBonus() {
    ArrayList<Product> products = Product.getProducts();
    for (Product product : products) {
      if (product.getQuantity() > 0) {
        System.out.println(product);  
      }
    }  
  }

  public static void printProductsSimple() {
    ArrayList<Product> products = Product.getProducts();
    for (Product product : products) {
      System.out.println(product);
    }
  }

  public static void buyProducts(SimpleCustomer cust) {
    int id;
    int quantity;
    int totalQuantity = 0;
    Product prod;
    ArrayList<int[]> basket = new ArrayList<int[]>();
    Scanner input = new Scanner(System.in);

    while (true) {
      int[] purchase = new int[2];
      System.out.print("Type product id: ");
      id = input.nextInt();
      if (id == -1) {
        break;
      }
      prod = Product.checkAvailability(id);
      if (prod == null) {
        System.out.print("Product with id " + id + " does not exist. ");
        System.out.println("Try again.");
      } else if (prod.getQuantity() == 0) {
        System.out.println("Product " + prod.getName() + " is not available");
      } else {
        while (true) {
          System.out.print("Quantity: ");
          quantity = input.nextInt();
          if (quantity <= prod.getQuantity()) {
            purchase[0] = id;
            purchase[1] = quantity;
            basket.add(purchase);
            prod.setQuantity(prod.getQuantity() - quantity);
            totalQuantity += quantity;
            break;
          } else {
            System.out.printf("Not enough stock. ");
            System.out.println("Stock: " + prod.getQuantity() + " pieces.");
          }
        }
      }
    }
    Receipt rec = new Receipt(cust,basket);
    rec.printReceipt();
    rec.setItems(totalQuantity);
    if (cust instanceof BonusCustomer) {
      BonusCustomer customer = (BonusCustomer) cust;
      int ptsWon = customer.getCard().pointsWon(rec.getCost());
      customer.getCard().addPoints(ptsWon);
      int points = customer.getCard().getPoints();
      double totalCost = rec.getCost();
      if (points >= 200) {
        System.out.println("Today you earned " + ptsWon + " points and reached"
                + " 200 points. You get 20% discount.");
        customer.getCard().setPoints(points - 200);
        points = customer.getCard().getPoints();
        totalCost *= 0.8 ;
        rec.setCost(totalCost);
        System.out.println("You now have " + points + " points.");
        System.out.printf("Total cost: %.2f\n\n",totalCost);
      } else {
        System.out.println("Not enough points for a discount yet!");
        System.out.printf("Total cost: %.2f\n\n",totalCost);
      }
    } else {
      System.out.printf("Total cost: %.2f\n\n",rec.getCost());
    }
  }

  public static void printPrevTransactions(BonusCustomer bc) {
    ArrayList<Receipt> receipts = Receipt.getReceipts();
    for (Receipt receipt : receipts) {
      if (receipt.getBonusCustomer().equals(bc)) {
        System.out.println(receipt);
      }
    }
  }

}