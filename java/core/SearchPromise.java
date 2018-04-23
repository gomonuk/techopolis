package core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SearchPromise extends HelperBase {

    private static final By EMPTY_SEARCH = By.xpath(".//div[@class = 'stub-empty __gifts-search']");
    private static final By NOT_EMPTY_SEARCH = By.xpath(".//div[@class='loader-container h-mod']");

    public SearchPromise(WebDriver driver) {
        super(driver);
    }

    protected void check() {

    }

    public boolean isEmptySearch() {
        return explicitWait(ExpectedConditions.visibilityOfElementLocated(EMPTY_SEARCH), 10, 50);

    }

    public boolean isNotEmptySearch() {
        return explicitWait(ExpectedConditions.visibilityOfElementLocated(NOT_EMPTY_SEARCH), 10, 50);

    }




}
