package tests;

import core.*;
import model.TestBot;
import org.junit.Test;
import org.junit.Assert;


// 1. В поле поиска ввести любую неверную информацию.
// 2. Ожидаем появление реультата.
// 3. Должно появиться сообщение о том, что запрос не дал результатов.

public class WrongInput extends TestBase {
    private static final String SEARCH_QUERY = "f,hfrflf,hf ";

    @Test
    public void testWrongInput()  {
        new LoginMainPage(driver).doLogin(new TestBot("TechnopolisOpen18Bot1", "technopolis18"));
        //Переходим в подарки, и возвращаем экземпляр класса GiftsMainPage.
        GiftsMainPage giftsMainPage = new UserMainPage(driver).clickGiftsOnToolbar();
        // Вводим поисковый запрос, и получаем экземпляр промиса.
        SearchPromise searchPromise = giftsMainPage.typeSearchQuery(SEARCH_QUERY);

        Assert.assertTrue("Не обнаружено ожидаемой ошибки поиска. ", searchPromise.isEmptySearch());
    }
}
//можно ли промис держать в пейдже ?