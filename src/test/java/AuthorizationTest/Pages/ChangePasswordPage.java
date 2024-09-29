package AuthorizationTest.Pages;

import java.time.Duration;

import static AuthorizationTest.Elements.ChangePasswordPage.*;
import static com.codeborne.selenide.Condition.exist;

public class ChangePasswordPage {

    public static void waitForPageLoad() {changePasswordTitle.shouldBe(exist, Duration.ofSeconds(30));}

    public static void enterPassword(String pass) {password.setValue(pass);}

    public static void enterConfirmPassword(String pass) {confirmPassword.setValue(pass);}

    public static void clickChangePasswordButton() {changePasswordButton.click();}
}
