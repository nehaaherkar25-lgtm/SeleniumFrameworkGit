package org.pageobjects;

import org.abstractcomponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PlaceOrderPage extends AbstractComponent {
    WebDriver driver;

    public PlaceOrderPage(WebDriver driver) {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(tagName = "h1")
    private WebElement confirmationMsg;

    @FindBy(css = "td[class*='em-spacer'] label[class*='inserted']")
    private WebElement orderID;

    public String getConfirmationMsg(){
        return confirmationMsg.getText().trim();
    }

    public String getOrderId(){
        return orderID.getText().split("\\|")[1].trim();
    }
}
