package REQ3434;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.AssertJUnit;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.asserts.Assertion;


@Listeners(TestListener.class)
public class ExtendReportDeomWithTestNG {

    final static String filePath = "Extent.html";
    ExtentHtmlReporter htmlReporter;
    ExtentReports extent;
    WebDriver  driver;
    XSSFWorkbook workbook;

    Logger logger=  LogManager.getLogger(ExtendReportDeomWithTestNG.class);

    @BeforeTest
    public void setUp(){
        htmlReporter= new ExtentHtmlReporter("extentHTml.html");
       extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        WebDriverManager.chromedriver().setup();
       driver=new ChromeDriver();
    }

    @Test
    public void test1() throws InterruptedException {
        ExtentTest test1= extent.createTest("Google Search Test One","Sample description");
        logger.info("logger information,hahaha");
        driver.get("https://github.com/SeleniumHQ/selenium/wiki/DesiredCapabilities");
        Thread.sleep(3000);
    }
    @Test
    public void test2() throws InterruptedException {
        ExtentTest test1= extent.createTest("Google Search Test One","Sample description");
        logger.info("logger information,hahaha");
        driver.get("https://github.com/SeleniumHQ/selenium/wiki/DesiredCapabilities");
        AssertJUnit.assertTrue(1==3);
        Thread.sleep(3000);
    }

    @AfterTest
    public void tearDown(){
        driver.close();
        driver.quit();
    }

    public static class ThapeloLIstener implements ITestListener {
        @Override
        public void onTestStart(ITestResult result) {
            System.out.println("Statrt test");
        }


    }
}
