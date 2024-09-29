package AuthorizationTest.Tests;

import AuthorizationTest.Pages.*;
import com.codeborne.selenide.Configuration;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.opentest4j.AssertionFailedError;

import java.util.stream.Stream;

import static AuthorizationTest.Utils.generateRandomEmail;
import static AuthorizationTest.Utils.generateRandomPassword;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.*;
import static io.qameta.allure.Allure.*;

@Epic("Пользовательский интерфейс")
@Feature("Авторизация")
public class AuthorizationTest {

    private static final String email = "niragoqa@cyclelove.cc";
    private static final String password = "f$Q:w&6,Jz6QVgA";

    private static final String forgotPasswordEmail = "lynneacyan@rustyload.com";
    private static final String forgotPasswordEmailPassword = "3p@UtXY8k#";

    @BeforeAll
    public static void setUp() {
        Configuration.baseUrl = "https://auth.rbc.ru";
        Configuration.browser = "firefox";
        Configuration.browserCapabilities = new FirefoxOptions().setPageLoadStrategy(PageLoadStrategy.EAGER).addArguments("-headless", "--window-size=1920,1080", "--disable-notifications", "--disable-gpu", "--disable-dev-tools", "--fastSetValue");
        Configuration.timeout = 2000;
    }

    @BeforeEach
    public void openLoginPage() {
        open("/");
        step("Дано: пользователь на странице авторизации", RegisterPage::waitForPageLoad);
    }

    @AfterEach
    public void cleanUp() {
        clearBrowserCookies();
        clearBrowserLocalStorage();
        executeJavaScript("window.sessionStorage.clear()");
    }

    @AfterAll
    public static void tearDown() {closeWebDriver();}

    @ParameterizedTest(name = "Login with {3} test")
    @Tag("login")
    @MethodSource("authorizationTestData")
    @Story("Как пользователь, чтобы зайти в свой аккаунт, я хочу иметь возможность авторизоваться в системе.")
    @Severity(SeverityLevel.CRITICAL)
    public void authorization(boolean valid, String email, String password, String testName) {
        String emailValidity = "правильный";
        String passwordValidity = "правильный";
        String no = "";
        if (!valid) {
            no = "не";
            if (testName.equals("invalid email")) {emailValidity = "неправильный";} else {passwordValidity = "неправильный";}
        }
        String description = switch (testName) {
            case "valid credentials" -> "Авторизация с правильными email и паролем";
            case "invalid email" -> "Авторизация с неправильным email и правильным паролем";
            case "invalid password" -> "Авторизация с правильным email и неправильным паролем";
            default -> "Авторизация с неправильным email и неправильным паролем";
        };

        description(description);
        String errorEvent = (valid) ? "Authorization failure with correct credentials" : "Successful authorization with invalid credentials";
        String errorEventRu = (valid) ? "Неудачная авторизация с верными данными" : "Успешная авторизация с неправильными данными";
        step("Когда пользователь нажимает кнопку 'Вход'", RegisterPage::clickLoginButton);
        step("И вводит " + emailValidity + " email", () -> {
            System.out.println("Enter email: " + email);
	        LoginPage.enterEmail(email);
            addAttachment("Ввели email: " + email, "Ввели email: " + email);
        });
        step("И вводит " + passwordValidity + " пароль", () -> {
            System.out.println("Enter password: " + password);
            LoginPage.enterPassword(password);
            addAttachment("Ввели пароль: " + password, "Ввели пароль: " + password);
        });
        step("И нажимает кнопку 'Войти'", () -> {
            LoginPage.clickLoginButton();
            System.out.println("Login button clicked");
        });
        step ("Тогда он " + no + " попадает на главную страницу", () -> {
            MainPage.waitForPageLoad();
            try {assertEquals(MainPage.authorizationCheck(), valid, "\nError: " + errorEvent + ".\nTest failed.\n");}
            catch (AssertionFailedError e) {addAttachment("Ошибка:" + errorEventRu + ".\nТест провален.", "Ошибка:" + errorEventRu + ".\nТест провален.");}
        });
    }

    private static Stream<Arguments> authorizationTestData() {
        return Stream.of(
                Arguments.of(true, email, password, "valid credentials"),
                Arguments.of(false, generateRandomEmail(), password, "invalid email"),
                Arguments.of(false, email, generateRandomPassword(), "invalid password")
        );
    }

