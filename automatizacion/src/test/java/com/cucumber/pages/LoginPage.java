package com.cucumber.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cucumberpom.base.BaseTest;

import org.junit.Assert;

public class LoginPage extends BaseTest {

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

    @FindBy(css = "input[name=\"username\"]")
    WebElement userName;

    @FindBy(css = "input[name=\"password\"]")
    WebElement password;

    public LoginPage() {
        PageFactory.initElements(driver, this);
    }

    public void waitForElementToBeVisible() throws InterruptedException {
        try{
            WebElement tituloLogin = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("h5[class='oxd-text oxd-text--h5 orangehrm-login-title']")
                    )
            );
            
            String titulo = tituloLogin.getText();
            String expectedTitle = "Login";
            Assert.assertEquals(expectedTitle, titulo);
        }
        catch(Exception e){
            System.out.println("El elemento no se encontro: " + e.getMessage());
        }
    }

    public HomePage el_usuario_ingresa_las_credenciales_validas() throws InterruptedException {
        String usuario = prop.getProperty("userName");
        String contrasena = prop.getProperty("password");

        doLogin(usuario, contrasena);
        return new HomePage();
    }

    public void doLogin(String usuario, String contrasena) throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(userName)).sendKeys(usuario);
        wait.until(ExpectedConditions.visibilityOf(password)).sendKeys(contrasena);

    }

}
