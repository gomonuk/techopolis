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
        LoginMainPage loginMainPage = new LoginMainPage(driver);
        UserMainPage userMainPage = loginMainPage.doLogin(new TestBot("TechnopolisOpen18Bot1", "technopolis18")); //Логинимся
        GiftsMainPage giftsMainPage = userMainPage.clickGiftsOnToolbar(); //Переходим в подарки

        giftsMainPage.typeSearchQuery(SEARCH_QUERY);

        Assert.assertTrue("Не обнаружено ожидаемой ошибки поиска.",
                giftsMainPage.isEmptySearch());

    }
}
