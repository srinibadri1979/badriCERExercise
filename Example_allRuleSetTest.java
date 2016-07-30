package curam.test.cerrules.exercise;

import curam.creole.execution.session.Session;
import curam.creole.ruleclass.Example_all.impl.Person;
import curam.creole.ruleclass.Example_all.impl.Person_Factory;
import curam.test.framework.CuramServerTest;
import curam.util.exception.AppException;
import curam.util.exception.InformationalException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to test the "all" in Curam CER Rules.
 * "all" is equivalent of AND condition in GATES logic
 * 
 * Rules are specifying that to be a lone Parent -
 * 
 * 1) The person should not be married
 * 2) The person should have at aleast one dependent child
 */
public class Example_allRuleSetTest extends CuramServerTest {

  public Example_allRuleSetTest(final String arg0) {

    super(arg0);
    // TODO Auto-generated constructor stub
  }

  @Override
  protected void setUp() {

    super.setUp();

  }

  /**
   * Test whether the Person is a Lone Parent
   * 
   * @throws AppException
   * @throws InformationalException
   */
  public void testisALoneParent() throws AppException, InformationalException {

    final Session session = getSession();

    // Create Person Objects - Parent - Father
    final Person father = Person_Factory.getFactory().newInstance(session);
    father.age().specifyValue(25);
    father.isMarried().specifyValue(false);

    // Child is also a Person with age 10 and the child is not married
    final Person child1 = Person_Factory.getFactory().newInstance(session);
    child1.age().specifyValue(10);
    child1.isMarried().specifyValue(false);

    // Child is also a Person with age 3 and the child is not married

    final Person child2 = Person_Factory.getFactory().newInstance(session);
    child2.age().specifyValue(3);
    child2.isMarried().specifyValue(false);

    // Add to the Children list
    final List<Person> childList = new ArrayList<Person>();
    childList.add(child1);
    childList.add(child2);

    father.children().specifyValue(childList);

    //
    assertTrue(father.isLoneParent().getValue());

  }

  /**
   * Test whether the Person is a Lone Parent
   * 
   * @throws AppException
   * @throws InformationalException
   */
  public void testEligibilityifParentIsMarried() throws AppException,
    InformationalException {

    final Session session = getSession();

    // Create Person Objects - Parent - Father
    final Person father = Person_Factory.getFactory().newInstance(session);
    father.age().specifyValue(25);
    father.isMarried().specifyValue(true);

    // Child is also a Person with age 3 and the child is not married

    final Person child2 = Person_Factory.getFactory().newInstance(session);
    child2.age().specifyValue(3);
    child2.isMarried().specifyValue(false);

    // Add to the Children list
    final List<Person> childList = new ArrayList<Person>();
    // childList.add(child1);
    childList.add(child2);

    father.children().specifyValue(childList);

    // Assert false
    assertFalse(father.isLoneParent().getValue());

  }

  /**
   * Test when the person is married
   * 
   * @throws AppException
   * @throws InformationalException
   */
  public void testisALoneParentWithChildAgeLessThan5() throws AppException,
    InformationalException {

    final Session session = getSession();

    // Create Person Objects - Parent - Father
    final Person father = Person_Factory.getFactory().newInstance(session);
    father.age().specifyValue(25);
    father.isMarried().specifyValue(false);

    // Child is also a Person with age 3 and the child is not married

    final Person child2 = Person_Factory.getFactory().newInstance(session);
    child2.age().specifyValue(3);
    child2.isMarried().specifyValue(false);

    // Add to the Children list
    final List<Person> childList = new ArrayList<Person>();
    // childList.add(child1);
    childList.add(child2);

    father.children().specifyValue(childList);

    // As the father is Married he is not eligible for the Lone Parent Benefit
    assertTrue(father.isLoneParent().getValue());

  }

  /**
   * Test when the person is married
   * 
   * @throws AppException
   * @throws InformationalException
   */
  public void testWithNoChildren() throws AppException,
    InformationalException {

    final Session session = getSession();

    // Create Person Objects - Parent - Father
    final Person father = Person_Factory.getFactory().newInstance(session);
    father.age().specifyValue(25);
    father.isMarried().specifyValue(false);

    // Child is also a Person with age 3 and the child is not married

    final Person child2 = Person_Factory.getFactory().newInstance(session);
    child2.age().specifyValue(3);
    child2.isMarried().specifyValue(false);

    // Add to the Children list - (with list is empty)
    final List<Person> childList = new ArrayList<Person>();
    // childList.add(child1);
    // childList.add(child2);

    father.children().specifyValue(childList);

    // As the father is Married he is not eligible for the Lone Parent Benefit
    assertFalse(father.isLoneParent().getValue());

  }

}
