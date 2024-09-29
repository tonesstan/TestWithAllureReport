package AuthorizationTest.Elements;

import com.codeborne.selenide.SelenideElement;

import java.util.HashMap;

import static com.codeborne.selenide.Selenide.$;

public class MainPage {

    //https://auth.rbc.ru
    private static final HashMap<String, SelenideElement> elements = new HashMap<>() {{
        put("logo", $("span.topline__logo"));
    }};

    public static final SelenideElement logo = elements.get("logo");
}