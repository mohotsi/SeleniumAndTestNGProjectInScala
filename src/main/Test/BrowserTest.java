import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.Listeners;


public class BrowserTest {


    public static void main(String[] args) throws InterruptedException {
//        System.setProperty("webdriver.gecko.driver","./src/WebDriver/geckodriver.exe");
//        WebDriver driver= new FirefoxDriver();
        // driver.get("https://www.selenium.dev/");
//        System.setProperty("webdriver.chrome.driver","./src/WebDriver/chromedriver.exe");
//        WebDriver driver= new ChromeDriver();


        WebDriverManager.iedriver().setup();
        WebDriver driver= new FirefoxDriver();

        driver.get("https://www.selenium.dev/");
        Thread.sleep(4000);
        driver.close();


    }

}
