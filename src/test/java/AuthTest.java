import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class AuthTest {

    @BeforeEach
    public void setup(){
        open("https://github.com/");
        TestPage.mainPage.mainSignInButton()
                .click();
    }

    @Test
    @DisplayName("Авторизация с коректными данными:")
    public void shouldAuthorizeTest() {

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

    @MethodSource("incorrectCredentials")
    @ParameterizedTest(name = "{displayName} {0}")
    @DisplayName("Авторизация с некоректными данными:")
    public void shouldNotAuthorizeTest(String type,
                                       String phone,
                                       String password) {

        $("[id='login_field']")
                .sendKeys(phone);
        $("[id='password']")
                .sendKeys(password);
        $(".js-sign-in-button")
                .click();
        $(".flash.flash-full.flash-error")
                .shouldBe(visible);

    }

    static Stream<Arguments> incorrectCredentials() {
        return Stream.of(
                arguments(
                        "зарегистрированный номер телефона и пароль от чужого аккаунта",
                        "9999999999",
                        "123456789Qq"

                ),
                arguments(
                        "незарегистрированный номер телефона и пароль от чужого аккаунта",
                        "9100000000",
                        "123456789Qq"
                )
        );
    }
}
