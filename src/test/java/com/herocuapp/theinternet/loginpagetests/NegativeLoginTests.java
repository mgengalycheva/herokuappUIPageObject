package com.herocuapp.theinternet.loginpagetests;

import com.herokuapp.theinternet.base.TestUtilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

public class NegativeLoginTests extends TestUtilities {

    @Parameters({"username", "password", "expectedMessage"})
    @Test(priority = 2, groups = { "negativeTests", "smokeTests"})
    public void negativeLoginTest(String username, String password, String expectedMessage) {

        log.info("Starting negativeLoginTest with " + username + " and " + password);

        String url = "http://the-internet.herokuapp.com/login";
        driver.get(url);
        log.info("Page is opened");

        WebElement usernameElement = driver.findElement(By.id("username"));
        usernameElement.sendKeys(username);

        WebElement passwordElement = driver.findElement(By.name("password"));
        passwordElement.sendKeys(password);

        WebElement loginButton = driver.findElement(By.xpath("//button[contains(@type, 'submit')]"));
        loginButton.click();

        //verification

        WebElement notSuccessMessage = driver.findElement(By.cssSelector("#flash"));
        String actualMessage = notSuccessMessage.getText();

        Assert.assertTrue(actualMessage.contains(expectedMessage), "Actual message is not the same as expected");

    }

}
