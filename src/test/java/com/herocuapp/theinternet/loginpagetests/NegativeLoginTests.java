package com.herocuapp.theinternet.loginpagetests;

import com.herokuapp.theinternet.base.TestUtilities;
import com.herokuapp.theinternet.pages.LoginPage;
import com.herokuapp.theinternet.pages.SecureAreaPage;
import com.herokuapp.theinternet.pages.WelcomePageObject;
import org.testng.Assert;
import org.testng.annotations.*;

public class NegativeLoginTests extends TestUtilities {

    @Parameters({"username", "password", "expectedMessage"})
    @Test(priority = 2, groups = { "negativeTests", "smokeTests"})
    public void negativeLoginTest(String username, String password, String expectedMessage) {

        log.info("Starting negativeLoginTest with " + username + " and " + password);

// open test page
        WelcomePageObject welcomePageObject = new WelcomePageObject(driver, log);
        welcomePageObject.openPage();

// click on form authentication link
        LoginPage loginPage = welcomePageObject.clickFormAuthenticationLink();

// execute login
        loginPage.negativeLogin(username, password);
        loginPage.waitForErrorMessage();

//verification
        String actualMessage = loginPage.getErrorMessage();
        Assert.assertTrue(actualMessage.contains(expectedMessage), "Actual message is not the same as expected");

    }

}
