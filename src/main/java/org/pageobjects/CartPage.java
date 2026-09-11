package org.pageobjects;

import org.abstractcomponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends AbstractComponent {

    WebDriver driver;

    //for driver we need parameterized constructor since it should know the driver instance
    public CartPage(WebDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css="div[class='cartSection'] h3")
    private List<WebElement> cartP;

    public boolean verifyProdDisplay(String prodName){
        return cartP.stream().anyMatch(s->s.getText().equalsIgnoreCase(prodName));
    }
}
