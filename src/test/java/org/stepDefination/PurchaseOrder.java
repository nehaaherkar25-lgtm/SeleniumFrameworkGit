package org.stepDefination;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.pageobjects.*;
import org.testcomponents.BaseTest;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

public class PurchaseOrder extends BaseTest {
    SoftAssert sa = new SoftAssert();
    LoginPage lp;
    ProductCatalog pc;
    CartPage cp;
    CheckouttPage co;
    PlaceOrderPage po;
    @Given("User landed on ecomm website")
    public void user_landed_on_ecomm_website() throws IOException {
        launchApplication();
    }

    @Given("User logged in with {string} and {string}")
    public void userLoggedInWithAnd(String username, String password) {
        lp = new LoginPage(driver);
        pc = lp.login(username, password);
    }

    @When("User adds {string} to cart")
    public void userAddsToCart(String productName) {
        pc.addProdToCart(productName);
        cp = pc.goToCart();
    }


    @And("Checkout {string} and Submit the order")
    public void checkoutAndSubmitTheOrder(String productName) throws InterruptedException {
        boolean flag = cp.verifyProdDisplay(productName);
        sa.assertTrue(flag);
        co = cp.goToCheckout();
        co.setSelectCountry("India");
        po = co.submit();
    }

    @Then("{string} is displayed on confirmation page")
    public void isDisplayedOnConfirmationPage(String arg0) {
        String confirmationMsg = po.getConfirmationMsg();
        //orderId=po.getOrderId();
        sa.assertEquals(confirmationMsg,"THANKYOU FOR THE ORDER.");
        sa.assertAll();
    }

    @Then("{string} error message is displayed")
    public void errorMessageIsDisplayed(String error) {
        String actualMsg = lp.getErrorMsg();
        sa.assertEquals(actualMsg,"Incorrect email or password.");
        sa.assertAll();
    }
}
