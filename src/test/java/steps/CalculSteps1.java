package steps;

import cucumber.api.java.ru.Если;
import cucumber.api.java.ru.То;
import org.testng.Assert;
import testing_explain.model.testing_explan_model.Calculator;

public class CalculSteps1 {


    private int result;

    @Если("в калькуляторе сложить числа {int} и {int}")
    public int calcul(int a, int b) {
        return Calculator.sum(a,b);
    }

    @То("cумма будет равна {int}")
    public void assetResult(int expResult){
        Assert.assertEquals(result,expResult);
    }
}
