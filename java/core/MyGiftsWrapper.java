package core;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MyGiftsWrapper extends HelperBase {

    private WebElement element;
    private WebDriver driver;

    private static final By DELETE = By.xpath(".//input[@id='hook_FormButton_button_remove_confirm']");
    private static final By CLOSE = By.xpath(".//a[@title='Удалить' and contains(@class, 'gift-card_ac')]");

    private MyGiftsWrapper(WebElement element, WebDriver driver) {
        this.element = element;
        this.driver = driver;
    }

    @Override
    protected void check() {
    }

    public void deleteGift() {

        // Нужно для отображения CLOSE
        moveToElement(element);

        Assert.assertTrue("Не дождались CLOSE",explicitWait(ExpectedConditions.visibilityOfElementLocated(CLOSE),
                10, 1));

        element.findElement(CLOSE).click();

        Assert.assertTrue("Не дождались DELETE",explicitWait(ExpectedConditions.elementToBeClickable(DELETE),
                10, 1));

        //Необходимо выполнять именно от драйвера(т.е. от корня), т.к. окно с подтверждением не принадлежит element
        driver.findElement(DELETE).click();

        //Необоходимо обновить страницу, т.к после удаления подарка, его изображение все ещё остается на странице.
        driver.navigate().refresh();

        }

    public static List<MyGiftsWrapper> wrap(List<WebElement> elements, WebDriver driver) {
        if (elements.isEmpty()) {
            return Collections.emptyList();
        }

        List<MyGiftsWrapper> wrapped_elements = new ArrayList<MyGiftsWrapper>();
        for (WebElement wrapped_elem : elements) {
            wrapped_elements.add(new MyGiftsWrapper(wrapped_elem, driver));
        }
        return wrapped_elements;
    }
}
