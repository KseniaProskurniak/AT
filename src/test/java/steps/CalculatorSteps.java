package test.java.steps;

import cucumber.api.java.ru.Если;
import redmine.managers.Context;
import testing_explain.model.testing_explan_model.Calculator;

public class CalculatorSteps {

    @Если("В калькуляторе {string} числа {int} и {int} и сохранить результат в переменную {string}")
    public void calculateSum(String operation, int first, int second, String resultStashId) {
        Integer result;
        switch (operation) {
            case "сложить":
                result = Calculator.sum(first, second);
                Context.put(resultStashId, result);
                break;
            case "разделить":
                result = Calculator.divide(first, second);
                Context.put(resultStashId, result);
                break;
            default:
                throw new IllegalArgumentException("Неизвестная операция для чисел: " + operation);
        }
    }

}
