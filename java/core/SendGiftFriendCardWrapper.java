package core;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class SendGiftFriendCardWrapper extends HelperBase{

    private WebElement element;
    private WebDriver driver;

    private static final By FRIEND_CARD = By.xpath(".//div[@class='sel-single']");

    public SendGiftFriendCardWrapper(WebElement element, WebDriver driver) {
        this.driver = driver;
        this.element = element;
    }


    public boolean isBotName(String bot_name)  {
        return explicitWait(ExpectedConditions.textToBePresentInElement(element, bot_name),
                5, 5);
    }

    public void clickToFriendCard(){
        element.findElement(FRIEND_CARD).click();
    }


    public static List<SendGiftFriendCardWrapper> wrap(List<WebElement> elements, WebDriver driver) {
        if (elements.isEmpty()) {
            return Collections.<SendGiftFriendCardWrapper>emptyList();
        }

        List<SendGiftFriendCardWrapper> wrapped_elements = new ArrayList<SendGiftFriendCardWrapper>();
        for (WebElement wrapped_elem : elements) {
            wrapped_elements.add(new SendGiftFriendCardWrapper(wrapped_elem, driver));
        }
        return wrapped_elements;
    }

    @Override
    protected void check() {

    }
}