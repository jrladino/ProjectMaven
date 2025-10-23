package com.cucumber.pages;

import org.openqa.selenium.support.PageFactory;

import com.cucumberpom.base.BaseTest;

public class HomePage extends BaseTest {

    public HomePage() {
        PageFactory.initElements(driver, this);
    }

    public String getHomePageTitle() {
        return driver.getTitle();
    }

}
