package tests;

import core.*;
import model.TestBot;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import java.util.List;
import java.util.concurrent.TimeUnit;

// У получателя надо удалить все подарки.
//  1. В строке поиска по подаркам ввести "8 марта"
//  2. Удостоверится что выведенные подаки соответствуют теме запроса
//  3. Кликнуть по нужному подарку
//  4. Проверить что открылось окно с выбром получателя подарка
//  5. Кликнуть по иконке друга ("Подарить")
//  6. Проверить что открылось окно с сообщением "Подарок отправлен"

//  7. Вылогинится и залогиниться получателем(bot2)
//  8. Кликнуть Оповещения
//  9. Принять подарок от bot1
// 10. Проверить наличие подарка на аватарке получателя
// 11. Проверить наличие подарка в "Мои подарки"


public class SendGift extends TestBase {

    @Before
    public void deleteReceivedGifts() throws InterruptedException {
        new LoginMainPage(driver).doLogin(new TestBot("TechnopolisOpen18Bot1", "technopolis18")); //Логинимся
        GiftsMainPage giftsMainPage = new UserMainPage(driver).clickGiftsOnToolbar();
        giftsMainPage.clickMyGifts();
        GiftsReceivedPage giftsReceivedPage = giftsMainPage.clickMyReceivedGifts();

        //TimeUnit.SECONDS.sleep(10);
        Assert.assertTrue("Не представлен подарок соответствующий поисковому запросу",
                giftsReceivedPage.isImageOfGiftsPresent());

        List<Wrapper> wrapped = GiftsReceivedPage.getReceivedGiftsWrapped();
        for (Wrapper w : wrapped) {
            System.out.println("Итерация началась.");
            giftsReceivedPage.deleteGift(w);
        }
    }

    @Test
    public void testGroupCreation() {
        //new UserMainPage(driver).clickGiftsOnToolbar(); //Переходим в подарки



    }
}


