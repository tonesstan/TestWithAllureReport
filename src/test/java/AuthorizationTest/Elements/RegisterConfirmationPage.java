package AuthorizationTest.Elements;

import com.codeborne.selenide.SelenideElement;

import java.util.HashMap;

import static com.codeborne.selenide.Selenide.$;

public class RegisterConfirmationPage {

    //https://auth.rbc.ru
    private static final HashMap<String, SelenideElement> elements = new HashMap<>() {{
        put("confirmButton", $("input.verification__form__submit"));
    }};

    public static final SelenideElement confirmButton = elements.get("confirmButton");

}
