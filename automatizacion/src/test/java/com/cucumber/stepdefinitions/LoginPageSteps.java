package com.cucumber.stepdefinitions;

import com.cucumberpom.base.BaseTest;
import com.cucumber.pages.LoginPage;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginPageSteps extends BaseTest {
    
    LoginPage loginPage;

    @Before
    public void setUp(){
        initBrowser();
        loginPage = new LoginPage();
    }

    @After
    public void tearDown() throws InterruptedException{
        Thread.sleep(8000);
        driver.close();
    }

    @Given("^El usuario se encuentra en la pagina de login$")
    public void el_usuario_se_encuentra_en_la_pagina_de_login() {
        driver.get(prop.getProperty("applicationUrl"));
        loginPage = new LoginPage();
    }

    @When("^Verficar que el usuario esta en la pagina de login$")
    public void verficar_que_el_usuario_esta_en_la_pagina_de_login() throws InterruptedException {
        loginPage.waitForElementToBeVisible();
    }

    @Then("^El usuario ingresa las credenciales validas$")
    public void el_usuario_ingresa_las_credenciales_validas() {
        // Implementar la logica para ingresar las credenciales validas
    }

}
