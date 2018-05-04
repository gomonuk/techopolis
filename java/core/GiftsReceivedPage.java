package core;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Collections;
import java.util.List;

public class GiftsReceivedPage extends HelperBase {
    //check base elements
    private static final By TOP_PANEL = By.id("topPanel");
    private static final By LEFT_PANEL = By.id("hook_Block_GiftsFrontSidebar");
    private static final By MIDDLE_PANEL = By.id("hook_Block_GiftsFrontMRB");

    private static final By IMAGES_OF_GIFTS = By.xpath(".//div[contains(@style, 'background-image')]");
    private static final By RECEIVED_GIFTS = By.xpath(".//div[@class = 'ugrid_cnt']//div[contains(@class, 'gift-card-wide')]");

    public GiftsReceivedPage(WebDriver driver) {
        super(driver);
    }


    protected void check() {
        Assert.assertTrue("Не дождались TOP_PANEL",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(TOP_PANEL),
                        10, 1));
        Assert.assertTrue("Не дождались TOP_PANEL",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(LEFT_PANEL),
                        10, 1));
        Assert.assertTrue("Не дождались TOP_PANEL",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(MIDDLE_PANEL),
                        10, 1));
    }

    public static List<MyGiftsWrapper> getReceivedGiftsWrapped() {
        if (isElementPresent(RECEIVED_GIFTS)) {
            return MyGiftsWrapper.wrap(driver.findElements(RECEIVED_GIFTS), driver);
        }
        return Collections.emptyList();
    }

    public boolean isImageOfGiftsPresent() {
        return explicitWait(ExpectedConditions.visibilityOfElementLocated(IMAGES_OF_GIFTS),
                5, 50);
    }
}
