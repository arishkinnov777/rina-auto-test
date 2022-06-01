package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class GithubPage {
    public SelenideElement clickTooltip() {
        return $("[id='branch-select-menu']").as("тултип выбора ветки");
    }

    public ElementsCollection checkoutBranch() {
        return $$(".flex-1.css-truncate.css-truncate-overflow").as("выбор ветки гитхаба");
    }

    public SelenideElement releasesLink() {
        return $(byText("Releases"));
    }

    public SelenideElement buttonRelease() {
        return $(".js-selected-navigation-item.selected.subnav-item").as("кнопка 'Release'");
    }

    public SelenideElement findInput() {
        return $(".form-control.subnav-search-input.width-full").as("инпут поиска");
    }

    public ElementsCollection releasesList() {
        return $$(".Box-body").as("список релизов");
    }
}
