package org.pageobjects;

import org.abstractcomponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OrdersPage extends AbstractComponent {
    WebDriver driver;

    public OrdersPage(WebDriver driver) {
        super(driver);
        this.driver=driver;
    }

    @FindBy(xpath = "//tbody/tr[1]/th")
    private WebElement order_id;

    @FindBy(xpath = "//tbody/tr[1]/td[2]")
    private WebElement prod_name;

    public boolean verifyOrderDisplayed(String orderId, String prodName){
        String oid = order_id.getText().trim();
        String pname = prod_name.getText().trim();
        if(oid.equalsIgnoreCase(orderId) && pname.equalsIgnoreCase(prodName))
            return true;
        else
            return false;
    }
}
