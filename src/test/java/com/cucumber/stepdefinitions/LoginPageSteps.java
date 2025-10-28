package com.cucumber.stepdefinitions;

import com.cucumberpom.base.BaseTest;
import com.cucumber.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.junit.Assert;

public class LoginPageSteps extends BaseTest {
    private LoginPage loginPage;

    @Given("^El usuario se encuentra en la pagina de login$")
    public void el_usuario_se_encuentra_en_la_pagina_de_login() {
        loginPage = new LoginPage(driver);
        loginPage.open(prop.getProperty("applicationUrl"));
    }

    @When("^Verficar que el usuario esta en la pagina de login$")
    public void verficar_que_el_usuario_esta_en_la_pagina_de_login() {
        boolean visible = loginPage.estaEnLoginPage();
        Assert.assertTrue("El campo de usuario no está visible, no se cargó correctamente la página de login", visible);
    }

    @Then("^El usuario ingresa las credenciales validas$")
    public void el_usuario_ingresa_las_credenciales_validas() {
        loginPage.login(prop.getProperty("userName"), prop.getProperty("password"));
        Assert.assertTrue("❌ Error: El usuario no logró iniciar sesión correctamente.",
                loginPage.isUserLoggedIn());
    }
}
