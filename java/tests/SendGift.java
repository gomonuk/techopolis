package tests;

import core.*;
import model.TestBot;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.concurrent.TimeUnit;

/*
1.	В строке поиска по подаркам ввести запрос
2.	Ожидаем появление реультата,  должны отобразиться подарки с параметром query равным нашему запросу.
3.	Кликнуть по первому подарку
4.	Проверить, что открылось окно с выбором получателя подарка
5.	Кликнуть по иконке друга-получателя
6.	Проверить, что открылось окно с сообщением "Подарок отправлен"
7.	Залогиниться получателем(bot2)
8.	Кликнуть "Оповещения"
9.	Принять подарок от bot1
*/

public class SendGift extends TestBase {
    private static final String SEARCH_QUERY = "пасха";
    private static final String bot_name = "QA18testbot23";

    @Test
    public void testSendGift() {
        LoginMainPage loginMainPage = new LoginMainPage(driver);
        UserMainPage userMainPage = loginMainPage.doLogin(new TestBot("TechnopolisOpen18Bot1", "technopolis18")); //Логинимся
        GiftsMainPage giftsMainPage = userMainPage.clickGiftsOnToolbar(); //Переходим в подарки

        giftsMainPage.typeSearchQuery(SEARCH_QUERY);

        Assert.assertTrue("Не дождались поисковой выдачи.",
                giftsMainPage.isNotEmptySearch());

        Assert.assertTrue("Не представлен подарок соответствующий поисковому запросу",
                giftsMainPage.isValidSearchQuery(SEARCH_QUERY));

        giftsMainPage.clickFirstGifts();

        Assert.assertTrue("Не обнаружено окно выбора получателя.",
                giftsMainPage.isPresentFriendSelectionWindow());


        List<SendGiftFriendCardWrapper> wrapped = giftsMainPage.getWrappedRecipients();
        for (SendGiftFriendCardWrapper w : wrapped ){
            if (w.isBotName(bot_name)){
                w.clickToFriendCard();
                break;
            }
        }

        Assert.assertTrue("Не дождались GiftSentWindowPresent", giftsMainPage.isGiftSentWindowPresent());

        giftsMainPage.clickCloseGiftSentWindow();
        giftsMainPage.doLogout();

        loginMainPage.check();
        loginMainPage.doLogin(new TestBot("QA18testbot23", "QA18testbot"));

        userMainPage.clickNotifications();
        Assert.assertTrue("Не дождались NOTIFICATIONS_LAYER_CONTENT", userMainPage.isNotificationLayerContent());

        Assert.assertTrue("Не дождались ACCEPT_BUTTOM", userMainPage.isAcceptButtom());
        userMainPage.clickAccept();

        Assert.assertTrue("Не дождались NOTIFICATIONS_CLOSE", userMainPage.isNotificationCloseButtom());
        userMainPage.closeNotifications();

        userMainPage.clickNotifications();

    }
}


