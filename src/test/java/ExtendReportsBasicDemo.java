import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class ExtendReportsBasicDemo {
    public static void main(String[] args) {
        ExtentHtmlReporter htmlReporter= new ExtentHtmlReporter("extentHTml.html");
        ExtentReports extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        ExtentTest test1= extent.createTest("Google Search Test One","Sample description");
        DesiredCapabilities capabilities= new DesiredCapabilities();
        capabilities.setCapability("ignoreProtectedModeSettings",true);
        WebDriverManager.iedriver().setup();
        WebDriverManager.iedriver().setup();
        WebDriver driver= new InternetExplorerDriver();

         test1.log(Status.INFO,"Starting Test case");

        driver.get("https://web.facebook.com/");
        test1.pass("Navigated to facebook.com");

        driver.findElement(By.name("email")).sendKeys("0783079016");
        test1.pass("Enter User Name");
        driver.findElement(By.name("pass")).sendKeys("@@Habbakkuk2v2");
        test1.pass("Enter password");
        driver.findElement(By.name("login")).sendKeys(Keys.RETURN);
        test1.pass("click enter button to login");
        driver.close();;
        driver.quit();
        test1.pass("closed browser");
        test1.info("complted test");



        ExtentTest test2= extent.createTest("Google Search Test Two","Sample description");

        test1.log(Status.INFO,"Starting Test case");
        WebDriverManager.firefoxdriver().setup();
WebDriver driver2= new FirefoxDriver();
        driver2.get("https://web.facebook.com/");
        test1.pass("Navigated to facebook.com");

        driver2.findElement(By.name("email")).sendKeys("0783079016");
        test2.pass("Enter User Name");
        driver2.findElement(By.name("pass")).sendKeys("@@Habbakkuk2v2");
        test2.pass("Enter password");
        driver2.findElement(By.name("login")).sendKeys(Keys.RETURN);
        test2.fail("click enter button to login");
        driver2.close();

        test2.pass("closed browser");
        test2.info("complted test");
        extent.flush();

    }
}
