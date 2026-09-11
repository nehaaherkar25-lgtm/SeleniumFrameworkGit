package org.pageobjects;

import org.abstractcomponents.AbstractComponent;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CheckouttPage extends AbstractComponent {

    WebDriver driver;

    //for driver we need parameterized constructor since it should know the driver instance
    public CheckouttPage(WebDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css="input[placeholder='Select Country']")
    private WebElement selectCountry;

    @FindBy(css = ".ta-results button")
    private List<WebElement> contries;

    private By country = By.cssSelector(".ta-results button");

    @FindBy(css = "a[class*='submit']")
    private WebElement submitBtn;

    @FindBy(css = ".action__submit")
    private WebElement submitButton;

    private String submitBy = ".icon-arrow-right-circle";

    public void setSelectCountry(String countryName){
        selectCountry.sendKeys("Ind");
        for(WebElement c:contries){
            c.sendKeys(Keys.chord(Keys.DOWN));
            if(c.getText().equalsIgnoreCase(countryName)){
                c.click();
                break;
            }
        }
    }

    public PlaceOrderPage submit() throws InterruptedException {
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", submitButton);
        return new PlaceOrderPage(driver);
    }
}
