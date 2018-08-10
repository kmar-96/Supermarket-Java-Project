package supermarket;

public class BonusCustomer extends SimpleCustomer {

  private BonusCard card;

  public BonusCustomer(String nm, String usernm, String passwd) {
    super(nm,usernm,passwd);
    setCard(new BonusCard());
  }

  public void setCard(BonusCard cd) {
    card = cd;
  }

  public BonusCard getCard() {
    return card;
  }

}