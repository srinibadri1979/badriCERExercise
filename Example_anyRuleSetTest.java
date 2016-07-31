package curam.test.cerrules.exercise;

import curam.creole.execution.session.Session;
import curam.creole.ruleclass.Example_any.impl.Person;
import curam.creole.ruleclass.Example_any.impl.Person_Factory;
import curam.test.framework.CuramServerTest;
import curam.util.exception.AppException;
import curam.util.exception.InformationalException;
import java.util.ArrayList;
import java.util.List;

/**
 * Test case for testing the "any" expression in CER Rules.
 * ANY is similar to OR in GATES logic
 * 
 * 1) QualifiesforFreeTravelPass - Person must be aged,blind or disabled. If he
 * satisfies any one criteria he will be eligible for Bus Pass. *
 * 
 * 2) QualifiesForChildBenefit -The person must have a child less than 16 years
 * of age. Then he will qualify for Child Benefit
 * 
 * 
 */
public class Example_anyRuleSetTest extends CuramServerTest {

  public Example_anyRuleSetTest(final String arg0) {

    super(arg0);
    // TODO Auto-generated constructor stub
  }

  /**
   * Tests for a scenario where a person qualifies for both the benefits
   * 
   * @throws AppException
   * @throws InformationalException
   */
  public void testQualifiesForBothBenefits() throws AppException,
    InformationalException {

    final Session session = getSession();

    // Create Person Objects - Parent
    final Person parent = Person_Factory.getFactory().newInstance(session);

    parent.age().specifyValue(66);
    parent.isBlind().specifyValue(true);
    parent.isDisabled().specifyValue(true);

    final Person child1 = Person_Factory.getFactory().newInstance(session);
    child1.age().specifyValue(12);
    child1.isBlind().specifyValue(false);
    child1.isDisabled().specifyValue(false);

    final Person child2 = Person_Factory.getFactory().newInstance(session);
    child2.age().specifyValue(12);
    child2.isBlind().specifyValue(false);
    child2.isDisabled().specifyValue(false);

    final List<Person> childrenList = new ArrayList<Person>();
    childrenList.add(child2);
    childrenList.add(child1);

    parent.children().specifyValue(childrenList);

    assertTrue(parent.qualifiesForChildBenefit().getValue());

    assertTrue(parent.qualifiesForFreeTravelPass().getValue());

  }

  /**
   * Tests for a scenario where a person qualifies for both the benefits
   * 
   * @throws AppException
   * @throws InformationalException
   */
  public void testQualifiesForTravelPass() throws AppException,
    InformationalException {

    final Session session = getSession();

    // Create Person Objects - Parent
    final Person person = Person_Factory.getFactory().newInstance(session);

    // If the person is above the age of 65
    person.age().specifyValue(66);

    assertTrue(person.qualifiesForFreeTravelPass().getValue());

  }

  /**
   * Tests for a scenario where a person qualifies for travel pass if he is
   * blind.
   * 
   * @throws AppException
   * @throws InformationalException
   */
  public void testQualifiesForTravelPass1() throws AppException,
    InformationalException {

    final Session session = getSession();

    // Create Person Objects - Parent
    final Person person = Person_Factory.getFactory().newInstance(session);

    // If the person is above the age of 65
    person.isBlind().specifyValue(true);

    assertTrue(person.qualifiesForFreeTravelPass().getValue());

  }

  /**
   * Tests for a scenario where a person qualifies for travel pass if he is
   * is disabled. Similar test cases for blind
   * 
   * @throws AppException
   * @throws InformationalException
   */
  public void testQualifiesForTravelPass2() throws AppException,
    InformationalException {

    final Session session = getSession();

    // Create Person Objects - Parent
    final Person person = Person_Factory.getFactory().newInstance(session);

    // If the person is disabled
    person.isDisabled().specifyValue(true);

    assertTrue(person.qualifiesForFreeTravelPass().getValue());

  }

  /**
   * Tests for a false scenario where the person is not disabled
   * 
   * @throws AppException
   * @throws InformationalException
   */
  public void testQualifiesForTravelPass3() throws AppException,
    InformationalException {

    final Session session = getSession();

    // Create Person Objects - Parent
    final Person person = Person_Factory.getFactory().newInstance(session);

    // Since we are evaluating a false condition the test has to specify
    // all the values for a person (which is a member to check the
    // qualification for Bus Pass).
    // If we don't specify it throws an VALUE_MUST_BE_SPECIFIED. The reason
    // being it won't be able to evaluate the condition unless and until a value
    // is specified.

    person.isBlind().specifyValue(false);

    person.age().specifyValue(25);

    // If the person is disabled
    person.isDisabled().specifyValue(false);

    // Since the person is not blind neither aged nor disabled he is not
    // eligible for a Travel Pass.
    assertFalse(person.qualifiesForFreeTravelPass().getValue());

  }

  /**
   * 
   * @throws AppException
   * @throws InformationalException
   */

  public void testQualifiesForChildBenefit() throws AppException,
    InformationalException {

    final Session session = getSession();

    // Create Person Objects - Parent
    final Person parent = Person_Factory.getFactory().newInstance(session);

    parent.age().specifyValue(25);

    final Person child1 = Person_Factory.getFactory().newInstance(session);
    child1.age().specifyValue(12);

    final Person child2 = Person_Factory.getFactory().newInstance(session);
    child2.age().specifyValue(12);

    final List<Person> childrenList = new ArrayList<Person>();
    childrenList.add(child2);
    childrenList.add(child1);

    parent.children().specifyValue(childrenList);

    // Person has children below the age of 16 and is
    // qualified for child benefit.
    assertTrue(parent.qualifiesForChildBenefit().getValue());

  }

  /**
   * Tests the scenario when a Person does not have any children
   * 
   * @throws AppException
   * @throws InformationalException
   */

  public void testDisQualifiedForChildBenefit() throws AppException,
    InformationalException {

    final Session session = getSession();

    // Create Person Objects - Parent
    final Person parent = Person_Factory.getFactory().newInstance(session);

    parent.age().specifyValue(25);

    final List<Person> childrenList = new ArrayList<Person>();

    parent.children().specifyValue(childrenList);

    // Since the person does not have children he is disqualified from getting
    // Child Benefits
    assertFalse(parent.qualifiesForChildBenefit().getValue());

  }

}
