package core;

import model.TestBot;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class LoginMainPage extends HelperBase{
    private static final By FIELD_EMAIL = By.id("field_email");
    private static final By FIELD_PASSWORD = By.id("field_password");
    private static final By SIGN_IN_BUTTOM = By.xpath(".//input[contains(@data-l,'t,sign_in')]");
    private static final By ANONYM_FEED_MAIN = By.xpath(".//div[@class='anonym-feed_main']");
    private static final By ANONYM_FEED_ASIDE = By.xpath(".//div[@class='anonym-feed_aside']");

    public LoginMainPage(WebDriver driver) {
        super(driver);
    }

    public void check() {
        List<WebElement> linkElements = new ArrayList<WebElement>();
        linkElements.add(driver.findElement(FIELD_EMAIL));
        linkElements.add(driver.findElement(FIELD_PASSWORD));
        linkElements.add(driver.findElement(SIGN_IN_BUTTOM));
        linkElements.add(driver.findElement(ANONYM_FEED_MAIN));
        linkElements.add(driver.findElement(ANONYM_FEED_ASIDE));

        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfAllElements(linkElements));
    }

    public UserMainPage doLogin(TestBot testBot) {
        type(testBot.getLogin(), FIELD_EMAIL);
        type(testBot.getPassword(), FIELD_PASSWORD);
        click(SIGN_IN_BUTTOM);
        return new UserMainPage(driver);
    }
}