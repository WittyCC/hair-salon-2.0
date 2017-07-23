import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.Arrays;

public class ClientTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void client_instantiatesCorrectly_true() {
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

  @Test
  public void all_returnsAllInstancesOfClient_true() {
    Client firstClient = new Client("Vain Valerie", "206-XXX-XXXX", 1);
    firstClient.save();
    Client secondClient = new Client("Narcissistic Nate", "206-XXX-XXXX", 1);
    secondClient.save();
    assertEquals(true, Client.all().get(0).equals(firstClient));
    assertEquals(true, Client.all().get(1).equals(secondClient));
  }

  @Test
  public void find_returnsClientWithSameId_secondClient() {
    Client firstClient = new Client("Vain Valerie", "206-XXX-XXXX", 1);
    firstClient.save();
    Client secondClient = new Client("Narcissistic Nate", "206-XXX-XXXX", 7);
    secondClient.save();
    assertEquals(Client.find(secondClient.getId()), secondClient);
  }

  @Test
  public void save_savesStylistIdIntoDB_true() {
    Stylist testStylist = new Stylist("Henry", "Brazilian Blowouts");
    testStylist.save();
    Client testClient = new Client("Vain Valerie", "206-XXX-XXXX", testStylist.getId());
    testClient.save();
    Client savedClient = Client.find(testClient.getId());
    assertEquals(savedClient.getStylistId(), testStylist.getId());
  }

  @Test
  public void getClients_retrievesAllClientsFromDatabase_clientsList() {
    Stylist testStylist = new Stylist("Henry", "Brazilian Blowouts");
    testStylist.save();
    Client firstClient = new Client("Vain Valerie", "206-XXX-XXXX", testStylist.getId());
    firstClient.save();
    Client secondClient = new Client("Narcissistic Nate", "206-XXX-XXXX", testStylist.getId());
    secondClient.save();
    Client[] clients = new Client[] { firstClient, secondClient };
    assertTrue(testStylist.getClients().containsAll(Arrays.asList(clients)));
  }
}
