package AuthorizationTest.Pages;


import com.codeborne.selenide.ex.ElementNotFound;

import static AuthorizationTest.Elements.MainPage.*;
import static com.codeborne.selenide.Condition.exist;

public class MainPage {

    public static void waitForPageLoad() {try {logo.shouldBe(exist);} catch (ElementNotFound e) {System.out.println("Authorization failed!");}}

    public static boolean authorizationCheck() {return logo.exists();}

}