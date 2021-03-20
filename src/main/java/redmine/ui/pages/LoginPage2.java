package redmine.ui.pages;

import lombok.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@Data
public class LoginPage2 extends AbstractPage {
    private WebDriver webDriver;

    public LoginPage2(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void login(String username, String password) {
        webDriver.get("http://edu-at.dfu.i-teco.ru/login");
        WebElement loginElement = webDriver.findElement(By.xpath("//input[@id='username']"));
        WebElement passwordElement = webDriver.findElement(By.xpath("//input[@id='password']"));
        WebElement submitButton = webDriver.findElement(By.xpath("//input[@id='login-submit']"));
        loginElement.sendKeys(username);
        passwordElement.sendKeys(password);
        submitButton.click();
    }
}
