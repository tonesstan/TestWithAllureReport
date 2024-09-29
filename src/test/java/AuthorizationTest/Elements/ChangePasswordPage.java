package AuthorizationTest.Elements;

import com.codeborne.selenide.SelenideElement;

import java.util.HashMap;

import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.$;

public class ChangePasswordPage {

    //https://auth.rbc.ru
    private static final HashMap<String, SelenideElement> elements = new HashMap<>() {{
        put("changePasswordTitle", $(".manage-pass__title"));
        put("password", $(byName("password")));
        put("confirmPassword", $(byName("confirm_password")));
        put("changePasswordButton", $("input.manage-pass__form__submit"));
    }};

    public static final SelenideElement changePasswordTitle = elements.get("changePasswordTitle");
    public static final SelenideElement password = elements.get("password");
    public static final SelenideElement confirmPassword = elements.get("confirmPassword");
    public static final SelenideElement changePasswordButton = elements.get("changePasswordButton");

}
