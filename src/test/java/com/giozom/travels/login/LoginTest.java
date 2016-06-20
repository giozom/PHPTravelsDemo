package com.giozom.travels.login;

import com.giozom.travels.domain.TravelsAccount;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


/**
 * Created by garsenius on 20/06/2016.
 */
public class LoginTest {

    @Test
    public void loginToTravels() {

        WebDriver driver = new FirefoxDriver();

        //domain object for Travels application
        TravelsAccount travelsAccount = new TravelsAccount();

        //using travels object to get the travels_url
        driver.get(travelsAccount.travels_url +"/login");

        //get the web elements on the login page
        WebElement txt_username = driver.findElement(By.name("username"));
        WebElement txt_password = driver.findElement(By.name("password"));
        WebElement loginButton = driver.findElement(By.xpath(".//*[@id='loginfrm']/div/div[5]/div/div/div[1]/button"));

        //login
        txt_username.sendKeys(travelsAccount.userName);
        txt_password.sendKeys(travelsAccount.userPassword);
        loginButton.click();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        //verify title after login
        assertThat(driver.getTitle(), is("My Account"));

        driver.get(travelsAccount.travels_url + "/account/logout");
        driver.quit();








    }



}
