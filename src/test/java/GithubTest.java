
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.Keys;

import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@Owner("arishkinnov")
@Feature("Проверка GitHub")

public class GithubTest {
    @BeforeEach
    public void setup() {
        open("https://github.com/junit-team/junit4");
    }

    @Test
    @Feature("Выбор ветки GitHub")
    @DisplayName("Выбор ветки")
    public void shouldCheckoutBrunchTest() {
        step("Клинуть на дропдаун и выбрать ветку ", () -> {
            TestPage.githubPage.clickTooltip()
                    .click();
            TestPage.githubPage.checkoutBranch()
                    .get(1)
                    .click();
        });

        step("Проверить, что выбрана ветка 'fixtures'", () -> {
            TestPage.githubPage.clickTooltip()
                    .shouldHave(text("fixtures"));
        });
    }

    @MethodSource("findBy")
    @ParameterizedTest(name = "{0}")
    @DisplayName("Поиск по ")
    @Feature("Поиск по релизам")
    public void shouldSearchByReleaseTest(String type,
                                          String value,
                                          Integer size) {

        step("Перейти на страницу 'Releases'", () -> {
            TestPage.githubPage.releasesLink()
                    .click();
            TestPage.githubPage.buttonRelease()
                    .shouldBe(visible);
        });

        step("Проверить, сколько записей найдется по значению ", () -> {

            TestPage.githubPage.findInput() //ввести значение
                    .sendKeys(value);
            TestPage.githubPage.findInput(). //нажать на кнопку ENTER
                    sendKeys(Keys.ENTER);
            TestPage.githubPage.releasesList(). //проверить сколько строк вышло по значению
                    shouldHave(size(size));
        });
    }

    static Stream<Arguments> findBy() {
        return Stream.of(
                arguments(
                        "фрагмету состоящем из цифр",
                        "4.13",
                        "6"

                ),
                arguments(
                        "фрагмету состоящем из букв",
                        "RC",
                        "2"
                ),
                arguments(
                        "полному названию",
                        "JUnit 4.13 RC 2",
                        "1"
                )
        );
    }
}
