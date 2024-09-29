package AuthorizationTest.Elements;

import com.codeborne.selenide.SelenideElement;

import java.time.Duration;
import java.util.HashMap;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.*;

public class RegisterPage {

    //https://auth.rbc.ru
    private static final HashMap<String, SelenideElement> elements = new HashMap<>() {{
        put("email", $$(byName("email")).filter(visible).first());
        put("password", $$(byName("password")).filter(visible).first());
        put("confirmPassword", $(byName("confirm_password")));
        put("checkBox", $(".paywall__auth__form__checkbox__input js-paywall-auth-input"));
        put("loginButton", $x("//div[@data-type='enter']"));
        put("reCaptcha", $("#recaptcha-anchor"));
        put("registerButton", $$("input.paywall__auth__form__submit").filter(visible).first().shouldBe(exist, Duration.ofSeconds(30)));
    }};

    public static final SelenideElement email = elements.get("email");
    public static final SelenideElement password = elements.get("password");
    public static final SelenideElement confirmPassword = elements.get("confirmPassword");
    public static final SelenideElement checkBox = elements.get("checkBox");
    public static final SelenideElement loginButton = elements.get("loginButton");
    public static final SelenideElement reCaptcha = elements.get("reCaptcha");
    public static final SelenideElement registerButton = elements.get("registerButton");
}
