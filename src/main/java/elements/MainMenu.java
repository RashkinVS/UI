package elements;

import io.qameta.allure.Step;
import models.Ticket;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static pages.AbstractPage.saveScreenshot;

/**
 * Главное меню (блок элементов)
 */
public class MainMenu {

    // Способ объявления элементов страницы, через аннотацию @FindBy (с последующей инициализацией)

    @FindBy(xpath = "//span[contains(text(),'New Ticket')]")
    private WebElement newTicketButton;

    @FindBy(id = "userDropdown")
    private WebElement logInButton;

    @FindBy(xpath = "//input[@id='search_query']")
    private WebElement inputSearch;

    @FindBy(xpath = "//nav//button[@type='submit']")
    private WebElement goButton;

    public MainMenu(WebDriver driver) {
        /* Необходимо инициализировать элементы класса, аннотированные @FindBy.
           Лучше всего это делать в конструкторе. */
        PageFactory.initElements(driver, this);
    }

    @Step("Нажать кнопку создания нового тикета")
    public void clickOnNewTicketButton() {
        newTicketButton.click();
        saveScreenshot();
    }

    @Step("Нажать кнопку логина")
    public void clickOnLogInButton() {
        logInButton.click();
        saveScreenshot();
    }

    @Step("Найти тикет с помощью поиска")
    public void searchTicket(Ticket ticket) {
        setInputSearch(ticket.getTitle())
                .clickOnGoButton();
        saveScreenshot();
    }

    /* Если после вызова void метода, может потребоваться вызов другого метода этого же класса,
       то можно вернуть сам класс и вызвать следующий метод через точку. */
    @Step("Ввести в поле поиска значение {text}")
    public MainMenu setInputSearch(String text) {
        inputSearch.sendKeys(text);
        saveScreenshot();
        return this;
    }

    @Step("Нажать кнопку поиска")
    public void clickOnGoButton() {
        goButton.click();
        saveScreenshot();
    }

    @Step("Получить логин пользователя")
    public String loginedUser() {
        return logInButton.getText();
    }
}
