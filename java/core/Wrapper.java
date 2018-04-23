package core;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class Wrapper {

    private WebElement element;
    private WebDriver driver;
    private static final By CLOSE = By.xpath(".//a[@title='Удалить' and contains(@class, 'gift-card_ac')]");
    private static final By DELETE = By.xpath(".//input[@id='hook_FormButton_button_remove_confirm']");

    private static final By CAPTION = By.xpath(".//div[contains(@style, 'background-image')]");

    public Wrapper(WebElement element, WebDriver driver) {
        this.driver = driver;
        this.element = element;
    }

    public WebElement returnElement()  {
        return element;
    }

    public WebDriver returnDriver()  {
        return driver;
    }

/*    public void deleteGift() throws InterruptedException {
        new Actions(driver).moveToElement(element.findElement(By.xpath(".//div[@class='caption']"))).build().perform();

*//*        Assert.assertTrue("Не обнаружено кнопки удаления. ", element.findElement(CLOSE).isDisplayed());
        (new WebDriverWait(driver, 30))
                .until(ExpectedConditions.visibilityOfElementLocated(CLOSE));*//*

        TimeUnit.SECONDS.sleep(3);
        element.findElement(CLOSE).click();
        TimeUnit.SECONDS.sleep(3);
        new Actions(driver).moveToElement(element.findElement(By.xpath(".//div[@class='formButtonContainer']"))).build().perform();

        element.findElement(DELETE).isDisplayed();
        element.findElement(DELETE).click();
    }*/

    //Трансфорсмер
    public static class Transformer {

        public static List<Wrapper> wrap(List<WebElement> elements, WebDriver driver) {
            if (elements.isEmpty()) {
                return Collections.<Wrapper>emptyList();
            }
            List<Wrapper> comments = new ArrayList<Wrapper>();
            for (WebElement comment : elements) {
                comments.add(new Wrapper(comment, driver));
            }
            return comments;
        }
    }
}