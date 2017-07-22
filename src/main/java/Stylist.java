public class Stylist {
  private String name;
  private String expertise;

  public Stylist() {
    this.name = name;
    this.expertise = expertise;
  }

  public String getName() {
    return name;
  }

  public String getExpertise() {
    return expertise;
  }

  @Override
  public boolean equals(Object otherStylist){
    if (!(otherStylist instanceof Stylist)) {
      return false;
    } else {
      Stylist newStylist = (Stylist) otherStylist;
      return this.getName().equals(newStylist.getName()) &&
             this.getEmail().equals(newStylist.getExpertise());
    }
  }
}
