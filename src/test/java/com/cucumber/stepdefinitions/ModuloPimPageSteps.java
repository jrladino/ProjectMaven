package com.cucumber.stepdefinitions;

import com.cucumberpom.base.BaseTest;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import com.cucumber.pages.ModuloPimPage;

public class ModuloPimPageSteps extends BaseTest {

    private ModuloPimPage moduloPimPage;

    @And("^El usuario se encuentra en la pagina de inicio del sistema$")
    public void el_usuario_se_encuentra_en_la_pagina_de_inicio_del_sistema()  {
        moduloPimPage = new ModuloPimPage(driver); // ✅ Usa el driver del BaseTest ya inicializado
        moduloPimPage.validaPantallaPrincipal();
    }

    @When("^El usuario ingresa al modulo PIM$")
    public void el_usuario_ingresa_al_modulo_PIM()  {
        moduloPimPage.irAModuloPIM();
    }

    @When("^Usuario hace clic en el boton Agregar$")
    public void usuario_hace_clic_en_el_boton_Agregar()  {
        moduloPimPage.usuario_hace_clic_en_el_boton_Agregar();
    }

    @Then("^El usuario crea un nuevo empleado con los datos validos$")
    public void el_usuario_crea_un_nuevo_empleado_con_datos_validos() throws InterruptedException {
        moduloPimPage.el_usuario_crea_un_nuevo_empleado_con_datos_validos();
    }

    @Then("^El usuario guarda el formulario con los datos$")
    public void el_usuario_guarda_el_formulario_con_los_datos() throws InterruptedException {
        moduloPimPage.el_usuario_guarda_el_formulario_con_los_datos();
    }
}
