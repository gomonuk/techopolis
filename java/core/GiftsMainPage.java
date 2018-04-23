package core;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.*;



public class GiftsMainPage extends HelperBase {
    //check base elements
    private static final By TOP_PANEL = By.id("topPanel");
    private static final By LEFT_PANEL = By.id("hook_Block_GiftsFrontSidebar");
    private static final By MIDDLE_PANEL = By.id("hook_Block_GiftsFrontMRB");

    //Loadind control
    private static final By SEARCH_INPUT = By.xpath(".//input[@id = 'gf-search-input']");
    private static final By LOADING_BAR = By.xpath(".//div[@class='link-show-more_loading']");

    //Notifications
    private static final By NOTIFICATION = By.xpath(".//div[@id='hook_Block_NotificationsToolbarButton']");
    private static final By NOTIFICATIONS = By.xpath(".//div[@id='ntf_layer_content_inner']/div");

    //Received Gifts
    private static final By MY_GIFTS = By.xpath(".//a[@href='/gifts/my']");
    private static final By MY_RECEIVED_GIFTS = By.xpath(".//a[@href='/gifts/received']");
    private static final By RECEIVED_GIFTS = By.xpath(".//div[@class = 'ugrid_cnt']//div[contains(@class, 'gift-card-wide')]");

    //logout
    private static final By TOOLBAR_DROPDOWN = By.xpath(".//div[@class='ucard-mini toolbar_ucard']");
    private static final By LOGOUT = By.xpath(".//a[@data-l='t,logoutCurrentUser']");
    private static final By CONFIRM = By.xpath(".//input[@data-l='t,confirm']");

    //trash
    private static final By GIFT_ID = By.xpath(".//div[@data-pid='552074627201']");
    private static final By FRIEND_SELECTION_WINDOW = By.xpath(".//div[@class='modal-new_hld']");
    private static final By FIRST_GIFT = By.xpath(".//div[@data-pid='552074627201']");
    private static final By ICON_BOT_FREND = By.xpath(".//div[text()='QA18testbot23 QA18testbot23']/../..");

    private static final By GIFT_SENT_WINDOW = By.xpath(".//div[@class='pf ']");
    private static final By CLOSE_GIFT_SENT_WINDOW = By.xpath(".//a[@id='nohook_modal_close']");




    public GiftsMainPage(WebDriver driver) {
        super(driver);
    }

    protected void check() {
        List<WebElement> linkElements = new ArrayList<WebElement>();
        linkElements.add(driver.findElement(TOP_PANEL));
        linkElements.add(driver.findElement(LEFT_PANEL));
        linkElements.add(driver.findElement(MIDDLE_PANEL));

        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfAllElements(linkElements));

    }
    public List<Wrapper> getReceivedGiftsWrapped() {
        if (isElementPresent(RECEIVED_GIFTS)) {
            return Wrapper.Transformer.wrap(driver.findElements(RECEIVED_GIFTS), driver);
        }

        return Collections.emptyList();
    }

    public void scrollDown() {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,25000)", "");


    }

    public SearchPromise typeSearchQuery(String query) {
        type(query + " ", SEARCH_INPUT);
        return new SearchPromise(driver);
    }


    public boolean isValidSearchQuery(String query) {
        By SEARCH_QUERY_IN_JSON = By.xpath(String.format(".//div[@class='ugrid_i soh-s posR'][1]/div[contains(@data-seen-params, '%s')]", query));
        return explicitWait(ExpectedConditions.visibilityOfElementLocated(SEARCH_QUERY_IN_JSON), 5, 50);

    }

    public void checkLoadingBarExplicit() {
        Assert.assertTrue("Не дождались лоадинг бара",
                explicitWait(ExpectedConditions.visibilityOfElementLocated(LOADING_BAR), 10, 1));

    }


    public void checkGiftbyId() {
        (new WebDriverWait(driver, 30))
                .until(ExpectedConditions.visibilityOfElementLocated(GIFT_ID));

    }

    public void checkFriendSelectionWindow() {
        (new WebDriverWait(driver, 30))
                .until(ExpectedConditions.visibilityOfElementLocated(FRIEND_SELECTION_WINDOW));

    }

    public void checkGiftSentWindow() {
        driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@class='modal-new_payment-frame']")));
        (new WebDriverWait(driver, 30))
                .until(ExpectedConditions.visibilityOfElementLocated(GIFT_SENT_WINDOW));

    }

    public void checkLocator(By locator) {
        (new WebDriverWait(driver, 30))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));

    }

    public void clickFirstGifts() {
        click(FIRST_GIFT);
    }

    public void clickFrendIcon() {
        click(ICON_BOT_FREND);
    }

    public void clickMyGifts() {
        click(MY_GIFTS);
    }

    public GiftsReceivedPage clickMyReceivedGifts() {
        click(MY_RECEIVED_GIFTS);
        return new GiftsReceivedPage(driver);
    }


    public void clickNotification() {
        click(NOTIFICATION);
    }

    public void clickCloseGiftSentWindow() {
        driver.switchTo().alert().accept();

        driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@class='modal-new_payment-frame']")));
        click(CLOSE_GIFT_SENT_WINDOW);
    }

    public void doLogout() {
        click(TOOLBAR_DROPDOWN);
        click(LOGOUT);
        click(CONFIRM);
    }


}
