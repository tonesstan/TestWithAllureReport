package AuthorizationTest.Pages;

import java.time.Duration;

import static AuthorizationTest.Elements.RegisterConfirmationPage.*;
import static com.codeborne.selenide.Condition.exist;

public class RegisterConfirmationPage {

    public static void waitForPageLoad() {confirmButton.shouldBe(exist, Duration.ofSeconds(30));}

    public static void clickConfirmButton() {confirmButton.click();}
}
