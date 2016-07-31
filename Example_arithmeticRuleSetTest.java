package curam.test.cerrules.exercise;

import curam.creole.calculator.CREOLETestHelper;
import curam.creole.execution.session.Session;
import curam.creole.ruleclass.Example_arithmetic.impl.ArithmeticExampleRuleClass;
import curam.creole.ruleclass.Example_arithmetic.impl.ArithmeticExampleRuleClass_Factory;
import curam.test.framework.CuramServerTest;

/**
 * Class for doing the Arithmetic Rule Sets.
 * 
 * 
 */
public class Example_arithmeticRuleSetTest extends CuramServerTest {

  public Example_arithmeticRuleSetTest(final String arg0) {

    super(arg0);

  }

  /**
   * Testing an Arithmetic RuleSet
   */
  public void testArithmeticRuleSetTest() {

    final Session session = getSession();
    final ArithmeticExampleRuleClass arithmeticExampleRuleClass =
      ArithmeticExampleRuleClass_Factory.getFactory().newInstance(session);

    CREOLETestHelper.assertEquals(5, arithmeticExampleRuleClass
      .addANumberToAnother().getValue());

    CREOLETestHelper.assertEquals(20, arithmeticExampleRuleClass
      .chainedArithmetic().getValue());

    CREOLETestHelper.assertEquals(1.5, arithmeticExampleRuleClass
      .divideANumbersByAnother().getValue());

    CREOLETestHelper.assertEquals(6, arithmeticExampleRuleClass
      .multiplyANumberByAnother().getValue());

    CREOLETestHelper.assertEquals(20, arithmeticExampleRuleClass
      .chainedArithmetic().getValue());

    CREOLETestHelper.assertEquals(4.7, arithmeticExampleRuleClass
      .roundedAddition().getValue());

    CREOLETestHelper.assertEquals(0.667, arithmeticExampleRuleClass
      .roundedDivision().getValue());

  }
}
