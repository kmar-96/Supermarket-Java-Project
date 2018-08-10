package supermarket;

public class BonusCard {

  private int points;

  public void setPoints(int pts) {
    points = pts;
  }

  public int getPoints() {
    return points;
  }

  public void addPoints(int pts) {
    points += pts;
  }

  public int pointsWon(double cost) {
    return (int) cost / 3;
  }

}