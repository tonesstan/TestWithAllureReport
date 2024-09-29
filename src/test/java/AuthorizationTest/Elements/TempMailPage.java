package AuthorizationTest.Elements;

import java.util.HashMap;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.*;

public class TempMailPage {

    //https://temp-mail.io
    /*
    private static final HashMap<String, SelenideElement> elements = new HashMap<>() {{
        put("logo", $(".logo__img"));
        put("email", $x("//input[@data-qa='current-email']"));
        put("refreshButton", $("//button[@data-qa='refresh-button']"));
        put("messageFrom", $(".message__from"));
        put("messageSubject", $(".message__subject"));
        put("messageCloseButton", $("button.btn.message-close"));
        put("deleteMessageButton", $(byTitle("Удалить это сообщение")));
        put("passwordChangeLink", $x("//a[@rel='noopener noreferrer']"));
        put("emailConfirmButton", $x("//img[@alt='Активировать']"));
    }};
    */

    //https://mail.tm
    private static final HashMap<String, SelenideElement> elements = new HashMap<>() {{
        put("logo", $("svg.light-img.h-8"));
        put("currentEmail", $("input#Dont_use_WEB_use_API"));
        put("account", $("span.font-medium.leading-none.text-gray-600"));
        put("login", $x("/html/body/div[3]/div/div[2]/div/div/div[2]/div[4]/div[2]/div/div/div[2]/button[2]/span[2]"));
        put("email", $(byName("address")));
        put("password", $(byName("password")));
        put("loginButton", $("button.w-full.inline-flex.justify-center.border.border-transparent.rounded-md.bg-indigo-600.px-4.py-2.text-base.font-medium.leading-6.text-white.shadow-sm.transition"));
        put("refreshButton", $$("//a[@aria-current='page'").first());
        put("messageFrom", $x("//html/body/div[3]/div/div[2]/main/div[2]/div[2]/ul/li/a/div/div[1]/div[2]/div[1]/div[2]/span"));
        put("messageSubject", $("div.truncate.text-sm.leading-5.text-gray-900"));
        put("messageCloseButton", $("a.flex.items-center.text-sm.font-medium.leading-5.text-gray-500.transition"));
        put("deleteMessageButton", $x("/html/body/div[3]/div/div[2]/main/div[2]/div/div[2]/div[2]/span[4]/button"));
        put("passwordChangeLink", $x("//a[@rel='noopener noreferrer']"));
    }};

    public static final SelenideElement logo = elements.get("logo");
    public static final SelenideElement currentEmail = elements.get("currentEmail");
    public static final SelenideElement account = elements.get("account");
    public static final SelenideElement login = elements.get("login");
    public static final SelenideElement email = elements.get("email");
    public static final SelenideElement password = elements.get("password");
    public static final SelenideElement loginButton = elements.get("loginButton");
    public static final SelenideElement refreshButton = elements.get("refreshButton");
    public static final SelenideElement messageFrom = elements.get("messageFrom");
    public static final SelenideElement messageSubject = elements.get("messageSubject");
    public static final SelenideElement messageCloseButton = elements.get("messageCloseButton");
    public static final SelenideElement deleteMessageButton = elements.get("deleteMessageButton");
    public static final SelenideElement passwordChangeLink = elements.get("passwordChangeLink");

}