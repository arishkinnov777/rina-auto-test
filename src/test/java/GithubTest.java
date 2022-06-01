
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

@Owner("arishkinnov")
@Feature("Ветки гита")

public class GithubTest {
    @BeforeEach
    public void setup() {
        open("https://github.com/junit-team/junit4");
    }

    @Test
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

    @Test
    @DisplayName("Выбор ветки")
    public void shouldSearchByReleaseTest() {
        step("Перейти на страницу 'Releases'", () -> {
            TestPage.githubPage.releasesLink()
                    .click();
            TestPage.githubPage.buttonRelease()
                    .shouldBe(visible);
        });

        step("Проверить, сколько записей найдется по значению в буквах", () -> {
            TestPage.githubPage.findInput() //ввести значение
                    .sendKeys("RC");
            TestPage.githubPage.findInput(). //нажать на кнопку ENTER
                    sendKeys(Keys.ENTER);
            TestPage.githubPage.releasesList(). //проверить сколько строк вышло по значению RC
                    shouldHave(size(2));
        });

        step("Проверить, сколько записей найдется по значению в цифрах", () -> {
            TestPage.githubPage.findInput()// очистить инпут
                    .clear();
            TestPage.githubPage.findInput()
                    .sendKeys("4.13");//ввести значение
            TestPage.githubPage.findInput() //нажать на кнопку ENTER
                    .sendKeys(Keys.ENTER);
            TestPage.githubPage.releasesList().//проверить сколько строк вышло по значению 4.13
                    shouldHave(size(6));
        });
    }
}
