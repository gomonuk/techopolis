package core;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Dimension;

public class GiftsReceivedPage extends HelperBase {
    //check base elements
    private static final By TOP_PANEL = By.id("topPanel");
    private static final By LEFT_PANEL = By.id("hook_Block_GiftsFrontSidebar");
    private static final By MIDDLE_PANEL = By.id("hook_Block_GiftsFrontMRB");
    private static final By HOOK_DATA = By.xpath(".//div[@class='hookData']");

    private static final By IMAGES_OF_GIFTS = By.xpath(".//div[contains(@style, 'background-image')]");

    //Received Gifts
    private static final By DELETE = By.xpath(".//input[@id='hook_FormButton_button_remove_confirm']");
    private static final By CLOSE = By.xpath(".//a[@title='Удалить' and contains(@class, 'gift-card_ac')]");
    private static final By MY_GIFTS = By.xpath(".//a[@href='/gifts/my']");
    private static final By MY_RECEIVED_GIFTS = By.xpath(".//a[@href='/gifts/received']");
    private static final By RECEIVED_GIFTS = By.xpath(".//div[@class = 'ugrid_cnt']//div[contains(@class, 'gift-card-wide')]");

    public GiftsReceivedPage(WebDriver driver) {
        super(driver);
    }

/*
   org.openqa.selenium.StaleElementReferenceException: stale element reference: element is not attached to the page document

    at core.GiftsReceivedPage.check(GiftsReceivedPage.java:46)
    at core.HelperBase.<init>(HelperBase.java:17)
    at core.GiftsReceivedPage.<init>(GiftsReceivedPage.java:36)
    at core.GiftsMainPage.clickMyReceivedGifts(GiftsMainPage.java:143)

    */
    protected void check() {
/*        List<WebElement> linkElements = new ArrayList<WebElement>();
        linkElements.add(driver.findElement(TOP_PANEL));
        linkElements.add(driver.findElement(LEFT_PANEL));
        linkElements.add(driver.findElement(MIDDLE_PANEL));

        (new WebDriverWait(driver, 20))
                .until(ExpectedConditions.visibilityOfAllElements(linkElements));*/
    }

    public static List<Wrapper> getReceivedGiftsWrapped() {
        if (isElementPresent(RECEIVED_GIFTS)) {
            return Wrapper.Transformer.wrap(driver.findElements(RECEIVED_GIFTS), driver);
        }
        return Collections.emptyList();
    }

    public void deleteGift(Wrapper wrappedElement) throws InterruptedException {
        WebElement webElement = wrappedElement.returnElement();
        WebDriver webDriver = wrappedElement.returnDriver();
        new Actions(webDriver).moveToElement(webElement.findElement(By.xpath(".//div[@class='caption']"))).build().perform();

        Assert.assertTrue("Не дождались CLOSE",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(CLOSE), 10, 1));

        webElement.findElement(CLOSE).click();

        Assert.assertTrue("Не дождались DELETE",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(DELETE), 10, 1));

        //webDriver.switchTo().activeElement();
        // 1. webElement.sendKeys(Keys.ENTER); //org.openqa.selenium.WebDriverException: unknown error: cannot focus element

        // 2. webElement.findElement(DELETE).click(); // no such element: Unable to locate element: {"method":"xpath","selector":".//input[@id='hook_FormButton_button_remove_confirm']"}

        TimeUnit.SECONDS.sleep(2);

        Actions actions = new Actions(webDriver);
        actions.moveToElement(webElement, 890, 512).click().build().perform();

    }
    public boolean isImageOfGiftsPresent() {
        return explicitWait(ExpectedConditions.visibilityOfElementLocated(IMAGES_OF_GIFTS), 10, 50);
    }

    public void checkLocator(By locator) {
        (new WebDriverWait(driver, 30))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void clickMyGifts() {
        click(MY_GIFTS);
    }

    public void clickMyReceivedGifts() {
        click(MY_RECEIVED_GIFTS);
    }
}
