package steps;

import cucumber.api.java.ru.И;
import org.testng.Assert;
import redmine.managers.Context;

public class AssertionSteps {

    @И("значение переменной {string} равно {int}")
    public void assertResult(String stashId, Integer expectedResult) {
        Integer result = Context.get(stashId, Integer.class);
        Assert.assertEquals(result, expectedResult);
    }
}
