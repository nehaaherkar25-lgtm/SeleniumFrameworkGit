package org.abstractcomponents;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.pageobjects.CartPage;
import org.pageobjects.CheckouttPage;
import org.pageobjects.OrdersPage;

import java.time.Duration;

public class AbstractComponent {

    WebDriver driver;

    public AbstractComponent(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//*[contains(text(),'Cart')]//parent::button[contains(@class,'btn-custom')]")
    WebElement cartBtn;

    @FindBy(xpath="//button[text()='Checkout']")
    WebElement checkoutBtn;

    @FindBy(css="button[routerlink*='myorders']")
    WebElement ordersBtn;

    public void waitForElemVisibility(By elem){
        WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(7));
        w.until(ExpectedConditions.visibilityOfElementLocated(elem));
    }

    public void waitForWebelemVisibility(WebElement elem){
        WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(5));
        w.until(ExpectedConditions.visibilityOf(elem));
    }

    public void waitForElemInvisibility(By elem){
        WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(7));
        w.until(ExpectedConditions.invisibilityOfElementLocated(elem));
    }

    public CartPage goToCart(){
        cartBtn.click();
        return new CartPage(driver);
    }

    public CheckouttPage goToCheckout(){
        checkoutBtn.click();
        return new CheckouttPage(driver);
    }

    public OrdersPage goToOrders(){
        ordersBtn.click();
        return new OrdersPage(driver);
    }

    public void scrollBy(){
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("window.scrollBy(0,1500)");
    }

    public void scrollElemIntoView(String elem){
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("document.querySelector(\""+elem+"\").scrollIntoView()");
    }

    public void scrollElementIntoView(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

}
