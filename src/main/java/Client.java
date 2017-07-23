import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;

public class Client {
  private String name;
  private String contactInfo;
  private int stylistId;
  private int id;

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

  public int getId(){
    return id;

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

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO clients (name, contactinfo, stylistid) VALUES (:name, :contactinfo, :stylistId)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("contactInfo", this.contactInfo)
        .addParameter("stylistId", this.stylistId)
        .executeUpdate()
        .getKey();
    }
  }

  public static List<Client> all() {
    String sql = "SELECT * FROM clients";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Client.class);
    }
  }

  public static Client find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM clients where id=:id";
      Client monster = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Client.class);
      return monster;
    }
  }

  public List<Client> getClients() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM clients where stylistId=:id";
      return con.createQuery(sql)
        .addParameter("id", this.id)
        .executeAndFetch(Client.class);
    }
  }
}
