package testing_explain.model;

import testing_explain.model.testing_explan_model.Calculator;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CalculatorTest {

    @DataProvider(name = "positiveProvider", parallel = true)
    public static Object[][] positiveProvider() {
        return new Object[][]{
                {6, 2, 3},
                {8, 2, 4},
                {6, 1, 6},
                {0, 9, 0}
        };
    }

    @DataProvider(name = "negativeProvider", parallel = true)
    public static Object[][] negativeProvider() {
        return new Object[][]
                {       {6, 0},
                        {0, 0}
                };
    }

    @Test(dataProvider = "positiveProvider")
    public void positiveCalculationTest(int number1, int number2, int expectedResult) {
        int actualResult = Calculator.divide(number1, number2);

        Assert.assertEquals(actualResult, expectedResult);
    }

    @Test(dataProvider = "negativeProvider", expectedExceptions = {ArithmeticException.class})
    public void negativeCalculationTest(int number1, int number2) {
        Calculator.divide(number1, number2);
    }

}