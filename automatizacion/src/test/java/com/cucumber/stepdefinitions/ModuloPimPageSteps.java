package com.cucumber.stepdefinitions;


import com.cucumberpom.base.BaseTest;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import com.cucumber.pages.ModuloPimPage;

public class ModuloPimPageSteps extends BaseTest {
    
    ModuloPimPage moduloPimPage = new ModuloPimPage();

    @Given("^El usuario se encuentra en la pagina de inicio del sistema PIM$")
    public void el_usuario_se_encuentra_en_la_pagina_de_inicio_del_sistema_PIM() throws InterruptedException {
        moduloPimPage.el_usuario_se_encuentra_en_la_pagina_de_inicio_del_sistema_PIM();
    }

    /*@Then("^El usuario crea un nuevo empleado con datos validos$")
    public void el_usuario_crea_un_nuevo_empleado_con_datos_validos() throws InterruptedException {
       
    }   */
}
