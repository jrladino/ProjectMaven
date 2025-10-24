package com.cucumber.stepdefinitions;


import com.cucumberpom.base.BaseTest;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

//import io.cucumber.java.en.Then;

import com.cucumber.pages.ModuloPimPage;

public class ModuloPimPageSteps extends BaseTest {
    
    private ModuloPimPage moduloPimPage;

    @And("^El usuario se encuentra en la pagina de inicio del sistema PIM$")
    public void el_usuario_se_encuentra_en_la_pagina_de_inicio_del_sistema_PIM() {
        moduloPimPage = new ModuloPimPage(driver); // ✅ Usa el driver del BaseTest ya inicializado
        moduloPimPage.validaPantallaModuloPIM();
    }

    @When("^El usuario ingresa al modulo PIM$")
    public void el_usuario_ingresa_al_modulo_PIM() {
        moduloPimPage.irAModuloPIM();
    }


    @Then("^El usuario crea un nuevo empleado con los datos validos$")
    public void el_usuario_crea_un_nuevo_empleado_con_datos_validos() throws InterruptedException {
        moduloPimPage.crearNuevoEmpleado("Juan", "Camilo", "Perez");
    }   
}
