package org.tests;

import org.pageobjects.*;
import org.testcomponents.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class StandaloneTest extends BaseTest {

    public String orderId, productName = "ZARA COAT 3", countryName = "India";

    @Test(dataProvider= "getData", groups = "Purchase")
    public void e2eSubmitOrder(HashMap<String,String> input) throws IOException, InterruptedException {
        SoftAssert sa = new SoftAssert();
        LoginPage lp = new LoginPage(driver);
        ProductCatalog pc = lp.login(input.get("email"), input.get("password"));
        pc.addProdToCart(input.get("productName"));
        CartPage cp = pc.goToCart();
        boolean flag = cp.verifyProdDisplay(input.get("productName"));
        sa.assertTrue(flag);
        CheckouttPage co = cp.goToCheckout();
        co.setSelectCountry(countryName);
        PlaceOrderPage po = co.submit();
        String confirmationMsg = po.getConfirmationMsg();
        orderId=po.getOrderId();
        sa.assertEquals(confirmationMsg,"THANKYOU FOR THE ORDER.");
        sa.assertAll();
    }

    @Test(dependsOnMethods = "e2eSubmitOrder")
    public void orderHistory(){
        SoftAssert sa = new SoftAssert();
        LoginPage lp = new LoginPage(driver);
        lp.login("neha@g.com","Neha@1234");
        OrdersPage op = lp.goToOrders();
        boolean flag = op.verifyOrderDisplayed(orderId,productName);
        sa.assertTrue(flag);
        sa.assertAll();
    }

    @DataProvider
    public Object[][] getData() throws IOException {
        List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir") + "\\src\\test\\java\\org\\testdata\\PurchaseOrder.json");
        return new Object[][] {{data.get(0)}, {data.get(1)}};
    }

    /*@DataProvider
    public Object[][] getData(){
        HashMap<String,String> input1 = new HashMap<String,String>();
        input1.put("email","ar@g.com");
        input1.put("password","Arv@1234");
        input1.put("productName","ADIDAS ORIGINAL");

        HashMap<String,String> input2 = new HashMap<String,String>();
        input2.put("email","neha@g.com");
        input2.put("password","Neha@1234");
        input2.put("productName","ZARA COAT 3");

        return new Object[][] {{input1}, {input2}};
    }*/

    /*@DataProvider
    public Object[][] getData(){
        return new Object[][] {
                {"ar@g.com","Arv@1234","ADIDAS ORIGINAL"},
                {"neha@g.com","Neha@1234","ZARA COAT 3"}
        };
    }*/
}
