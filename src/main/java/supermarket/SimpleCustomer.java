package supermarket;

import java.util.ArrayList;

public class SimpleCustomer {

  private static int counter = 0;
  private static ArrayList<SimpleCustomer> customers = new ArrayList<SimpleCustomer>(); 
  private int id;
  private String name;
  private String username;
  private String password;

  public SimpleCustomer(String nm, String usernm, String passwd) {
    customers.add(this);
    counter++;
    setId(counter);
    setName(nm);
    setUsername(usernm);
    setPassword(passwd);  
  }

  public int getCounter() {
    return counter;
  }

  public static ArrayList<SimpleCustomer> getCustomers() {
    return customers;
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

  public void setUsername(String usernm) {
    username = usernm;
  }

  public String getUsername() {
    return username;
  }

  public void setPassword(String passwd) {
    password = passwd;
  }

  public String getPassword() {
    return password;
  }

  public void changePassword(String pass1, String pass2) {
    if (pass1.equals(pass2)) {
      setPassword(pass1);
      System.out.printf("Password has been changed.\n\n");
    } else {
      System.out.printf("Passwords don't match.\n\n");
    }
  }

}