    @Test
    @Tag("restore")
    @DisplayName("Change forgotten password")
    @Story("Как пользователь, чтобы сохранить доступ к своему аккаунту, я хочу иметь возможность восстановить пароль к нему по email.")
    @Description("Тест восстановления доступа к аккаунту")
    @Severity(SeverityLevel.NORMAL)
    public void changePassword() {
        step("Когда пользователь нажимает кнопку 'Вход'", RegisterPage::clickLoginButton);
        step("И нажимает на ссылку 'Забыли пароль?'", LoginPage::clickForgotPassword);
        step("Тогда он попадает на страницу восстановления пароля", ForgotPasswordPage::waitForPageLoad);
        step("Когда пользователь вводит email", () -> {
            ForgotPasswordPage.enterEmail(forgotPasswordEmail);
            addAttachment("Ввели email: " + forgotPasswordEmail, "Ввели email: " + forgotPasswordEmail);
        });
        step("И нажимает кнопку 'Восстановить'", ForgotPasswordPage::clickRestoreButton);
        step("Тогда он видит сообщение 'Смена пароля'", () -> {
            ForgotPasswordPage.waitForRequestConfirmation();
            assertTrue(ForgotPasswordPage.requestCheck(), "\nError: password change request hasn't been confirmed.\nTest failed.\n");
        });
        step("Когда пользователь переходит на сайт своей электронной почты", () -> {
            open("https://mail.tm");
            TempMailPage.waitForPageLoad();
            TempMailPage.waitForTempMailCreation();
        });
        step("И заходит в свой аккаунт", () -> {
            TempMailPage.openAccountMenu();
            TempMailPage.clickLoginInAccountMenu();
            TempMailPage.enterEmail(forgotPasswordEmail);
            TempMailPage.enterPassword(forgotPasswordEmailPassword);
            TempMailPage.clickLoginButton();
        });
//        assertEquals(TempMailPage.getEmail(), forgotPasswordEmail, "\nError: temporary e-mail has been deleted by the mail service.\nTest failed.\n");
        step("И ожидает письмо со ссылкой на смену пароля", () -> {
            try {TempMailPage.waitForMessage();} catch (Exception e) {
                fail("\nError: password change message hasn't received for 10 minutes.\nTest failed.\n");
                addAttachment("Ошибка: письмо не получено в течение 10 минут. \nТест провален.", "Ошибка: письмо не получено в течение 10 минут. \nТест провален.");
            }
            try {assertEquals("auth@rbc.ru", TempMailPage.getSendersEmail(), "Error: trash message.\nTest failed.");}
            catch (AssertionFailedError e) {addAttachment("Ошибка: пришло мусорное сообщение.\nТест провален.", "Ошибка: пришло мусорное сообщение.\nТест провален.");}
        });
        step("И, получив письмо, открывает его", () -> {
            TempMailPage.clickMessage();
            TempMailPage.waitForMessageLoad();
        });
        step("И нажимает на ссылку на смену пароля", TempMailPage::clickPasswordChangeLink);
        step("Тогда он попадает на страницу смены пароля", ChangePasswordPage::waitForPageLoad);
        String pass = generateRandomPassword();
        step("Когда пользователь вводит новый пароль", () -> {
            System.out.println("Enter new password: " + pass);
            ChangePasswordPage.enterPassword(pass);
            addAttachment("Ввели пароль: " + pass, "Ввели новый пароль: " + pass);
        });
        step("И повторяет его ввод в поле подтверждения пароля", () -> ChangePasswordPage.enterConfirmPassword(pass));
        step("И нажимает кнопку 'Сохранить новый пароль'", ChangePasswordPage::clickChangePasswordButton);
        step("Тогда он попадает на главную страницу", () -> {
            MainPage.waitForPageLoad();
            try {assertTrue(MainPage.authorizationCheck(), "\nError: password change failed.\nTest failed.\n");}
            catch (AssertionFailedError e) {addAttachment("Ошибка: смена пароля не удалась. \nТест провален.", "Ошибка: смена пароля не удалась. \nТест провален.");}
        });
    }

    /*
    @Test
    @Tag("register")
    @DisplayName("Registration with real new email")
    public void registrationWithRealEmail() {
        open("https://temp-mail.io");
        TempMailPage.waitForPageLoad();
        TempMailPage.waitForTempMailCreation();
        String email = TempMailPage.getEmail();
        System.out.println("Email: " + email);
        String password = generateRandomPassword();
        System.out.println("Password: " + password);
        open("/");
        RegisterPage.waitForPageLoad();
        RegisterPage.enterEmail(email);
        RegisterPage.enterPassword(password);
        RegisterPage.enterConfirmPassword(password);
        RegisterPage.clickReCaptcha();
        RegisterPage.checkCheckBox();
        RegisterPage.clickRegistrationButton();
        MainPage.waitForPageLoad();
        assertTrue(MainPage.authorizationCheck(), "\nError: registration failed.\nTest failed.\n");
        open("https://temp-mail.io");
        TempMailPage.waitForPageLoad();
        TempMailPage.waitForTempMailCreation();
        try {TempMailPage.waitForMessage();} catch (Exception e) {fail("\nConfirmation message not received.\nTest failed.\n");}
        assertEquals(TempMailPage.getSendersEmail(), "auth@rbc.ru", "Error: trash message.\nTest failed.");
        TempMailPage.clickMessage();
        TempMailPage.waitForMessageLoad();
        TempMailPage.clickEmailConfirmButton();
        MainPage.waitForPageLoad();
        assertTrue(MainPage.authorizationCheck(), "\nError: email confirmation failed.\nTest failed.\n");
    }
     */

}