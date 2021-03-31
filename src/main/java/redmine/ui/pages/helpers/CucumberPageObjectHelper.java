package redmine.ui.pages.helpers;

import lombok.SneakyThrows;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.openqa.selenium.WebElement;
import org.reflections.Reflections;
import redmine.ui.pages.AbstractPage;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Set;

public class CucumberPageObjectHelper {

    @SneakyThrows
    public static WebElement getElementBy(String cucumberPageName, String cucumberFieldName) {
        AbstractPage page = getPageBy(cucumberPageName);
        //   Field foundField = Stream.of(page.getClass().getFields())
        //          .filter(field -> field.isAnnotationPresent(CucumberName.class))
        Field foundField = FieldUtils.getFieldsListWithAnnotation(page.getClass(), CucumberName.class).stream()
                .filter(field -> cucumberFieldName.equals(field.getAnnotation(CucumberName.class).value()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(
                        String.format("Нет аннотации @CucumberName(\"%s\") у поля", cucumberFieldName)));
        foundField.setAccessible(true);
        return (WebElement) foundField.get(page);
    }

    @SneakyThrows
    @SuppressWarnings("unchecked")
    public static List<WebElement> getElementsBy(String cucumberPageName, String cucumberFieldName) {
        AbstractPage page = getPageBy(cucumberPageName);
        Field foundField = FieldUtils.getFieldsListWithAnnotation(page.getClass(), CucumberName.class).stream()
                .filter(field -> cucumberFieldName.equals(field.getAnnotation(CucumberName.class).value()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(
                        String.format("Нет аннотации @CucumberName(\"%s\") у поля", cucumberFieldName)));
        foundField.setAccessible(true);
        return (List<WebElement>) foundField.get(page);
    }

    @SneakyThrows
    @SuppressWarnings("unchecked")
    private static AbstractPage getPageBy(String cucumberPageName) {
        Reflections reflections = new Reflections("redmine.ui.pages");
        Set<Class<?>> allClasses = reflections.getTypesAnnotatedWith(CucumberName.class);

        Class<?> pageClass = allClasses.stream()
                .filter(clazz -> cucumberPageName.equals(clazz.getAnnotation(CucumberName.class).value()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(
                        String.format("Нет аннотации @CucumberName(\"%s\") у класса", cucumberPageName)
                ));
        return Pages.getPage((Class<AbstractPage>) pageClass);
    }

}
