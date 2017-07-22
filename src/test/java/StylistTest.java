import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class StylistTest {

  @Test
  public void stylist_instantiatesCorrectly_true() {
    Stylist testStylist = new Stylist("Henry", "Brazilian Blowout");
    assertEquals(true, testStylist instanceof Stylist);
  }

  @Test
  public void getName_stylistInstantiatesWithName_Henry() {
    Stylist testStylist = new Stylist("Henry", "Brazilian Blowout");
    assertEquals("Henry", testStylist.getName());
  }

  @Test
  public void getEmail_stylistInstantiatesWithExpertise_String() {
    Stylist testStylist = new Stylist("Henry", "Brazilian Blowout");
    assertEquals("henry@henry.com", testStylist.getExpertise());
  }

  @Test
  public void equals_returnsTrueIfNameAndExpertiseAreSame_true() {
    Stylist firstStylist = new Stylist("Henry", "Brazilian Blowout");
    Stylist anotherStylist = new Stylist("Henry", "Brazilian Blowout");
    assertTrue(firstStylist.equals(anotherStylist));
  }

  @Test
  public void save_insertsObjectIntoDatabase_Stylist() {
    Stylist testStylist = new Stylist("Henry", "Brazilian Blowout");
    testStylist.save();
    assertTrue(Stylist.all().get(0).equals(testStylist));
  }

}
