package pages;

import elements.MainMenu;

/** Элементы общие для системы Helpdesk */
public class HelpdeskBasePage extends AbstractPage {

    /** Доступ к элементам главного меню */
    public MainMenu mainMenu() {
        return new MainMenu(driver);
    }
}
