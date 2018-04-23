package tests;

import core.*;
import model.TestBot;
import org.junit.Test;

import java.util.List;


// 1. Удаляем все оповещения
// 2. Удаляем все принятые подарки.

public class Delete extends TestBase {
    private static final String SEARCH_QUERY = "f,hfrflf,hf ";

    @Test
    public void testWrongInput()  throws Exception{
        new LoginMainPage(driver).doLogin(new TestBot("TechnopolisOpen18Bot1", "technopolis18")); //Логинимся
        GiftsMainPage giftsMainPage = new UserMainPage(driver).clickGiftsOnToolbar(); //Переходим в подарки, и возвращаем экземпляр класса GiftsMainPage.

        giftsMainPage.clickNotification();
        List<Wrapper> wrapped = giftsMainPage.getReceivedGiftsWrapped();

        for (Wrapper w : wrapped) {
            System.out.println("1");
        }


    }
}
//написать каменты
//написать каменты к методам
//Переделать только на подарки