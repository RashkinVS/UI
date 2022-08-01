package pages;

import io.qameta.allure.Step;
import models.Ticket;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import static models.Dictionaries.getPriority;
import static models.Dictionaries.getQueue;

/**
 * Страница создания тикета
 */
public class CreateTicketPage extends HelpdeskBasePage {

    @FindBy(xpath = "//select[@id='id_queue']")
    private WebElement selectQueue;

    @FindBy(xpath = "//input[@id='id_title']")
    private WebElement inputProblem;

    @FindBy(xpath = "//textarea[@id='id_body']")
    private WebElement inputDescription;

    @FindBy(xpath = "//select[@id='id_priority']")
    private WebElement selectPriority;

    @FindBy(xpath = "//input[@id='id_due_date']")
    private WebElement inputDueDate;

    @FindBy(xpath = "//input[@id='id_submitter_email']")
    private WebElement inputEmailAddress;

    @FindBy(xpath = "//button[@class='btn btn-primary btn-lg btn-block']")
    private WebElement submitTicketButton;

    public CreateTicketPage() {
        PageFactory.initElements(driver, this);
    }

    @Step("Создать тикет")
    public CreateTicketPage createTicket(Ticket ticket) {
        selectQueueMethod(ticket.getQueue());
        setInputProblem(ticket.getTitle());
        setInputDescription(ticket.getDescription());
        selectPriorityMethod(ticket.getPriority());
        setInputDueDate(ticket.getDue_date());
        setInputEmailAddress(ticket.getSubmitter_email());
        clickOnSubmitButton();
        saveScreenshot();
        return this;
    }

    @Step("Выбрать очередь: {queue}")
    public void selectQueueMethod(int queue) {
        Select select = new Select(selectQueue);
        select.selectByVisibleText(getQueue(queue));
        saveScreenshot();
    }

    @Step("Ввести имя проблемы: {text}")
    public void setInputProblem(String text) {
        inputProblem.sendKeys(text);
        saveScreenshot();
    }

    @Step("Ввести описание проблемы: {text}")
    public void setInputDescription(String text) {
        inputDescription.sendKeys(text);
        saveScreenshot();
    }

    @Step("Выбрать приоритет: {priority}")
    public void selectPriorityMethod(int priority) {
        Select select = new Select(selectPriority);
        select.selectByVisibleText(getPriority(priority));
        saveScreenshot();
    }

    @Step("Ввести дату: {text}")
    public void setInputDueDate(String text) {
        inputDueDate.sendKeys(text);
        saveScreenshot();
    }

    @Step("Ввести e-mail: {text}")
    public void setInputEmailAddress(String text) {
        inputEmailAddress.sendKeys(text);
        saveScreenshot();
    }

    @Step("Нажать на кнопку создания тикета")
    public void clickOnSubmitButton() {
        submitTicketButton.click();
        saveScreenshot();
    }
}
