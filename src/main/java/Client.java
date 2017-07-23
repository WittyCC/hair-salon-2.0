public class Client {
  private String name;
  private String contactInfo;
  private int stylistId;

  public Client(String name, String contactInfo, int stylistId) {
    this.name = name;
    this.contactInfo = contactInfo;
    this.stylistId = stylistId;
  }

  public String getName() {
    return name;
  }

  public String getContactInfo() {
    return contactInfo;
  }

  public int getStylistId() {
    return stylistId;
  }

  @Override
  public boolean equals(Object otherClient){
    if (!(otherClient instanceof Client)) {
      return false;
    } else {
      Client newClient = (Client) otherClient;
      return this.getName().equals(newClient.getName()) &&
             this.getStylistId() == newClient.getStylistId();
    }
  }
}
