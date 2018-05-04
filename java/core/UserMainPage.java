package core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class UserMainPage extends HelperBase{
    public UserMainPage(WebDriver driver) {
        super(driver);
    }

    private static final By USER_GROUPS = By.xpath(".//*[contains(@data-l,'userAltGroup')]");
    private static final By USER_GIFTS = By.xpath(".//*[contains(@data-l,'t,giftsFront')]");
    private static final By USER_AVATAR = By.xpath(".//div[@class='entity-avatar']//div[contains(@class,'stub-img')]");
    private static final By NOTIFICATIONS = By.xpath(".//*[@data-l='t,notifications']");
    private static final By NOTIFICATIONS_LAYER_CONTENT = By.xpath(".//*[@id='hook_Block_NotificationsLayerContent']");
    private static final By ACCEPT_BUTTOM = By.xpath(".//button[@name='ACCEPT'][1]");
    private static final By CLOSE_NOTIFICATIONS_BTN = By.xpath(".//*[@id='ntf_layer_close']");


    protected void check() {
        List<WebElement> linkElements = new ArrayList<WebElement>();
        linkElements.add(driver.findElement(USER_GROUPS));
        linkElements.add(driver.findElement(USER_GIFTS));
        linkElements.add(driver.findElement(USER_AVATAR));

        (new WebDriverWait(driver, 10))
                .until(visibilityOfAllElements(linkElements));
    }

    public GiftsMainPage clickGiftsOnToolbar() {
        click(USER_GIFTS);
        return new GiftsMainPage(driver);
    }

    public void clickNotifications(){
        click(NOTIFICATIONS);
    }

    //Дропдаун - оповещения
    public boolean isNotificationLayerContent(){
        return explicitWait(ExpectedConditions.visibilityOfElementLocated(NOTIFICATIONS_LAYER_CONTENT),
                10, 1);
    }

    //Подтверждение принятия подарка в оповещениях
    public boolean isAcceptButtom(){
        return explicitWait(ExpectedConditions.visibilityOfElementLocated(ACCEPT_BUTTOM),
                10, 1);
    }

    public void clickAccept(){
        click(ACCEPT_BUTTOM);
    }

    public boolean isNotificationCloseButtom(){
        return explicitWait(ExpectedConditions.visibilityOfElementLocated(CLOSE_NOTIFICATIONS_BTN),
                10, 1);
    }
    public void closeNotifications(){
        click(CLOSE_NOTIFICATIONS_BTN);
    }
}