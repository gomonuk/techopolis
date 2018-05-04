package tests;

import core.*;
import model.TestBot;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class DeleteSentGifts extends TestBase {

    @Before
    public void deleteReceivedGifts() {
        LoginMainPage loginMainPage = new LoginMainPage(driver);
        UserMainPage userMainPage = loginMainPage.doLogin(new TestBot("TechnopolisOpen18Bot1", "technopolis18")); //Логинимся
        GiftsMainPage giftsMainPage = userMainPage.clickGiftsOnToolbar(); //Переходим в подарки

        giftsMainPage.clickMyGifts();
        GiftsReceivedPage giftsReceivedPage = giftsMainPage.clickMyReceivedGifts();

        Assert.assertTrue("Не обнаруженны карды подарков.",
                giftsReceivedPage.isImageOfGiftsPresent());


        // До тех пор, пока отображаются подарки, создаем список вебелементов, удаляем первый подарок в списке.
        while(giftsReceivedPage.isImageOfGiftsPresent()){
            List<MyGiftsWrapper> wrapped = GiftsReceivedPage.getReceivedGiftsWrapped();
            wrapped.get(0).deleteGift();
        }
    }

    @Test
    public void test() {
        System.out.println("test");

    }
}


