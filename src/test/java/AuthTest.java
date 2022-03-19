import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class AuthTest {

    @Test
    public void shouldAuthorizeTest() {

        open("https://github.com/"); //Открыть форму авторизации

        $("[href = '/login']") //Залогинтся в гитхабе
                .click();
        $("[id='login_field']")
                .sendKeys("rinatest");
        $("[id='password']")
                .sendKeys("fhbyf12345678)");
        $(".js-sign-in-button")
                .click();
        $(".Header")
                .shouldBe(visible);

        $(".js-feature-preview-indicator-container") //переход в личный профиль
                .click();
        $(byText("Your profile"))
                .click();
        $(byText("Arina Novikova"))
                .shouldHave(visible);

    }
}
