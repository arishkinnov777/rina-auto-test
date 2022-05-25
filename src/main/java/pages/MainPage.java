package pages;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class MainPage {
    public SelenideElement mainSignInButton() {
        return $("[href='/login']").as("кнопка логина");
    }

    public SelenideElement loginInput() {
        return $("[id='login_field']").as("поле ввода логина");
    }

    public SelenideElement passwordInput() {
        return $("[id='password']").as("поле ввода пароля");
    }

    public SelenideElement enterButton() {
        return $(".js-sign-in-button").as("кнопка Войти");
    }

    public SelenideElement header() {
        return $(".Header.js-details-container").as("шапка страници с выбором разделов");
    }

    public SelenideElement commandList() {
        return $(".js-feature-preview-indicator-container")
                .as("кнопка открытия тулбара");
    }

    public SelenideElement myProfileButton() {
        return $(byText("Your profile")).
                as("Кнопка входа в профиль");
    }
    public SelenideElement myName() {
        return $(byText("Arina Novikova")).as("Имя пользователя");
    }
    public SelenideElement error() {
        return $(".flash.flash-full.flash-error").as("ошибка");
    }

}
