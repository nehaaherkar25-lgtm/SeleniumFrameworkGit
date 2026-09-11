package org.tests;

import org.pageobjects.*;
import org.testcomponents.BaseTest;
import org.testcomponents.Retry;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

public class ErrorValidationsTest extends BaseTest {

    @Test(groups = "ErrorHandling")
    public void loginErrorVal() throws IOException {
        SoftAssert sa = new SoftAssert();
        LoginPage lp = new LoginPage(driver);
        lp.login("n@g.com","Neha1234");
        String actualMsg = lp.getErrorMsg();
        sa.assertEquals(actualMsg,"Incorrect email or password.");
        sa.assertAll();
    }

    @Test(retryAnalyzer= Retry.class)
    public void errorVal(){
        SoftAssert sa = new SoftAssert();
        LoginPage lp = new LoginPage(driver);
        ProductCatalog cp = lp.login("anshika@gmail.com","Iamking@000");
        String name = cp.getApplName();
        sa.assertEquals(name,"Automation Pract");
        sa.assertAll();
    }
}
