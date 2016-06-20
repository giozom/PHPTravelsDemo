package com.giozom.travels.login;

import com.giozom.travels.domain.TravelsAccount;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;




public class LoginTest {

    ExtentReports report;
    ExtentTest logger;

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
        travelsAccount = new TravelsAccount();

    }

    @Test
    public void loginToTravels(){
        report = new ExtentReports("/Users/garsenius/github/PHPTravelsDemo/target/extent-reports/TravelsTestReport.html");

        logger = report.startTest("Verify login is successful");

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

        LoginPage loginPage = new LoginPage(driver);
        loginPage.typeUsername(travelsAccount.userName);
        loginPage.typePassword(travelsAccount.userPassword);
        loginPage.signIn();

        logger.log(LogStatus.INFO, "Logged in successful");


        wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.titleContains("My Account"));

        //verify title after login
        assertThat(driver.getTitle(), is("My Account"));

        logger.log(LogStatus.PASS, "Title verified");
        report.endTest(logger);
        report.flush();
        driver.get("/Users/garsenius/github/PHPTravelsDemo/target/extent-reports/TravelsTestReport.html");

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

    }



}
