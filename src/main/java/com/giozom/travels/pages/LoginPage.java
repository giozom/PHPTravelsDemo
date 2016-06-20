package com.giozom.travels.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

    public static WebDriver driver;

    By username_text_field = By.name("username");
    By password_text_field = By.name("password");
    By login_button = By.xpath(".//*[@id='loginfrm']/div/div[5]/div/div/div[1]/button");
    By messageHolder = By.xpath(".//*[@id='loginfrm']/div");



    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void typeUsername(String userName) {
        WebElement txt_username = driver.findElement(username_text_field);
        txt_username.sendKeys(userName);
    }


    public void typePassword(String userPassword) {
        WebElement txt_password = driver.findElement(password_text_field);
        txt_password.sendKeys(userPassword);
    }

    public void signIn() {
        WebElement loginButton = driver.findElement(login_button);
        loginButton.click();

    }

    public  boolean loginUnsuccessfulMessageIsPresent(){
        WebElement alertMsg = driver.findElement(messageHolder);
        try{
            alertMsg.getText().contains("Invalid Email or Password");
            return true;
        }catch (NoSuchElementException e){
            return false;
        }
    }
    

}
