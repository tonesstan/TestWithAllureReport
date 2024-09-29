package AuthorizationTest.Elements;

import com.codeborne.selenide.SelenideElement;

import java.util.HashMap;

import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class ForgotPasswordPage {

    //https://auth.rbc.ru
    private static final HashMap<String, SelenideElement> elements = new HashMap<>() {{
        put("forgotPasswordTitle", $("div.manage-pass__title.js-reset-title"));
        put("email", $(byName("email")));
        put("restoreButton", $("input.manage-pass__form__submit"));
        put("passwordChange", $x("//div[text()='Смена пароля']"));
    }};

    public static final SelenideElement forgotPasswordTitle = elements.get("forgotPasswordTitle");
    public static final SelenideElement email = elements.get("email");
    public static final SelenideElement restoreButton = elements.get("restoreButton");
    public static final SelenideElement passwordChange = elements.get("passwordChange");

}