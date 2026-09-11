package org.pageobjects;

import org.abstractcomponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import static org.openqa.selenium.support.locators.RelativeLocator.*;

import java.util.List;

public class ProductCatalog extends AbstractComponent {

    WebDriver driver;

    //for driver we need parameterized constructor since it should know the driver instance
    public ProductCatalog(WebDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath="//*[@class='row']//div[contains(@class,'ng-star-inserted')]")
    private List<WebElement> products;

    private By addToCart = By.xpath(".//*[contains(text(),'Add To Cart')]");

    private By prodMsg = By.cssSelector("div[aria-label='Product Added To Cart']");

    @FindBy(xpath="//div[contains(@class,'logo')]//h3/following-sibling::p")
    private WebElement label;

    public void addProdToCart(String prodname){
        String actual;
        for(WebElement p : products){
            actual = p.findElement(By.xpath(". //h5/b")).getText();
            if(actual.equalsIgnoreCase(prodname)){
                p.findElement(addToCart).click();
                waitForElemVisibility(prodMsg);
               // waitForElemInvisibility(prodMsg);
                break;
            }
        }
         /*WebElement prod = products.stream()
                .filter(s->s.findElement(By.xpath(".//h5/b")).getText().equalsIgnoreCase("ZARA COAT 3"))
                .findFirst().orElse(null);
        prod.findElement(By.xpath(".//*[contains(text(),'Add To Cart')]")).click();*/
        //sa.assertEquals(driver.findElement(By.cssSelector("div[aria-label='Product Added To Cart']")).getText().trim(),"Product Added To Cart");
    }

    public String getApplName(){
        waitForWebelemVisibility(label);
        return label.getText().trim();
    }
}
