import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

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

  public int getId() {
    return id;
  }

  @Override
  public boolean equals(Object otherClient) {
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
      String sql = "INSERT INTO clients (name, contactinfo, stylistid) VALUES (:name, :contactinfo, :stylistid)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("contactinfo", this.contactInfo)
        .addParameter("stylistid", this.stylistId)
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
      Client client = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Client.class);
      return client;
    }
  }

  public void update(String name, String contactInfo) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE clients SET name = :name, contactinfo = :contactinfo WHERE id = :id";
      con.createQuery(sql)
        .addParameter("name", name)
        .addParameter("contactinfo", contactInfo)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
    String sql = "DELETE FROM clients WHERE id = :id;";
    con.createQuery(sql)
      .addParameter("id", id)
      .executeUpdate();
    }
  }
}
