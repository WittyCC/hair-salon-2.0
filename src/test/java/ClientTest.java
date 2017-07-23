import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class ClientTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void monster_instantiatesCorrectly_true() {
    Client testClient = new Client("Bubbles", 1);
    assertEquals(true, testClient instanceof Client);
  }

}
