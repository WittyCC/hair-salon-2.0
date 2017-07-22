import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class StylistTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

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
    assertEquals("Brazilian Blowout", testStylist.getExpertise());
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

  @Test
  public void all_returnsAllInstancesOfStylist_true() {
    Stylist firstStylist = new Stylist("Henry", "Brazilian Blowout");
    firstStylist.save();
    Stylist secondStylist = new Stylist("Harriet", "Precision Haircuts");
    secondStylist.save();
    assertEquals(true, Stylist.all().get(0).equals(firstStylist));
    assertEquals(true, Stylist.all().get(1).equals(secondStylist));
  }

}
