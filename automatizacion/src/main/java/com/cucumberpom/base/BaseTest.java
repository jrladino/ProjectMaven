package com.cucumberpom.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;


public class BaseTest {
    
    public static WebDriver driver;
    public static Properties prop;

    public BaseTest() {
        try {
            prop = new Properties();
            String projectPath = System.getProperty("user.dir");
            FileInputStream fis = new FileInputStream(projectPath + "/src/test/resources/config.properties");
            prop.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void initBrowser(){
        String modo = System.getProperty("execMode");
        String browser = System.getProperty("browser", prop.getProperty("browser", "chrome"));
        System.out.println("Modo de ejecucion: " + modo);
        System.out.println("Browser: " + browser);
        if(browser.equalsIgnoreCase("chrome") && modo.equalsIgnoreCase("headless")){
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless=new");
            options.addArguments("--window-size=1366,768");
		    options.addArguments("--start-maximized");
		    options.addArguments("--disable-gpu");
		    options.addArguments("--ignore-certificate-errors");
		    options.addArguments("--no-sandbox");
		    options.addArguments("--disable-dev-shm-usage");
		    options.addArguments("--enable-automation");
		    options.addArguments("--disable-extensions");
		    options.addArguments("--dns-prefetch-disable");
		    options.addArguments("--incognito");
		    options.addArguments("--disable-web-security");
		    options.addArguments("--allow-running-insecure-content");
		    options.addArguments("--allow-insecure-localhost");
		    options.addArguments("--disable-popup-blocking");

            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(options);
        } 
        if(browser.equalsIgnoreCase("chrome") && !modo.equalsIgnoreCase("headless")){
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--incognito");
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(options);
        }
        driver.manage().window().maximize();
    }

}
