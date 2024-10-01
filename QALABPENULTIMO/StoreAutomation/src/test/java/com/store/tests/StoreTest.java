package com.store.tests;

import com.store.pages.LoginPage;
import com.store.pages.ProductPage;
import com.store.utils.ReportGenerator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class StoreTest {
    WebDriver driver;
    ReportGenerator reportGenerator;

    @BeforeMethod
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Carlos\\Desktop\\QALABPENULTIMO\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://qalab.bensg.com/store");

        reportGenerator = new ReportGenerator();
        reportGenerator.setupReport();  // Inicializa el reporte
    }

    @Test
    public void testLoginSuccessful() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("validUser", "validPassword");

        boolean result = driver.getTitle().contains("Dashboard");
        reportGenerator.generateReport("testLoginSuccessful", result);  // Genera el reporte
        Assert.assertTrue(result);
    }

    @Test
    public void testLoginFailure() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("invalidUser", "invalidPassword");

        boolean result = driver.findElement(By.id("error")).isDisplayed();
        reportGenerator.generateReport("testLoginFailure", result);  // Genera el reporte
        Assert.assertTrue(result);
    }

    @Test
    public void testInvalidCategory() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("validUser", "validPassword");

        ProductPage productPage = new ProductPage(driver);
        productPage.navigateToCategory("Autos", "Cars");

        boolean result = driver.findElement(By.id("no-products")).isDisplayed();
        reportGenerator.generateReport("testInvalidCategory", result);  // Genera el reporte
        Assert.assertTrue(result);
    }

    @AfterMethod
    public void teardown() {
        reportGenerator.flushReport();  // Finaliza y guarda el reporte
        driver.quit();
    }
}