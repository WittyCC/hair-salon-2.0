import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class ClientTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void monster_instantiatesCorrectly_true() {
    Client testClient = new Client("Vain Valerie", "206-XXX-XXXX", 1);
    assertEquals(true, testClient instanceof Client);
  }

  @Test
  public void Client_instantiatesWithName_String() {
    Client testClient = new Client("Vain Valerie", "206-XXX-XXXX", 1);
    assertEquals("Bubbles", testClient.getName());
  }

  @Test
  public void Client_instantiatesWithStylistId_int() {
    Client testClient = new Client("Vain Valerie", "206-XXX-XXXX", 1);
    assertEquals(1, testClient.getStylistId());
  }

  @Test
  public void equals_returnsTrueIfNameAndStylistIdAreSame_true() {
    Client testClient = new Client("Vain Valerie", "206-XXX-XXXX", 1);
    Client anotherClient = new Client("Vain Valerie", "206-XXX-XXXX", 1);
    assertTrue(testClient.equals(anotherClient));
  }

  @Test
  public void save_returnsTrueIfDescriptionsAretheSame() {
    Client testClient = new Client("Vain Valerie", "206-XXX-XXXX", 1);
    testClient.save();
    assertTrue(Client.all().get(0).equals(testClient));
  }
}
