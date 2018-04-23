package tests;

import core.*;
import model.TestBot;
import org.junit.Test;
import org.junit.Assert;

// 1. В поле поиска вводим запрос который должен отобразить много подарков.
// 2. Ожидаем появление реультата,  должны отобразиться подарки с параметром query равным нашему запросу.
// 3. Скроллим вниз.
// 4. Ожидаем появления значка подгрузки.


public class LoaderControl extends TestBase {
    private static String SEARCH_QUERY = "пасха";

    @Test
    public void testWrongInput() {
        new LoginMainPage(driver).doLogin(new TestBot("TechnopolisOpen18Bot1", "technopolis18"));
        //Переходим в подарки, и возвращаем экземпляр класса GiftsMainPage.
        final GiftsMainPage giftsMainPage = new UserMainPage(driver).clickGiftsOnToolbar();
        // Вводим поисковый запрос, и получаем экземпляр промиса.
        SearchPromise searchPromise = giftsMainPage.typeSearchQuery(SEARCH_QUERY);

        Assert.assertTrue("Не обнаружено ожидаемого результата поиска.",
                searchPromise.isNotEmptySearch());

        Assert.assertTrue("Не представлен подарок соответствующий поисковому запросу",
                giftsMainPage.isValidSearchQuery(SEARCH_QUERY)); // Проверяем соответсвие поисковому запросу.

        giftsMainPage.scrollDown(); // Скроллим вниз.
        giftsMainPage.checkLoadingBarExplicit(); // Каждую миллисикунду проверяем появление значка прогрузки.
        
    }
}

