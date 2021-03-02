package testing_explain.Human;

import testing_explain.model.testing_explan_model.Human;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HumanTests {
    Human human;

    @BeforeMethod
    public void prepareFixtures(){
    human = new Human ("Ася", 10);
    }

    @Test
    public void testPositiveHumanAge(){
    human.setAge(15);
    Assert.assertEquals(human.getAge(), 15);
    }

    @Test(expectedExceptions = {IllegalArgumentException.class}, expectedExceptionsMessageRegExp = "Возраст не может быть отрицательным")
    public void testNegativeHumanAge()
    {
        human.setAge(-15);
    }

}