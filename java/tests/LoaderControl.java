package tests;

import core.*;
import model.TestBot;
import org.junit.Test;
import org.junit.Assert;
import java.util.concurrent.TimeUnit;
// 1. В поле поиска вводим запрос который должен отобразить много подарков.
// 2. Ожидаем появление реультата,  должны отобразиться подарки с параметром query равным нашему запросу.
// 3. Скроллим вниз.
// 4. Ожидаем появления значка подгрузки.


public class LoaderControl extends TestBase {
    private final static String SEARCH_QUERY = "пасха";

    @Test
    public void testWrongInput() {
        LoginMainPage loginMainPage = new LoginMainPage(driver);
        UserMainPage userMainPage = loginMainPage.doLogin(new TestBot("TechnopolisOpen18Bot1", "technopolis18")); //Логинимся
        GiftsMainPage giftsMainPage = userMainPage.clickGiftsOnToolbar(); //Переходим в подарки

        giftsMainPage.typeSearchQuery(SEARCH_QUERY);

        Assert.assertTrue("Не дождались поисковой выдачи.",
                giftsMainPage.isNotEmptySearch());

        Assert.assertTrue("Не представлен подарок соответствующий поисковому запросу",
                giftsMainPage.isValidSearchQuery(SEARCH_QUERY)); // Проверяем соответсвие поисковому запросу.

        giftsMainPage.scrollDown(); // Скроллим вниз.

        // Каждую миллисикунду проверяем появление значка прогрузки.
        Assert.assertTrue("Не дождались лоадинг бара", giftsMainPage.isLoadingBarVisibility());

    }
}

