package tests;

import core.GiftsMainPage;
import core.LoginMainPage;
import core.TestBase;
import core.UserMainPage;
import model.TestBot;
import org.junit.Test;
import org.openqa.selenium.By;
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


public class OLD_SendGift extends TestBase {
    private static final By CLOSE_GIFT_SENT_WINDOW = By.xpath(".//a[@id='nohook_modal_close']");
    private static final String SEARCH_QUERY = "8 марта ";

    @Test
    public void testGroupCreation() throws Exception {
        LoginMainPage loginMainPage = new LoginMainPage(driver);
        loginMainPage.doLogin(new TestBot("TechnopolisOpen18Bot1", "technopolis18")); //Логинимся
        new UserMainPage(driver).clickGiftsOnToolbar(); //Переходим в подарки

        GiftsMainPage giftsMainPage = new GiftsMainPage(driver);
        giftsMainPage.typeSearchQuery(SEARCH_QUERY); //1. ВВодит строку в поиск
        giftsMainPage.checkGiftbyId(); // 2. Проверяем что в выдаче есть подарок с id 552074627201

        giftsMainPage.clickFirstGifts(); // 3.
        giftsMainPage.checkFriendSelectionWindow(); // 4.
        giftsMainPage.clickFrendIcon(); // 5.
        //iframegiftsMainPage.checkGiftSentWindow(); // 6.
        giftsMainPage.clickCloseGiftSentWindow();

        giftsMainPage.doLogout();

        loginMainPage.doLogin(new TestBot("QA18testbot23", "QA18testbot"));
        giftsMainPage.clickNotification();

        TimeUnit.SECONDS.sleep(10);






    }
}


