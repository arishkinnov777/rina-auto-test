import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@Owner("arishkinnov")
public class AuthTest {

    @BeforeEach
    public void setup(){
        open("https://github.com/");
        TestPage.mainPage.mainSignInButton()
                .click();
    }

    @Test
    @Story("Авторизация")
    @DisplayName("Авторизация с коректными данными:")
    public void shouldAuthorizeTest() {

        TestPage.mainPage.loginInput()
                .sendKeys("rinatest");
        TestPage.mainPage.passwordInput()
                .sendKeys("fhbyf12345678)");
        TestPage.mainPage.enterButton()
                .click();
        TestPage.mainPage.header()
                .shouldBe(visible);
        TestPage.mainPage.commandList() //переход в личный профиль
                .click();
        TestPage.mainPage.myProfileButton()
                .click();
        TestPage.mainPage.myName()
                .shouldHave(visible);

    }

    @MethodSource("incorrectCredentials")
    @ParameterizedTest(name = "{displayName} {0}")
    @DisplayName("Авторизация с некоректными данными:")
    public void shouldNotAuthorizeTest(String type,
                                       String phone,
                                       String password) {

        TestPage.mainPage.loginInput()
                .sendKeys(phone);
        TestPage.mainPage.passwordInput()
                .sendKeys(password);
        TestPage.mainPage.enterButton()
                .click();
        TestPage.mainPage.error()
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
