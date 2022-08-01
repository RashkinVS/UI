package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Страница логина
 */
public class LoginPage extends HelpdeskBasePage {

    // поиск элемента через xpath
    @FindBy(xpath = "//*[@id='username']")
    private WebElement user;

    // поиск элемента по id
    @FindBy(id = "password")
    private WebElement password;

    // поиск элемента через css
    @FindBy(css = "[type='submit']")
    private WebElement loginButton;

    public LoginPage() {
        PageFactory.initElements(driver, this);
    }

    /**
     * Авторизация пользователя
     *
     * @param user     логин пользователя
     * @param password пароль пользователя
     */
    @Step("Авторизация пользователя")
    public LoginPage login(String user, String password) {
        setUser(user);
        setPassword(password);
        clickOnLoginButton();
        saveScreenshot();
        return this;
    }

    @Step("Ввести логин {user}")
    private void setUser(String user) {
        this.user.sendKeys(user);
        saveScreenshot();
    }

    @Step("Ввести пароль")
    private void setPassword(String password) {
        this.password.sendKeys(password);
        saveScreenshot();
    }

    @Step("Нажать кнопку авторизации")
    private void clickOnLoginButton() {
        this.loginButton.click();
        saveScreenshot();
    }
}
