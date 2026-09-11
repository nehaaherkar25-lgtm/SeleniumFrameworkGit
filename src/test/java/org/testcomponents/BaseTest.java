package org.testcomponents;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class BaseTest {

    public WebDriver driver;
    public ExtentReports ext;

    public WebDriver initDriver() throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\resources\\confige.properties");
        prop.load(fis);
        String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");
        switch (browserName) {
            case "chrome":
            case "chromeheadless":
                ChromeOptions op = new ChromeOptions();
                Map<String, Object> prefs = new HashMap<>();
                prefs.put("profile.password_manager_leak_detection", false);
                op.setExperimentalOption("prefs", prefs);
                //op.addArguments("--start-maximized");
                if(browserName.contains("headless"))
                    op.addArguments("headless");
                driver = new ChromeDriver(op);
                driver.manage().window().setSize(new Dimension(1440,900));
                break;
            case "firefox":
                FirefoxOptions fp = new FirefoxOptions();
                //fp.addArguments("--start-maximized");
                driver = new FirefoxDriver(fp);
                break;
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        //driver.manage().window().maximize();
        return driver;
    }

    public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
        //read and convert json to string
        String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {});
        return data;
    }

    public String getScreenshot(String tcName,WebDriver driver) throws IOException {
        TakesScreenshot tr = (TakesScreenshot)driver;
        File src = tr.getScreenshotAs(OutputType.FILE);
        File dest = new File(System.getProperty("user.dir")+"\\reports\\"+tcName+".png");
        FileUtils.copyFile(src,dest);
        return tcName+".png"; //System.getProperty("user.dir")+"\\reports\\"+
    }
    @BeforeMethod(alwaysRun = true)
    public void launchApplication() throws IOException {
        driver = initDriver();
        driver.get("https://rahulshettyacademy.com/client/#/auth/login");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        //driver.close();
    }
}
