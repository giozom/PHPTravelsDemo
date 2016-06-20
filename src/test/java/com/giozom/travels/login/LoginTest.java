package com.giozom.travels.login;

import com.giozom.travels.domain.TravelsAccount;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


/**
 * Created by garsenius on 20/06/2016.
 */
public class LoginTest {

    public static final String TRAVELS_LOGIN_ENDPOINT = "/login";
    public static final String TRAVELS_LOGOUT_ENDPOINT = "/account/logout";

    public static WebDriver driver;

    private TravelsAccount travelsAccount;


    @BeforeClass
    public static void startDriver(){
        driver = new FirefoxDriver();
        driver.manage().window().maximize();

    }

    @Before
    public void prepForTest(){
        travelsAccount = new TravelsAccount();

    }

    @Test
    public void loginToTravels(){

        final String TRAVELS_LOGIN = travelsAccount.travels_url + TRAVELS_LOGIN_ENDPOINT;


        //page locators
        By username_text_field = By.name("username");
        By password_text_field = By.name("password");
        By login_button = By.xpath(".//*[@id='loginfrm']/div/div[5]/div/div/div[1]/button");

        //goto to login page
        driver.get(TRAVELS_LOGIN);
        System.out.println(TRAVELS_LOGIN);

        //setup page controls
        WebElement txt_username = driver.findElement(username_text_field);
        WebElement txt_password = driver.findElement(password_text_field);
        WebElement loginButton = driver.findElement(login_button);

        //login
        txt_username.sendKeys(travelsAccount.userName);
        txt_password.sendKeys(travelsAccount.userPassword);
        loginButton.click();

        //wait for page to load
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        //verify title after login
        assertThat(driver.getTitle(), is("My Account"));


    }

    @After
    public void closeSession() {
        final String TRAVELS_LOGOUT = travelsAccount.travels_url + TRAVELS_LOGOUT_ENDPOINT;
        //click logout
        driver.get(TRAVELS_LOGOUT);
    }


    @AfterClass
    public static void quitDriver(){
        //quit browser
        driver.quit();
    }



}
