package com.cucumber.pages;

import com.cucumberpom.base.BaseTest;

import java.time.Duration;

import com.cucumber.utils.ExcelUtil;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class ModuloPimPage extends BaseTest {
    
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

    ExcelUtil excelUtil = new ExcelUtil();

    public ModuloPimPage() {
        PageFactory.initElements(driver, this);
    }
    
    public void el_usuario_se_encuentra_en_la_pagina_de_inicio_del_sistema_PIM() throws InterruptedException {
        try{
            WebElement pim = wait.until(
                ExpectedConditions.elementToBeClickable(
                    org.openqa.selenium.By.cssSelector("a[class=\"oxd-main-menu-item active\"]")
                )                
            );
            pim.click();
        }
        catch(Exception e){
            System.out.println("Error al crear un nuevo empleado: " + e.getMessage());
        }
    }
}
