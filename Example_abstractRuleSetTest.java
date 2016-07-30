package curam.test.cerrules.exercise;

import curam.creole.execution.session.Session;
import curam.creole.ruleclass.Example_abstractRuleSet.impl.Benefit;
import curam.creole.ruleclass.Example_abstractRuleSet.impl.MedicalBenefit;
import curam.creole.ruleclass.Example_abstractRuleSet.impl.MedicalBenefit_Factory;
import curam.creole.ruleclass.Example_abstractRuleSet.impl.NeedyBenefit;
import curam.creole.ruleclass.Example_abstractRuleSet.impl.NeedyBenefit_Factory;
import curam.creole.ruleclass.Example_abstractRuleSet.impl.Person;
import curam.creole.ruleclass.Example_abstractRuleSet.impl.Person_Factory;
import curam.test.framework.CuramServerTest;
import curam.util.exception.AppException;
import curam.util.exception.InformationalException;
import java.util.List;

/**
 * Class to test the marker expression "abstract" in Curam CER Rules.
 * 
 * Based on the rules the understanding is that
 * 
 * 1) If the Person is 'poor' and 'sick' he is eligible for Medical Benefit.
 * 
 * 2) If the Person is 'poor' and is either ('hungry' or 'deprived') he is
 * eligible for Needy Benefit.
 */
public class Example_abstractRuleSetTest extends CuramServerTest {

  public Example_abstractRuleSetTest(final String arg0) {

    super(arg0);
    // TODO Auto-generated constructor stub
  }

  @Override
  protected void setUp() {

    super.setUp();

  }

  @SuppressWarnings(value = {"all" })
  public void testEligibilityForBothTheBenefits() throws AppException,
    InformationalException {

    final Session session = getSession();

    // Create Person Objects
    final Person person1 = Person_Factory.getFactory().newInstance(session);

    person1.isPoor().specifyValue(true);
    person1.isHungry().specifyValue(true);
    person1.isDeprived().specifyValue(true);
    person1.isSick().specifyValue(true);

    final List<? extends Benefit> listOfEligibleBenefits =
      person1.eligibleBenefits().getValue();

    assertTrue(listOfEligibleBenefits.size() == 2);

  }

  /**
   * Test Eligibility for Medical benefits in isolation
   * 
   * @throws AppException
   * @throws InformationalException
   */
  public void testEligibilityForMedicalBenefits() throws AppException,
    InformationalException {

    // Retrieve the Session in which the rules are run
    final Session session = getSession();

    // Create Person Objects
    final Person person1 = Person_Factory.getFactory().newInstance(session);
    person1.isPoor().specifyValue(true);
    person1.isSick().specifyValue(true);

    // These two values must be specified as true or false for checking the
    // eligibility for NeedyBenefit. Otherwise you will get an error message for
    // the
    // Needy Benefit saying "VALUE MUST BE SPECIFIED BEFORE IT IS USED".
    person1.isHungry().specifyValue(false);
    person1.isDeprived().specifyValue(false);

    final MedicalBenefit medicalBenefit =
      MedicalBenefit_Factory.getFactory().newInstance(session, person1);

    final NeedyBenefit needyBenefit =
      NeedyBenefit_Factory.getFactory().newInstance(session, person1);

    final List<? extends Benefit> listOfEligibleBenefits =
      person1.eligibleBenefits().getValue();

    assertTrue(medicalBenefit.isEligible().getValue());

    // Since the Person is 'Poor' and 'Sick' and not '(Hungry' or 'Deprived') he
    // is not eligible for NeedyBenefits.
    assertFalse(needyBenefit.isEligible().getValue());

    assertTrue(listOfEligibleBenefits.size() == 1);

  }

  /**
   * Test Eligibility for Needy benefits in isolation
   * 
   * @throws AppException
   * @throws InformationalException
   */
  public void testEligibilityForNeedyBenefits() throws AppException,
    InformationalException {

    // Retrieve the Session in which the rules are run
    final Session session = getSession();

    // Create Person Objects
    final Person person1 = Person_Factory.getFactory().newInstance(session);
    person1.isPoor().specifyValue(true);
    person1.isSick().specifyValue(false);

    // These two values must be specified as true or false for checking the
    // eligibility for NeedyBenefit. Otherwise you will get an error message for
    // the
    // Needy Benefit saying "VALUE MUST BE SPECIFIED BEFORE IT IS USED".
    person1.isHungry().specifyValue(true);
    person1.isDeprived().specifyValue(true);

    final MedicalBenefit medicalBenefit =
      MedicalBenefit_Factory.getFactory().newInstance(session, person1);

    final NeedyBenefit needyBenefit =
      NeedyBenefit_Factory.getFactory().newInstance(session, person1);

    final List<? extends Benefit> listOfEligibleBenefits =
      person1.eligibleBenefits().getValue();

    assertFalse(medicalBenefit.isEligible().getValue());

    // Since the Person is 'Poor' and 'Sick' and '(Hungry' or 'Deprived') he
    // is eligible for NeedyBenefits.
    assertTrue(needyBenefit.isEligible().getValue());

    assertTrue(listOfEligibleBenefits.size() == 1);

  }
}
