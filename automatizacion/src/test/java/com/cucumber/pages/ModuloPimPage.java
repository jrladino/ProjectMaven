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

    public void crearNuevoEmpleado(String nombre, String apellido, String idEmpleado) {
        try {
            By botonAgregar = By.xpath("//button[normalize-space()='Add']");
            WebElement agregarButton = wait.until(ExpectedConditions.elementToBeClickable(botonAgregar));
            agregarButton.click();

            By campoNombre = By.name("firstName");
            By campoApellido = By.name("lastName");
            By campoIdEmpleado = By.name("employeeId");
            By botonGuardar = By.xpath("//button[normalize-space()='Save']");

            wait.until(ExpectedConditions.visibilityOfElementLocated(campoNombre)).sendKeys(nombre);
            wait.until(ExpectedConditions.visibilityOfElementLocated(campoApellido)).sendKeys(apellido);
            wait.until(ExpectedConditions.visibilityOfElementLocated(campoIdEmpleado)).clear();
            wait.until(ExpectedConditions.visibilityOfElementLocated(campoIdEmpleado)).sendKeys(idEmpleado);
            wait.until(ExpectedConditions.elementToBeClickable(botonGuardar)).click();

            System.out.println("Nuevo empleado creado exitosamente.");
        } catch (TimeoutException e) {
            System.out.println("Error al crear un nuevo empleado: " + e.getMessage());
        }
    }
}
