package com.cucumber.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By userField = By.name("username");
    private By passField = By.name("password");
    private By loginButton = By.cssSelector("button[type='submit']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void open(String url) {
        driver.get(url);
        wait.until(ExpectedConditions.visibilityOfElementLocated(userField));
    }

    public boolean estaEnLoginPage() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(userField)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void login(String user, String pass) {
        wait.until(ExpectedConditions.elementToBeClickable(userField)).sendKeys(user);
        wait.until(ExpectedConditions.elementToBeClickable(passField)).sendKeys(pass);
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
        wait.until(ExpectedConditions.urlContains("/dashboard"));
    }

    public boolean isUserLoggedIn() {
        try {
            By dashboardHeader = By.xpath("//h6[text()='Dashboard']");
            wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardHeader));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
