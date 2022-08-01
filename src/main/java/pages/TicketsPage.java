package pages;

import io.qameta.allure.Step;
import models.Ticket;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * Страница с таблицей тикетов и фильтрами
 */
public class TicketsPage extends HelpdeskBasePage {

    // пример коллекции веб-элементов
    @FindBy(xpath = "//div[@class='tickettitle']/a")
    private List<WebElement> ticketsHref;

    public TicketsPage() {
        PageFactory.initElements(driver, this);
    }

    /**
     * Ищем строку с тикетом и нажимаем на нее
     *
     * @param ticket
     */
    @Step("Открыть тикет с id {ticket.id}")
    public void openTicket(Ticket ticket) {
        String id = String.valueOf(ticket.getId());
        ticketsHref.stream()
                .filter(WebElement::isDisplayed)
                .filter(ticketHref -> ticketHref.getText().startsWith(id))
                .findFirst()
                .orElseThrow(() -> new AssertionError("Не найден тикет с id " + id))
                .click();
        saveScreenshot();
    }
}
