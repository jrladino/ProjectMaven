package com.cucumber.hooks;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.cucumberpom.base.BaseTest;
import io.cucumber.java.Scenario;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.After;

public class Hooks extends BaseTest {

    @AfterStep
    public void addScreenshot(Scenario scenario) {
        // if (scenario.isFailed()) {
        final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        scenario.attach(screenshot, "image/png", "screenshot");
        // }
    }

    @Before
    public void setUp() {
        if (driver == null) {
            initBrowser();
            System.out.println("✅ Navegador inicializado.");
        }
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
            System.out.println("🧹 Navegador cerrado correctamente.");
        }
    }
}
