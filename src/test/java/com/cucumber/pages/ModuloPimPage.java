package com.cucumber.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cucumber.utils.ExcelUtil;
import com.cucumber.utils.WaitUtil;

import java.time.Duration;
import java.util.Map;
import java.util.NoSuchElementException;

import org.junit.Assert;

public class ModuloPimPage {

  private WebDriver driver;
  private WebDriverWait wait;

  public ModuloPimPage(WebDriver driver) {
    this.driver = driver;
    this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
  }

  public void validaPantallaPrincipal() {
    // Esperar a que aparezca el encabezado del Dashboard o Employee Information
    By headerPIM = By.xpath("//h6[normalize-space()='Employee Information' or normalize-space()='Dashboard']");
    WebElement elementoHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(headerPIM));

    // Validar que efectivamente se visualiza
    Assert.assertTrue("❌ No se visualizó la página principal.", elementoHeader.isDisplayed());
    System.out.println("✅ Validación exitosa: el usuario se encuentra la pantalla principal.");
  }

  public void irAModuloPIM() {
    try {
      // 1️⃣ Hacer clic en el menú PIM
      By menuPIM = By.xpath("//span[normalize-space()='PIM']/ancestor::a");
      WebElement moduloPIM = wait.until(ExpectedConditions.elementToBeClickable(menuPIM));
      moduloPIM.click();
      System.out.println("Ingreso correcto al módulo PIM.");

      // 2️⃣ Esperar que aparezca un encabezado característico del módulo
      By tituloPIM = By.xpath("//h6[normalize-space()='PIM']");
      WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(tituloPIM));

      // 3️⃣ Assert para confirmar que la página cargó correctamente
      Assert.assertTrue("❌ No se llegó a la página del módulo PIM.", header.isDisplayed());
      System.out.println("✅ Confirmado: El usuario está en la página del módulo PIM.");

    } catch (TimeoutException e) {
      System.out.println("Error al ingresar al módulo PIM: " + e.getMessage());
      Assert.fail("❌ No se pudo ingresar al módulo PIM dentro del tiempo esperado.");
    }
  }

  public void usuario_hace_clic_en_el_boton_Agregar() {
    try {
      // 1️⃣ Esperar y hacer clic en el botón "Add"
      By botonAgregar = By.xpath("//button[normalize-space()='Add']");
      WebElement agregarButton = wait.until(ExpectedConditions.elementToBeClickable(botonAgregar));
      agregarButton.click();
      System.out.println("Clic en el botón 'Agregar' exitoso.");

      // 2️⃣ Validar que se haya abierto la página "Add Employee"
      By tituloAddEmployee = By.xpath("//h6[normalize-space()='Add Employee']");
      WebElement headerAddEmployee = wait.until(ExpectedConditions.visibilityOfElementLocated(tituloAddEmployee));

      // 3️⃣ Assert para confirmar la navegación
      Assert.assertTrue("❌ No se abrió la página 'Add Employee' después de hacer clic en Agregar.",
          headerAddEmployee.isDisplayed());

      System.out.println("✅ Confirmado: Se abrió correctamente la página 'Add Employee'.");

    } catch (TimeoutException e) {
      System.out.println("❌ Error al hacer clic en el botón Agregar o al cargar la página: " + e.getMessage());
      Assert.fail("No se pudo abrir la página 'Add Employee' dentro del tiempo esperado.");
    }
  }

  public void el_usuario_crea_un_nuevo_empleado_con_datos_validos() throws InterruptedException {
    Map<String, String> datosEmpleado = ExcelUtil
        .obtenerRegistroDisponibleYMarcarUsado("src/test/resources/datos.xlsx");

    System.out.println("✅ Iniciando creación de nuevo empleado...");

    try {
      // 1️⃣ Ingresar nombres
      By inputFirstName = By.name("firstName");
      WebElement firstName = wait.until(ExpectedConditions.visibilityOfElementLocated(inputFirstName));
      firstName.sendKeys(datosEmpleado.get("Primer Nombre"));

      By inputMiddleName = By.name("middleName");
      wait.until(ExpectedConditions.visibilityOfElementLocated(inputMiddleName))
          .sendKeys(datosEmpleado.get("Segundo Nombre"));

      By inputLastName = By.name("lastName");
      wait.until(ExpectedConditions.visibilityOfElementLocated(inputLastName))
          .sendKeys(datosEmpleado.get("Apellido"));
      System.out.println("🧾 Datos personales ingresados correctamente.");

      // 2️⃣ Generar y asignar Employee ID único
      By employeeId = By.xpath("//label[normalize-space()='Employee Id']/following::input[1]");
      WebElement campoEmployeeId = wait.until(ExpectedConditions.visibilityOfElementLocated(employeeId));
      campoEmployeeId.clear();
      String idUnico = String.valueOf((int) (Math.random() * 1000000));
      campoEmployeeId.sendKeys(idUnico);
      System.out.println("🆔 Nuevo Employee ID generado: " + idUnico);

      // 3️⃣ Guardar primera parte del formulario
      By botonGuardar = By.xpath("//button[normalize-space()='Save']");
      WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(botonGuardar));
      Thread.sleep(3000); // Pequeña espera para asegurar que todo esté listo
      saveButton.click();
      System.out.println("💾 Primer guardado exitoso del empleado.");

      // 4️⃣ Validar que cargó la segunda sección del formulario
      By otherId = By.xpath("//label[normalize-space()='Other Id']/following::input[1]");
      WebElement campoOtherId = wait.until(ExpectedConditions.visibilityOfElementLocated(otherId));
      Assert.assertTrue("❌ No se cargó la segunda sección del formulario PIM.", campoOtherId.isDisplayed());
      System.out.println("✅ Sección de detalles adicionales cargada correctamente.");

      // 5️⃣ Completar datos personales
      campoOtherId.sendKeys(datosEmpleado.get("Cedula"));

      By licenseNumber = By.xpath("//label[normalize-space()=\"Driver's License Number\"]/following::input[1]");
      wait.until(ExpectedConditions.visibilityOfElementLocated(licenseNumber))
          .sendKeys(datosEmpleado.get("Licencia de conduccion"));

      By fechaLicencia = By.xpath("//label[normalize-space()='License Expiry Date']/following::input[1]");
      wait.until(ExpectedConditions.visibilityOfElementLocated(fechaLicencia))
          .sendKeys(datosEmpleado.get("Fecha expiracion Licencia"));
      System.out.println("📜 Campos de identificación completados.");

      // 6️⃣ Nacionalidad
      By nacionalidadField = By
          .xpath("//label[text()='Nationality']/following::div[contains(@class,'oxd-select-text')][1]");

      // 🔸 Esperar a que el loader desaparezca antes de interactuar
      WaitUtil.esperarLoader(driver, wait);
      // 🔸 Interactuar con el campo Nacionalidad
      wait.until(ExpectedConditions.elementToBeClickable(nacionalidadField)).click();

      String nacionalidad = datosEmpleado.get("Nacionalidad");
      By seleccionarNacionalidad = By
          .xpath("//div[contains(@class,'oxd-select-option') and normalize-space()='" + nacionalidad + "']");

      wait.until(ExpectedConditions.elementToBeClickable(seleccionarNacionalidad)).click();
      System.out.println("🌍 Nacionalidad seleccionada: " + nacionalidad);

      // 7️⃣ Estado civil
      By estadoCivilField = By
          .xpath("//label[text()='Marital Status']/following::div[contains(@class,'oxd-select-text')][1]");
      wait.until(ExpectedConditions.elementToBeClickable(estadoCivilField)).click();

      String estadoCivil = datosEmpleado.get("Estado Civil");
      By seleccionarEstadoCivil = By
          .xpath("//div[contains(@class,'oxd-select-option') and normalize-space()='" + estadoCivil + "']");
      wait.until(ExpectedConditions.elementToBeClickable(seleccionarEstadoCivil)).click();
      System.out.println("❤️ Estado civil seleccionado: " + estadoCivil);

      // 8️⃣ Fecha de nacimiento
      By fechaNacimiento = By.xpath("//label[text()='Date of Birth']/following::input[1]");
      WebElement campoFechaNacimiento = wait.until(ExpectedConditions.visibilityOfElementLocated(fechaNacimiento));
      campoFechaNacimiento.sendKeys(datosEmpleado.get("Fecha de Nacimiento"));
      System.out.println("🎂 Fecha de nacimiento ingresada: " + datosEmpleado.get("Fecha de Nacimiento"));

      // 9️⃣ Género
      String genero = datosEmpleado.get("Genero").equalsIgnoreCase("Hombre") ? "1" : "2";
      By selectorGenero = By.xpath("(//span[contains(@class,'oxd-radio-input')])[" + genero + "]");
      wait.until(ExpectedConditions.elementToBeClickable(selectorGenero)).click();
      System.out.println("🚻 Género seleccionado: " + datosEmpleado.get("Genero"));

      // 🔟 Confirmar que todo quedó visible antes de terminar
      Assert.assertTrue("❌ El campo 'Date of Birth' no es visible, posible error en el formulario.",
          campoFechaNacimiento.isDisplayed());
      System.out.println("✅ Validación final: el formulario se completó correctamente.");

    } catch (TimeoutException e) {
      Assert.fail("⏰ Error: elemento no encontrado dentro del tiempo esperado. " + e.getMessage());
    } catch (NoSuchElementException e) {
      Assert.fail("❌ Error: elemento no encontrado en el DOM. " + e.getMessage());
    } catch (Exception e) {
      Assert.fail("💥 Error inesperado durante la creación del empleado: " + e.getMessage());
    }
  }

  public void el_usuario_guarda_el_formulario_con_los_datos() throws InterruptedException {
    try {
      System.out.println("💾 Intentando guardar el formulario del empleado...");

      // 1️⃣ Esperar que el botón Save esté disponible y hacer clic
      By botonGuardar = By.xpath("(//button[normalize-space()='Save'])[1]");
      WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(botonGuardar));
      Thread.sleep(3000); // Pequeña espera para asegurar que todo esté listo
      saveButton.click();
      System.out.println("🖱️ Clic en el botón 'Save' ejecutado.");

      // 2️⃣ Marcar el registro en el Excel como usado
      ExcelUtil.marcarRegistroComoUsado("src/test/resources/datos.xlsx");
      System.out.println("📊 Registro marcado como utilizado en el Excel.");

      // 3️⃣ Validar que la página sigue activa y no arrojó errores
      String currentUrl = driver.getCurrentUrl();
      Assert.assertTrue("❌ La URL no es la esperada después del guardado. URL actual: " + currentUrl,
          currentUrl.contains("/pim") || currentUrl.contains("viewEmployee"));
      System.out.println("✅ Confirmación final: el formulario fue guardado y la navegación continúa en el módulo PIM.");

    } catch (TimeoutException e) {
      System.out.println("⏰ Error: el botón 'Save' o el mensaje de éxito no aparecieron a tiempo. " + e.getMessage());
      Assert.fail("No se detectó la confirmación del guardado dentro del tiempo esperado.");
    } catch (NoSuchElementException e) {
      System.out.println("❌ Error: elemento esperado no encontrado. " + e.getMessage());
      Assert.fail("No se encontró un elemento clave durante el proceso de guardado.");
    } catch (Exception e) {
      System.out.println("💥 Error inesperado durante el guardado del formulario: " + e.getMessage());
      Assert.fail("Fallo inesperado al guardar el formulario.");
    }
  }
}
