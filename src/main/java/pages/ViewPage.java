package pages;

import io.qameta.allure.Step;
import models.Ticket;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

import static models.Dictionaries.getPriority;
import static models.Dictionaries.getQueue;

/**
 * Страница просмотра карточки тикета (неавторизированный пользователь)
 */
public class ViewPage extends HelpdeskBasePage {

    // Способ объявления и инициализации элементов страницы через driver.findElement(locator)

    /* Инициализация сразу при объявлении элемента.
       Элемент должен присутствовать на странице браузера в момент создания объекта страницы new ViewPage() */
    private final WebElement queue = driver.findElement(By.xpath("//th[contains(text(), 'Queue:')]"));
    private final WebElement email = driver.findElement(By.xpath("//th[text()='Submitter E-Mail']/following-sibling::td[1]"));
    private final WebElement priority = driver.findElement(By.xpath("//th[text()='Priority']/following-sibling::td[1]"));

    // Пример поиска description, используя промежуточный элемент descriptionLabel
    private final WebElement descriptionLabel = driver.findElement(By.xpath("//th[text()='Description']"));
    // Поиск элемента можно выполнять не только относительно driver, но и относительно другого элемента
    private final WebElement description = descriptionLabel.findElement(By.xpath("./parent::*/following-sibling::tr[1]"));

    /* Поиск элемента по локатору.
       Используется для элементов, которых нет на странице браузера в момент создания объекта страницы,
       так как локатор может быть объявлен и проинициализирован до появления элемента на странице. */
    private final By captionLocator = By.xpath("//table/caption");
    private final WebElement caption;


    public ViewPage() {
        // В данном случае инициализация через PageFactory не нужна,
        // но можем проинициализировать элементы по локаторам (если элементы отображаются)
        caption = driver.findElement(captionLocator);
    }

    @Step("Проверить значение полей на карточке тикета")
    public ViewPage checkTicket(Ticket ticket) {
        Assert.assertTrue(queue.getText().contains(getQueue(ticket.getQueue())), "Имя очереди не соответствует");
        Assert.assertTrue(email.getText().contains(ticket.getSubmitter_email()), "E-mail не соответствует");
        Assert.assertTrue(priority.getText().contains(getPriority(ticket.getPriority())), "Приоритет не соответствует");
        Assert.assertTrue(description.getText().contains(ticket.getDescription()), "Описание не соответствует");
        Assert.assertTrue(getTicketTitle().contains(ticket.getTitle()), "Имя тикета не соответствует");
        saveScreenshot();
        return this;
    }

    @Step("Получить заголовок тикета")
    public String getTicketTitle() {
        // Если элемент появляется не сразу, можно выполнить ожидание по условию

        // условие видимости при поиске по локатору
        ExpectedCondition<WebElement> condition = ExpectedConditions.visibilityOfElementLocated(captionLocator);

        // поиск с ожиданием по условию
        WebElement ticketTitle = new WebDriverWait(driver, Duration.ofSeconds(5)).until(condition);

        return ticketTitle.getText();
    }

    @Step("Сохранить id тикета в объект")
    public void saveId(Ticket ticket) {
        String captionText = caption.getText();
        String id = captionText.substring(captionText.indexOf("-") + 1, captionText.indexOf("]"));
        ticket.setId(Integer.parseInt(id));
    }
}
