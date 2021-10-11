package com.herocuapp.theinternet.loginpagetests;

import com.herokuapp.theinternet.base.TestUtilities;
import com.herokuapp.theinternet.pages.LoginPage;
import com.herokuapp.theinternet.pages.SecureAreaPage;
import com.herokuapp.theinternet.pages.WelcomePageObject;
import org.testng.Assert;
import org.testng.annotations.*;

public class PositiveLoginTests extends TestUtilities {

    @Test(priority = 1, groups = { "positiveTests", "smokeTests"})
    public void loginTest() {
        log.info("Starting loginTests");

// open test page
        WelcomePageObject welcomePageObject = new WelcomePageObject(driver, log);
        welcomePageObject.openPage();

// click on form authentication link
        LoginPage loginPage = welcomePageObject.clickFormAuthenticationLink();

// execute login
        SecureAreaPage secureAreaPage = loginPage.logIn("tomsmith", "SuperSecretPassword!");

// verifications
        // new page url is expected
        Assert.assertEquals(secureAreaPage.getCurrentUrl(), secureAreaPage.getPageUrl());

        // log out button is visible
        Assert.assertTrue(secureAreaPage.isLogoutButtonIsVisible(), "Log out button is not visible");

        // successful login message
        String expectedMessage = "You logged into a secure area!";
        String actualMessage = secureAreaPage.getSuccessMessageText();
        Assert.assertTrue(actualMessage.contains(expectedMessage), "Actual message is not the same as expected");


    }
}
