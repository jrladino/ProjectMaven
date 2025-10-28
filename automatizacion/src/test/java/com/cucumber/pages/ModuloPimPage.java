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

    public void el_usuario_crea_un_nuevo_empleado_con_datos_validos() throws InterruptedException {
        Map<String, String> datosEmpleado = ExcelUtil.obtenerRegistroDisponibleYMarcarUsado("src/test/resources/datos.xlsx");
        
        System.out.println("SIIIIII LLLEGGAAAAA: " );
        
        try{

            Thread.sleep(2000); // Espera para observar el resultado 

            By inputFirstName = By.name("firstName");
            wait.until(ExpectedConditions.visibilityOfElementLocated(inputFirstName)).sendKeys(datosEmpleado.get("Primer Nombre"));

            By inputMiddleName = By.name("middleName");
            wait.until(ExpectedConditions.visibilityOfElementLocated(inputMiddleName)).sendKeys(datosEmpleado.get("Segundo Nombre"));

            By inputLastName = By.name("lastName");
            wait.until(ExpectedConditions.visibilityOfElementLocated(inputLastName)).sendKeys(datosEmpleado.get("Apellido"));
            Thread.sleep(2000); // Espera para observar el resultado 

            // Localizar el campo Employee Id
            By employeeId = By.xpath("//label[normalize-space()='Employee Id']/following::input[1]");
            WebElement campoEmployeeId = wait.until(ExpectedConditions.visibilityOfElementLocated(employeeId));

            // Limpiar el campo actual (borra el ID generado por defecto)
            campoEmployeeId.clear();

            // Crear un ID único con int random
            String idUnico = "" +(int)(Math.random() * 1000000); // Genera un número aleatorio de 6 dígitos            
            campoEmployeeId.sendKeys(idUnico);

            System.out.println("Nuevo Employee Id generado: " + idUnico);

            By botonGuardar = By.xpath("//button[normalize-space()='Save']");
            WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(botonGuardar));
            saveButton.click();

            System.out.println("Nuevo empleado creado exitosamente: " );
            
            Thread.sleep(2000); // Espera para observar el resultado

            By OtherID = By.xpath("//label[text()=\"Other Id\"]/following::input[1]");
            wait.until(ExpectedConditions.visibilityOfElementLocated(OtherID)).sendKeys(datosEmpleado.get("Cedula"));

            By LicenseNumber = By.xpath("//label[text()=\"Driver's License Number\"]/following::input[1]");
            wait.until(ExpectedConditions.visibilityOfElementLocated(LicenseNumber)).sendKeys(datosEmpleado.get("Licencia de conduccion"));

            By FechaLicencia = By.xpath("//label[text()=\"License Expiry Date\"]/following::input[1]");
            wait.until(ExpectedConditions.visibilityOfElementLocated(FechaLicencia)).sendKeys(datosEmpleado.get("Fecha expiracion Licencia"));

            Thread.sleep(2000); // Espera para observar el resultado
            
            // Hacer clic en el campo de Nacionalidad para desplegar las opciones
            By Nacionalidad = By.xpath("//label[text()='Nationality']/following::div[contains(@class,'oxd-select-text')][1]");
            wait.until(ExpectedConditions.elementToBeClickable(Nacionalidad)).click();

            // Esperar a que las opciones sean visibles
            By opciones = By.xpath("//div[contains(@class, 'oxd-select-option')]");
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(opciones));

            Thread.sleep(2000); // Espera para observar el resultado

            // Seleccionar la nacionalidad "Colombian" de la lista desplegable
            By SeleccionarNacionalidad = By.xpath("//div[contains(@class,'oxd-select-option') and normalize-space()='Colombian']");
            wait.until(ExpectedConditions.elementToBeClickable(SeleccionarNacionalidad)).click();

            // Ingresar la fecha de nacimiento
            By FechaNacimiento = By.xpath("//label[text()='Date of Birth']/following::input[1]");   
            wait.until(ExpectedConditions.visibilityOfElementLocated(FechaNacimiento)).sendKeys(datosEmpleado.get("Fecha de Nacimiento"));

            // Ingresar el género aleatoriamente
            String genero = datosEmpleado.get("Genero").equalsIgnoreCase("Hombre") ? "1" : "2";
            By SelectorGenero = By.xpath("(//span[@class='oxd-radio-input oxd-radio-input--active --label-right oxd-radio-input'])[" + genero + "]");
            wait.until(ExpectedConditions.elementToBeClickable(SelectorGenero)).click();

            Thread.sleep(3000); // Espera para observar el resultado antes de cerrar el navegador
        }
        catch (Exception e){
            System.out.println("Error al crear nuevo empleado: " + e.getMessage());
        }
    }

    public void el_usuario_guarda_el_formulario_con_los_datos() throws InterruptedException {
        try {
            By botonGuardar = By.xpath("(//button[normalize-space()='Save'])[1]");
            WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(botonGuardar));
            saveButton.click();

            Thread.sleep(2000); // Espera para observar el resultado
            ExcelUtil.marcarRegistroComoUsado("src/test/resources/datos.xlsx");

            Thread.sleep(2000); // Espera para observar el resultado

            System.out.println("Formulario guardado exitosamente.");
        } catch (TimeoutException e) {
            System.out.println("Error al guardar el formulario: " + e.getMessage());
        }
    }
}
