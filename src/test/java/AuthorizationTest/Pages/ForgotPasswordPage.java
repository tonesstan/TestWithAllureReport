package AuthorizationTest.Pages;

import java.time.Duration;

import static AuthorizationTest.Elements.ForgotPasswordPage.*;
import static com.codeborne.selenide.Condition.exist;

public class ForgotPasswordPage {

    public static void waitForPageLoad() {forgotPasswordTitle.shouldBe(exist);}

    public static void enterEmail(String eMail) {email.setValue(eMail);}

    public static void clickRestoreButton() {restoreButton.click();}

    public static void waitForRequestConfirmation() {passwordChange.shouldBe(exist, Duration.ofSeconds(8));}


    public static boolean requestCheck() {return passwordChange.isDisplayed();}

}
