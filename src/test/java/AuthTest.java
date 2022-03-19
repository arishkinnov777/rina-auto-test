import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class AuthTest {

    @Test
    public void shouldAuthorizeTest() {

        open("https://github.com/");
        $("[href = '/login']")
                .click();
        $("[id='login_field']")
                .sendKeys("arina-novikova2009@yandex.ru");
        $("[id='password']")
                .sendKeys("2403Ndaas2003");
        //4. Нажать кнопку sign in
        $(".js-sign-in-button")
                .click();
        $(".Header")
                .shouldBe(visible);
    }
}
