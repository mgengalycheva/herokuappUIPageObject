package com.herocuapp.theinternet.loginpagetests;

import com.herokuapp.theinternet.base.TestUtilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

public class PositiveLoginTests extends TestUtilities {

    @Test(priority = 1, groups = { "positiveTests", "smokeTests"})
    public void positiveLoginTest() {
        log.info("Starting positiveLoginTest");

// open test page
        String url = "http://the-internet.herokuapp.com/login";
        driver.get(url);
        log.info("Page is opened");

// enter username
        WebElement username = driver.findElement(By.id("username"));
        username.sendKeys("tomsmith");

// enter password
        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("SuperSecretPassword!");

        WebDriverWait wait = new WebDriverWait(driver, 10);

        sleep(3000);

// click login button
        WebElement loginButton = driver.findElement(By.xpath("//button[contains(@type, 'submit')]"));
        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        loginButton.click();

// verification:
// new url
        String expectedUrl = "http://the-internet.herokuapp.com/secure";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl, "Actual page url is not the same as expected");


// logout button is visible
        WebElement logOutButton = driver.findElement(By.xpath("//a[@class='button secondary radius']"));
        Assert.assertTrue(logOutButton.isDisplayed(), "Logout button is not visible");

// successful login message
        WebElement successMessage = driver.findElement(By.cssSelector("#flash"));
        String expectedMessage = "You logged into a secure area!";
        String actualMessage = successMessage.getText();
        //Assert.assertEquals(actualMessage, expectedMessage, "Actual message is not the same as expected");
        Assert.assertTrue(actualMessage.contains(expectedMessage), "Actual message is not the same as expected");

    }
}
