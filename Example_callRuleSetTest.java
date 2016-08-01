package curam.test.cerrules.exercise;

import curam.creole.calculator.CREOLETestHelper;
import curam.creole.execution.session.Session;
import curam.creole.ruleclass.Example_callRuleSet.impl.Person;
import curam.creole.ruleclass.Example_callRuleSet.impl.Person_Factory;
import curam.test.framework.CuramServerTest;
import curam.util.exception.AppException;
import curam.util.exception.InformationalException;

/**
 * 
 * Test for testing the Call to a Static method
 * 
 */
public class Example_callRuleSetTest extends CuramServerTest {

  public Example_callRuleSetTest(final String arg0) {

    super(arg0);
    // TODO Auto-generated constructor stub
  }

  public void testCallRuleSetTest() throws AppException,
    InformationalException {

    final Session session = getSession();

    // Create Person Objects
    final Person person = Person_Factory.getFactory().newInstance(session);

    person.name().specifyValue("James");

    person.age().specifyValue(5);

    CREOLETestHelper.assertEquals("Blue", person.favoriteColor().getValue());

  }

}
