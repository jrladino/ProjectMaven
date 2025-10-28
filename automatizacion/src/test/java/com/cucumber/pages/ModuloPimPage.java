package com.cucumber.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cucumber.utils.ExcelUtil;

import java.time.Duration;
import java.util.Map;

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

    public void usuario_hace_clic_en_el_boton_Agregar() {
        try {
            By botonAgregar = By.xpath("//button[normalize-space()='Add']");
            WebElement agregarButton = wait.until(ExpectedConditions.elementToBeClickable(botonAgregar));
            agregarButton.click();

            System.out.println("Clic en el botón Agregar exitoso.");
        } catch (TimeoutException e) {
            System.out.println("Error al hacer clic en el botón Agregar: " + e.getMessage());
        }
    }

    public void crearNuevoEmpleado() throws InterruptedException {
        Map<String, String> datosEmpleado = ExcelUtil.obtenerRegistroDisponibleYMarcarUsado("src/test/resources/datos.xlsx");
        
        System.out.println("SIIIIII LLLEGGAAAAA: " );
        Thread.sleep(5000); // Espera para observar el resultado antes de cerrar el navegador
        try{
            By inputFirstName = By.name("firstName");
            wait.until(ExpectedConditions.visibilityOfElementLocated(inputFirstName)).sendKeys(datosEmpleado.get("Primer Nombre"));

            By inputMiddleName = By.name("middleName");
            wait.until(ExpectedConditions.visibilityOfElementLocated(inputMiddleName)).sendKeys(datosEmpleado.get("Segundo Nombre"));

            By inputLastName = By.name("lastName");
            wait.until(ExpectedConditions.visibilityOfElementLocated(inputLastName)).sendKeys(datosEmpleado.get("Apellido"));

            By botonGuardar = By.xpath("//button[normalize-space()='Save']");
            WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(botonGuardar));
            saveButton.click();

            System.out.println("Nuevo empleado creado exitosamente: " );
            
            Thread.sleep(5000); // Espera para observar el resultado antes de cerrar el navegador
            
            By OtherID = By.xpath("//label[text()=\"Other Id\"]/following::input[1]");
            wait.until(ExpectedConditions.visibilityOfElementLocated(OtherID)).sendKeys(datosEmpleado.get("Cedula"));

            By LicenseNumber = By.xpath("//label[text()=\"Driver's License Number\"]/following::input[1]");
            wait.until(ExpectedConditions.visibilityOfElementLocated(LicenseNumber)).sendKeys(datosEmpleado.get("Licencia de conduccion"));



            Thread.sleep(5000); // Espera para observar el resultado antes de cerrar el navegador
        }
        catch (Exception e){
            System.out.println("Error al crear nuevo empleado: " + e.getMessage());
        }
    }
}
