package web;

import elements.MainMenu;
import io.qameta.allure.Step;
import models.Ticket;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class HelpdeskUITest {

    private WebDriver driver;
    private Ticket ticket;

    @BeforeClass
    public void setup() throws IOException {
        loadProperties();
        setupDriver();
    }

    @Step("Загрузить конфигурацилнные файлы")
    private void loadProperties() throws IOException {
        // Читаем конфигурационные файлы в System.properties
        System.getProperties().load(ClassLoader.getSystemResourceAsStream("config.properties"));
        System.getProperties().load(ClassLoader.getSystemResourceAsStream("user.properties"));
    }

    @Step("Создать экземпляр драйвера")
    private void setupDriver() {
        // Создание экземпляра драйвера
        driver = new ChromeDriver();
        // Устанавливаем размер окна браузера, как максимально возможный
        driver.manage().window().maximize();
        // Установим время ожидания для поиска элементов
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // Установить созданный драйвер для поиска в веб-страницах
        AbstractPage.setDriver(driver);
    }

    @Test
    public void createTicketTest() {
        ticket = buildNewTicket();

        driver.get(System.getProperty("site.url"));

        MainMenu mainMenu = new MainMenu(driver);
        mainMenu.clickOnNewTicketButton();

        CreateTicketPage createTicketPage = new CreateTicketPage();
        createTicketPage.createTicket(ticket);
        ViewPage viewPage = new ViewPage();
        viewPage.getTicketTitle();
        viewPage.saveId(ticket);
        viewPage.checkTicket(ticket);

        mainMenu.clickOnLogInButton();

        LoginPage loginPage = new LoginPage();
        loginPage.login(System.getProperty("user"), System.getProperty("password"));
        Assert.assertTrue(mainMenu.loginedUser().contains(ticket.getAssigned_to()), "Имя логина не совпадает");

        mainMenu.searchTicket(ticket);

        TicketsPage ticketsPage = new TicketsPage();
        ticketsPage.openTicket(ticket);

        TicketPage ticketPage = new TicketPage();
        ticketPage.checkTicket(ticket);
    }

    private Ticket buildNewTicket() {
        Ticket ticket = new Ticket();
        ticket.setTitle("UI test");
        ticket.setDue_date("2022-07-09");
        ticket.setAssigned_to(System.getProperty("user"));
        ticket.setCreated("2022-07-09T12:30:55.467Z");
        ticket.setModified("2022-07-09T12:30:55.467Z");
        ticket.setSubmitter_email("user@example.com");
        ticket.setStatus(1);
        ticket.setOn_hold(true);
        ticket.setDescription("UI description test");
        ticket.setResolution("UI resolution test");
        ticket.setPriority(2);
        ticket.setSecret_key("UI secretKey test");
        ticket.setQueue(2);
        ticket.setKbitem(2);
        ticket.setMerged_to(2);
        return ticket;
    }

    @AfterTest
    public void close() {
        if (driver != null) {
            // Закрываем одно текущее окно браузера
            driver.close();
            // Закрываем все открытые окна браузера, завершаем работу браузера, освобождаем ресурсы
            driver.quit();
        }
    }
}
