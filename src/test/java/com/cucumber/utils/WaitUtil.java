package com.cucumber.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtil {
  public static void esperarLoader(WebDriver driver, WebDriverWait wait) {
    try {
      wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.oxd-form-loader")));
      System.out.println("⏳ Loader finalizado, continuando con la acción...");
    } catch (Exception e) {
      System.out.println("⚠️ No se detectó loader activo o ya desapareció.");
    }
  }
}
