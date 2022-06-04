import io.qameta.allure.Feature;
import io.qameta.allure.Flaky;
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
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.params.provider.Arguments.arguments;


@Owner("arishkinnov")
@Feature("Авторизация")
public class AuthTest {

    @BeforeEach
    public void setup(){
        open("https://github.com/");
        TestPage.mainPage.mainSignInButton()
                .click();

    }

    @Story("Авторизация позитивный кейс")
    @DisplayName("Авторизация с коректными данными:")
    public void shouldAuthorizeTest() {
        step("Открыть главную страницу github ", () -> {
            open("https://github.com/");// переход на главную страницу

        });

        step("Заполнить поля инпута и пароля и нажать кнопку авторизации", () -> {
                    TestPage.mainPage.mainSignInButton()// кликнуть на кнопку 'Войти'
                            .click();
                    TestPage.mainPage.loginInput()// ввести логин
                            .sendKeys("rinatest");
                    TestPage.mainPage.passwordInput()// ввести пароль
                            .sendKeys("fhbyf12345678)");
                    TestPage.mainPage.enterButton()
                            .click();
                }
        );

        step("Проверка авторизации в github", () -> {
            TestPage.mainPage.header() //шапка страницы после авторизации
                    .shouldBe(visible);
            TestPage.mainPage.commandList() //открыть тулбар с выбором разделов
                    .click();
            TestPage.mainPage.myProfileButton()// кликнуть на 'Your profile'
                    .click();
            TestPage.mainPage.myName()//
                    .shouldBe(visible);
        });
    }
    @MethodSource("incorrectCredentials")
    @ParameterizedTest(name = "{displayName} {0}")
    @Story("Авторизация негативный кейс")
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
