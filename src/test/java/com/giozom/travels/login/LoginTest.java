package com.giozom.travels.login;

import com.giozom.travels.domain.TravelsAccount;
import com.giozom.travels.pages.LoginPage;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import com.relevantcodes.extentreports.ExtentReports;





public class LoginTest {

    public static ExtentReports report;
    public static ExtentTest logger;

    public static final String TRAVELS_LOGIN_ENDPOINT = "/login";
    public static final String TRAVELS_LOGOUT_ENDPOINT = "/account/logout";

    public static WebDriver driver;
    public static WebDriverWait wait;

    private TravelsAccount travelsAccount;


    @BeforeClass
    public static void startDriver(){
        driver = new FirefoxDriver();
        driver.manage().window().maximize();

    }

    @Before
    public void prepForTest(){

        //domain object for travel account
        travelsAccount = new TravelsAccount();

        //using extent reports to log and report on progress
        report = new ExtentReports("target/extent-reports/TravelsTestReport.html", true);
        logger = report.startTest("Test starting..");

    }

    @Test
    public void loginToTravels(){

        final String TRAVELS_LOGIN = travelsAccount.travels_url + TRAVELS_LOGIN_ENDPOINT;

        //goto to login page
        driver.get(TRAVELS_LOGIN);
        System.out.println(TRAVELS_LOGIN);
        logger.log(LogStatus.INFO, "Browser launched");


        //wait for page to load
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //verify title before login
        assertThat(driver.getTitle(), is("Login"));
        logger.log(LogStatus.INFO, "'Login' found in window title");

        LoginPage loginPage = new LoginPage(driver);

        loginPage.typeUsername(travelsAccount.userName);
        logger.log(LogStatus.INFO, "Username entered");

        loginPage.typePassword(travelsAccount.userPassword);
        logger.log(LogStatus.INFO, "Password entered");

        loginPage.signIn();
        logger.log(LogStatus.INFO, "Login button clicked");

        logger.log(LogStatus.INFO, "User successfully logged in");

        //add a delay
        wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.titleContains("My Account"));

        //verify title after login
        assertThat(driver.getTitle(), is("My Account"));

        logger.log(LogStatus.PASS, "'Successful Login Test Completed");

    }

    @Test
    public void failToLoginToTravels(){

        final String TRAVELS_LOGIN = travelsAccount.travels_url + TRAVELS_LOGIN_ENDPOINT;

        //goto to login page
        driver.get(TRAVELS_LOGIN);
        System.out.println(TRAVELS_LOGIN);
        logger.log(LogStatus.INFO, "Browser launched");

        //wait for page to load
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //verify title before login
        assertThat(driver.getTitle(), is("Login"));
        logger.log(LogStatus.INFO, "'Login' found in window title");

        LoginPage loginPage = new LoginPage(driver);

        loginPage.typeUsername(travelsAccount.userName);
        logger.log(LogStatus.INFO, "Username entered");

        loginPage.typePassword("wagaya"); //incorrect password test
        logger.log(LogStatus.INFO, "Password entered");

        loginPage.signIn();
        logger.log(LogStatus.INFO, "Login button clicked");

        logger.log(LogStatus.INFO, "User failed to log in");

        loginPage.loginUnsuccessfulMessageIsPresent();
        logger.log(LogStatus.INFO, "Error message displayed successfully");

        //verify title after failed login is still 'Login'
        assertThat(driver.getTitle(), is("Login"));

        logger.log(LogStatus.PASS, "'Failed Test Successfully completed");
    }

    @After
    public void closeSession() {
        final String TRAVELS_LOGOUT = travelsAccount.travels_url + TRAVELS_LOGOUT_ENDPOINT;
        //click logout
        driver.get(TRAVELS_LOGOUT);
        System.out.println(TRAVELS_LOGOUT);
    }


    @AfterClass
    public static void quitDriver(){
        //quit browser
        driver.quit();

        //endtest
        report.endTest(logger);
        report.flush();
        System.out.println(report);

    }

}
