package com.cucumberpom.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

    public static WebDriver driver;
    public static Properties prop = new Properties();

    public BaseTest() {
        try {
            String basePath = System.getProperty("user.dir");
            FileInputStream fis = new FileInputStream(basePath + "/src/test/resources/config.properties");
            prop.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Error al cargar config.properties: " + e.getMessage());
        }

        System.out.println("📁 Cargando config.properties...");
        System.out.println("Usuario: " + prop.getProperty("userName"));
        System.out.println("Password: " + prop.getProperty("password"));
        System.out.println("URL: " + prop.getProperty("applicationUrl"));
    }

    public static void initBrowser() {
        String modo = System.getProperty("execMode", prop.getProperty("execMode", "normal"));
        String browser = System.getProperty("browser", prop.getProperty("browser", "chrome"));

        System.out.println("Modo de ejecución: " + modo);
        System.out.println("Browser: " + browser);

        if (!browser.equalsIgnoreCase("chrome")) {
            throw new RuntimeException("Navegador no soportado: " + browser);
        }

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();

        if (modo.equalsIgnoreCase("headless")) {
            options.addArguments("--headless=new", "--window-size=1366,768", "--disable-gpu",
                    "--no-sandbox", "--disable-dev-shm-usage");
        } else {
            options.addArguments("--start-maximized", "--incognito");
        }

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0)); // usar waits explícitos
    }
}
