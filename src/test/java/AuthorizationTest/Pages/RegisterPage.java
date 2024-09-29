package AuthorizationTest.Pages;

import static AuthorizationTest.Elements.RegisterPage.*;
import static com.codeborne.selenide.Condition.exist;

public class RegisterPage {

    public static void waitForPageLoad() {registerButton.shouldBe(exist);}

    public static void enterEmail(String eMail) {email.setValue(eMail);}

    public static void enterPassword(String pass) {password.setValue(pass);}

    public static void enterConfirmPassword(String pass) {confirmPassword.setValue(pass);}

    public static void checkCheckBox() {checkBox.setSelected(true);}

    public static void uncheckCheckBox() {checkBox.setSelected(false);}

    public static void clickLoginButton() {loginButton.click();}

    public static void clickReCaptcha() {reCaptcha.click();}

    public static void clickRegistrationButton() {registerButton.click();}
}
