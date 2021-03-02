package testing_explain;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MyFirstTest {

    @BeforeMethod
    public void prepareFixture(){
        System.out.println("1");
    }

    @Test (testName = "Первый тест", description = "Имя нашего первого теста")
    public void myFirstTest()
    {

    }
}
