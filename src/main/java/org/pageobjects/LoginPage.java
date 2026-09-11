package org.pageobjects;

import org.abstractcomponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends AbstractComponent {

    WebDriver driver;

    //for driver we need parameterized constructor since it should know the driver instance
    public LoginPage(WebDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(id="userEmail")
    private WebElement userEmail;

    @FindBy(id="userPassword")
    private WebElement userPassword;

    @FindBy(name="login")
    private WebElement loginBtn;

    @FindBy(css="div[role='alert']")
    private WebElement errorMsg;

    public ProductCatalog login(String email, String pass){
        userEmail.sendKeys(email);
        userPassword.sendKeys(pass);
        loginBtn.click();
        return new ProductCatalog(driver);
    }

    public String getErrorMsg(){
        waitForWebelemVisibility(errorMsg);
        return errorMsg.getText().trim();
    }
   /* public void goTo(){
        driver.get("https://rahulshettyacademy.com/client/#/auth/login");
    }*/
}
