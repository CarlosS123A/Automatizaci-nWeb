package com.store.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage {
    WebDriver driver;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateToCategory(String category, String subcategory) {
        driver.findElement(By.linkText(category)).click();
        driver.findElement(By.linkText(subcategory)).click();
    }

    public void addProductToCart() {
        driver.findElement(By.cssSelector(".product")).click();
        driver.findElement(By.cssSelector(".add-to-cart")).click();
    }
}