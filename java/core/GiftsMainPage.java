package core;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class GiftsMainPage extends HelperBase {
    //check base elements
    private static final By TOP_PANEL = By.id("topPanel");
    private static final By LEFT_PANEL = By.id("hook_Block_GiftsFrontSidebar");
    private static final By MIDDLE_PANEL = By.id("hook_Block_GiftsFrontMRB");

    //Loadind control
    private static final By NOT_EMPTY_SEARCH = By.xpath(".//div[@class='loader-container h-mod']");
    private static final By SEARCH_INPUT = By.xpath(".//input[@id = 'gf-search-input']");
    private static final By LOADING_BAR = By.xpath(".//div[@class='link-show-more_loading']");

    //Received Gifts
    private static final By MY_GIFTS = By.xpath(".//a[@href='/gifts/my']");
    private static final By MY_RECEIVED_GIFTS = By.xpath(".//a[@href='/gifts/sent']");
    private static final By RECIPIENTS = By.xpath(".//ul[@class='ugrid_cnt']/li");

    //logout
    private static final By TOOLBAR_DROPDOWN = By.xpath(".//div[@class='ucard-mini toolbar_ucard']");
    private static final By LOGOUT = By.xpath(".//a[@data-l='t,logoutCurrentUser']");
    private static final By CONFIRM = By.xpath(".//input[@data-l='t,confirm']");

    //send gift
    private static final By FRIEND_SELECTION_WINDOW = By.xpath(".//div[@class='modal-new_hld']");
    private static final By FIRST_GIFT = By.xpath(".//div[@class='gift-front_cnt']//div[contains(@class, 'ugrid_i')]");
    private static final By IFRAME_GIFT_SENT_WINDOW = By.xpath(".//iframe[@class='modal-new_payment-frame']");
    private static final By CLOSE_GIFT_SENT_WINDOW = By.id("nohook_modal_close");

    //wrong input
    private static final By EMPTY_SEARCH = By.xpath(".//div[@class = 'stub-empty __gifts-search']");



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

    public List<SendGiftFriendCardWrapper> getWrappedRecipients() {
        if (isElementPresent(RECIPIENTS)) {
            return SendGiftFriendCardWrapper.wrap(driver.findElements(RECIPIENTS), driver);
        }
        return Collections.emptyList();
    }

    public GiftsReceivedPage clickMyReceivedGifts() {
        click(MY_RECEIVED_GIFTS);
        return new GiftsReceivedPage(driver);
    }

    public void scrollDown() {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,25000)", "");
    }

    public void typeSearchQuery(String query) {
        type(query + " ", SEARCH_INPUT);
    }

    public boolean isNotEmptySearch() {
        return explicitWait(ExpectedConditions.visibilityOfElementLocated(NOT_EMPTY_SEARCH),
                10, 50);
    }

    public boolean isGiftSentWindowPresent() {
        return explicitWait(ExpectedConditions.presenceOfElementLocated(CLOSE_GIFT_SENT_WINDOW),
                10, 50);
    }

    public boolean isValidSearchQuery(String query) {
        By SEARCH_QUERY_IN_JSON = By.xpath(String.format(".//div[contains(@class, '__search')]//div[contains(@data-seen-params, '%s')]", query));
        return explicitWait(ExpectedConditions.presenceOfElementLocated(SEARCH_QUERY_IN_JSON),
                10, 50);
    }

    public boolean isLoadingBarVisibility() {
        return explicitWait(ExpectedConditions.visibilityOfElementLocated(LOADING_BAR),
                10, 1);
    }

    public boolean isPresentFriendSelectionWindow() {
        return explicitWait(ExpectedConditions.visibilityOfElementLocated(FRIEND_SELECTION_WINDOW),
                15, 50);
    }

    public void clickFirstGifts() {
        click(FIRST_GIFT);
    }

    public boolean isEmptySearch() {
        return explicitWait(ExpectedConditions.visibilityOfElementLocated(EMPTY_SEARCH),
                10, 50);
    }

    public void clickMyGifts() {
        click(MY_GIFTS);
    }

    public void clickCloseGiftSentWindow() {
        click(CLOSE_GIFT_SENT_WINDOW);
    }

    public void doLogout() {
        Assert.assertTrue("Не обнаружен кликабельный локатор TOOLBAR_DROPDOWN",
                explicitWait(ExpectedConditions.elementToBeClickable(TOOLBAR_DROPDOWN),
                        10, 5));
        click(TOOLBAR_DROPDOWN);

        Assert.assertTrue("Не обнаружен кликабельный локатор LOGOUT",
                explicitWait(ExpectedConditions.elementToBeClickable(LOGOUT),
                        10, 5));
        click(LOGOUT);

        Assert.assertTrue("Не обнаружен кликабельный локатор CONFIRM",
                explicitWait(ExpectedConditions.elementToBeClickable(CONFIRM),
                        10, 5));
        click(CONFIRM);
    }


}
