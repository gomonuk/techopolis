package core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class UserMainPage extends HelperBase{
    public UserMainPage(WebDriver driver) {
        super(driver);
    }
    private static final By USER_GROUPS = By.xpath(".//*[contains(@data-l,'userAltGroup')]");
    private static final By USER_GIFTS = By.xpath(".//*[contains(@data-l,'t,giftsFront')]");
    private static final By USER_AVATAR = By.xpath(".//div[@class='entity-avatar']//div[contains(@class,'stub-img')]");

    protected void check() {
        List<WebElement> linkElements = new ArrayList<WebElement>();
        linkElements.add(driver.findElement(USER_GROUPS));
        linkElements.add(driver.findElement(USER_GIFTS));
        linkElements.add(driver.findElement(USER_AVATAR));

        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfAllElements(linkElements));
    }

    public GiftsMainPage clickGiftsOnToolbar() {
        click(USER_GIFTS);
        return new GiftsMainPage(driver);
    }
}