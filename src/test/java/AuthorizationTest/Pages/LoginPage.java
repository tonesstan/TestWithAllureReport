package AuthorizationTest.Pages;

import static AuthorizationTest.Elements.LoginPage.*;
import static com.codeborne.selenide.Condition.exist;

public class LoginPage {

    public static void waitForPageLoad() {loginButton.shouldBe(exist);}

    public static void enterEmail(String eMail) {email.setValue(eMail);}

    public static void enterPassword(String pass) {password.setValue(pass);}

    public static void clickLoginButton() {loginButton.click();}

    public static void clickRegistrationButton() {registerButton.click();}

    public static void clickForgotPassword() {forgotPassword.click();}
}