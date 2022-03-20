import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ProfileTest {

    @Test
    @DisplayName("Успешная авторизация")
    public void shouldAuthorizeTest2() {

        TestPage.mainPage.mainSignInButton()
                .click();

    }
}
