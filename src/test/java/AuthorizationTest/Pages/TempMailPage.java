package AuthorizationTest.Pages;

import org.opentest4j.AssertionFailedError;

import java.time.Duration;
import java.util.Objects;

import static AuthorizationTest.Elements.TempMailPage.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class TempMailPage{

    public static void waitForPageLoad() {logo.shouldBe(visible);}

    public static void waitForTempMailCreation() {currentEmail.shouldBe(visible, Duration.ofMinutes(1)).shouldHave(attribute("value")).shouldNotBe(attribute("value", ""));}

    public static void openAccountMenu() {account.click();}

    public static void clickLoginInAccountMenu() {login.click();}

    public static void enterEmail(String eMail) {email.setValue(eMail);}

    public static void enterPassword(String pass) {password.setValue(pass);}

    public static void clickLoginButton() {loginButton.click();}

    public static String getEmail() {return email.getAttribute("value");}

    public static void clickRefreshButton() {refreshButton.shouldBe(exist).click();}

    public static void waitForMessage() throws Exception {
        try {messageSubject.shouldBe(exist, Duration.ofMinutes(10));}
        catch (AssertionFailedError e) {throw new Exception(e);}
    }

//    public static String getSendersEmail() {return Objects.requireNonNull(messageFrom.getAttribute("title")).replaceAll(".*<([^>]+)>.*", "$1");}

    public static String getSendersEmail() {return Objects.requireNonNull(messageFrom.getText());}

    public static String getSubject() {return messageSubject.getText();}

    public static void clickMessage() {messageSubject.click();}

    public static void waitForMessageLoad() {messageCloseButton.shouldBe(exist, Duration.ofSeconds(8));}

    public static void clickPasswordChangeLink() {
        switchTo().frame($("iframe.w-full"));
        String href = Objects.requireNonNull(passwordChangeLink.getAttribute("href"));
        switchTo().defaultContent();
        System.out.println("Password change link: " + href);
        deleteMessageButton.click();
        open(href);
    }

    /*
    public static void clickEmailConfirmButton() {
        String href = Objects.requireNonNull(emailConfirmButton.getAttribute("href"));
        System.out.println("Confirmation link: " + href);
        deleteMessageButton.click();
        open(href);
    }
    */

}