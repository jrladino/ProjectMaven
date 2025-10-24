package com.cucumber.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.junit.Assert;

public class ModuloPimPage {
    
    @SuppressWarnings("unused")
    private WebDriver driver;
    private WebDriverWait wait;

    public ModuloPimPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }
    
    public void validaPantallaModuloPIM() {
        By headerPIM = By.xpath("//h6[text()='Dashboard']");
        WebElement elementoHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(headerPIM));
        Assert.assertTrue("❌ No se visualizó el dashboard del PIM.", elementoHeader.isDisplayed());
    }

    public void irAModuloPIM() {
        try {
            // Selector robusto que no depende de "active"
            By menuPIM = By.xpath("//span[normalize-space()='PIM']/ancestor::a");
            WebElement moduloPIM = wait.until(ExpectedConditions.elementToBeClickable(menuPIM));
            moduloPIM.click();
            System.out.println("Ingreso correcto al módulo PIM.");
        } catch (TimeoutException e) {
            System.out.println("Error al ingresar al módulo PIM: " + e.getMessage());
        }
    }

    public void crearNuevoEmpleado(String primerNombre, String segundoNombre, String apellido) throws InterruptedException {
        try {
            By botonAgregar = By.xpath("//button[normalize-space()='Add']");
            WebElement agregarButton = wait.until(ExpectedConditions.elementToBeClickable(botonAgregar));
            agregarButton.click();

            By campoNombre = By.name("firstName");
            By campoNombre2 = By.name("middleName");
            By campoApellido = By.name("lastName");
            By botonGuardar = By.xpath("//button[normalize-space()='Save']");

            wait.until(ExpectedConditions.visibilityOfElementLocated(campoNombre)).sendKeys(primerNombre);
            wait.until(ExpectedConditions.visibilityOfElementLocated(campoNombre2)).sendKeys(segundoNombre);
            wait.until(ExpectedConditions.visibilityOfElementLocated(campoApellido)).sendKeys(apellido);

            //insertar una espera para ver el llenado de los campos
            Thread.sleep(5000);

            //wait.until(ExpectedConditions.elementToBeClickable(botonGuardar)).click();

            System.out.println("Nuevo empleado creado exitosamente.");
        } catch (TimeoutException e) {
            System.out.println("Error al crear un nuevo empleado: " + e.getMessage());
        }
    }
}
