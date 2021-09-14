package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;
import java.util.Queue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import united.DisplayInputField;
import united.OperatorUtility;

/**
 * Test Cases for Operator Utility Class.
 * @author Team02
 *
 */
class OperatorUtilityTest
{

  private Queue<Integer> operators;
  
  @Test
  void testConstructor()
  {
    new OperatorUtility();
  }
  
  @BeforeEach
  void intialize() {
    operators = new LinkedList<>();
  }
  
  @Test
  void testSetAddOperator()
  {
    OperatorUtility.setOperator("+", operators);
    assertEquals(Integer.valueOf(DisplayInputField.ADD), operators.peek());
  }

  @Test
  void testSetSubtractOperator()
  {
    OperatorUtility.setOperator("-", operators);
    assertEquals(Integer.valueOf(DisplayInputField.SUBTRACT), operators.peek());
    OperatorUtility.setOperator("\u2212", operators);
    assertEquals(Integer.valueOf(DisplayInputField.SUBTRACT), operators.peek());
  }

  @Test
  void testSetMultiplyOperator()
  {
    OperatorUtility.setOperator("*", operators);
    assertEquals(Integer.valueOf(DisplayInputField.MULTI), operators.peek());
    OperatorUtility.setOperator("\u00d7", operators);
    assertEquals(Integer.valueOf(DisplayInputField.MULTI), operators.peek());
  }

  @Test
  void testSetDivideOperator()
  {
    OperatorUtility.setOperator("/", operators);
    assertEquals(Integer.valueOf(DisplayInputField.DIVIDE), operators.peek());
    OperatorUtility.setOperator("\u00f7", operators);
    assertEquals(Integer.valueOf(DisplayInputField.DIVIDE), operators.peek());
  }

  @Test
  void testSetOperatorMultipleOperators()
  {
    OperatorUtility.setOperator("/", operators);
    OperatorUtility.setOperator("+", operators);
    OperatorUtility.setOperator("-", operators);
    assertEquals(3, operators.size());
    assertEquals(Integer.valueOf(DisplayInputField.DIVIDE), operators.peek());
  }

  @Test
  void testSetWrongOperators()
  {
    OperatorUtility.setOperator("%", operators);
    assertEquals(0, operators.size());
  }
  
  @Test
  void testClickedPlusButton()
  {
    assertTrue(OperatorUtility.isOperator("+", OperatorUtility.OP_BUTTONS));
  }

  @Test
  void testClickedMinusButton()
  {
    assertTrue(OperatorUtility.isOperator("\u2212", OperatorUtility.OP_BUTTONS));
  }

  @Test
  void testClickedMultiplyButton()
  {
    assertTrue(OperatorUtility.isOperator("\u00d7", OperatorUtility.OP_BUTTONS));
  }

  @Test
  void testClickedDivideButton()
  {
    assertTrue(OperatorUtility.isOperator("\u00f7", OperatorUtility.OP_BUTTONS));
  }

  @Test
  void testClickedWrongOperatorButton()
  {
    assertFalse(OperatorUtility.isOperator("%", OperatorUtility.OP_BUTTONS));
  }
  
  @Test
  void testDivideKeyboard()
  {
    assertTrue(OperatorUtility.isOperator("/", OperatorUtility.OP_KEYBOARD));
  }
  
  @Test
  void testMultiKeyboard()
  {
    assertTrue(OperatorUtility.isOperator("*", OperatorUtility.OP_KEYBOARD));
  }
  
  @Test
  void testMinusKeyboard()
  {
    assertTrue(OperatorUtility.isOperator("-", OperatorUtility.OP_KEYBOARD));
  }

}
