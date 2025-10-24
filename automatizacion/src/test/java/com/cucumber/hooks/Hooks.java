package com.cucumber.hooks;

import com.cucumberpom.base.BaseTest;
import io.cucumber.java.Before;
import io.cucumber.java.After;

public class Hooks extends BaseTest {

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
