package core;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.google.common.base.Preconditions;
import java.util.concurrent.TimeUnit;

public abstract class HelperBase {
    protected static WebDriver driver;
    private boolean acceptNextAlert = true;

    public HelperBase(WebDriver driver) {
        this.driver = driver;
        check();
    }

    protected HelperBase() {
    }

    protected abstract void check();

    protected void type(String text, By locator) {
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(text);
    }

    protected void click(By locator) {
        driver.findElement(locator).click();
    }

    protected static boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    protected boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    protected String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }

    public void moveToElement(WebElement webElement){
        new Actions(driver).moveToElement(webElement).build().perform();
    }

    public boolean explicitWait(final ExpectedCondition<?> condition, long maxCheckTimeInSeconds, long millisecondsBetweenChecks) {
        Preconditions.checkNotNull(condition, "Condition must be not null");
        Preconditions.checkArgument(TimeUnit.MINUTES.toSeconds(3) > maxCheckTimeInSeconds, "Max check time in seconds should be less than 3 minutes");
        checkConditionTimeouts(maxCheckTimeInSeconds, millisecondsBetweenChecks);
        try {
            // сбрасываем ожидания в 0
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            // создаем эксплицитное ожидание
            WebDriverWait explicitWait = new WebDriverWait(driver, maxCheckTimeInSeconds, millisecondsBetweenChecks);
            // проверяем его
            explicitWait.until(condition);
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            // при любом результате восстанавливаем значение имплицитного ожидания по умолчанию
            if (driver != null) {
                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            } else {
                throw new IllegalArgumentException("Driver shouldnt be null");
            }        }
    }

    private void checkConditionTimeouts(long maxCheckTimeInSeconds, long millisecondsBetweenChecks) {
        Preconditions.checkState(maxCheckTimeInSeconds > 0, "maximum check time in seconds must be not 0");
        Preconditions.checkState(millisecondsBetweenChecks > 0, "milliseconds count between checks must be not 0");
        Preconditions.checkState(millisecondsBetweenChecks < (maxCheckTimeInSeconds * 1000),
                "Millis between checks must be less than max seconds to wait");
    }
}

