package com.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
public class BaseTest {

    protected WebDriver driver;

    @BeforeTest
    public void setupDriver(ITestContext ctx) throws IOException {

        //Optional Parameters are host and browser


        // Code to check copy test-resources
        Properties properties = new Properties();
        String temp [] = {System.getProperty("user.dir"),"src","test","test-resources","my.properties"};
        String file = String.join(File.separator, temp);
        FileInputStream fis = new FileInputStream(file);
        properties.load(fis);
        System.out.println(properties.get("author"));
        //


        String host = "localhost";
        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability("Max.Concurrency",3);

        if(System.getProperty("BROWSER") != null &&
                System.getProperty("BROWSER").equalsIgnoreCase("firefox")){
                dc.setBrowserName("firefox");
        }else{
            dc.setBrowserName("chrome");
        }

        if(System.getProperty("HUB_HOST") != null){
            host = System.getProperty("HUB_HOST");
        }

        String testName = ctx.getCurrentXmlTest().getName();


        String completeUrl = "http://" + host + ":4444/wd/hub";
        dc.setCapability("name", testName);
        this.driver = new RemoteWebDriver(new URL(completeUrl), dc);

        // OR

        //System.setProperty("webdriver.chrome.driver","path to driver");
        //this.driver=new ChromeDriver();
    }

    @AfterTest
    public void quitDriver(){
        this.driver.quit();
    }



}
