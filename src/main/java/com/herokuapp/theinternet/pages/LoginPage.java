package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePageObject{

    private By usernameLocator = By.id("username");
    private By passwordLocator = By.name("password");
    private By loginButtonLocator = By.tagName("button");
    private By errorMessageLocator = By.cssSelector("#flash");

    public LoginPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    public SecureAreaPage logIn(String username, String password) {
        log.info("Executing Login with username [" + username + "] and password " + password );
        type(username, usernameLocator);
        type(password, passwordLocator);
        click(loginButtonLocator);
        return new SecureAreaPage(driver, log);
    }

    public void negativeLogin(String username, String password) {
        log.info("Executing negative Login with username [" + username + "] and password " + password );
        type(username, usernameLocator);
        type(password, passwordLocator);
        click(loginButtonLocator);
    }

    public void waitForErrorMessage() {
        waitForVisibilityOf(errorMessageLocator, 5);
    }

    public String getErrorMessage() {
        return find(errorMessageLocator).getText();
    }
}
