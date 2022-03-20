package pages;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;

public class MainPage {
    public SelenideElement mainSignInButton() {
        return $("[href='/login']").as("кнопка логина");
    }
}
