package com.ilabquality

import com.ilabquality.Config.property
import io.github.bonigarcia.wdm.WebDriverManager
import org.apache.commons.io.FileUtils
import org.openqa.selenium.OutputType
import org.openqa.selenium.chrome.{ChromeDriver, ChromeOptions}
import org.openqa.selenium.edge.EdgeDriver
import org.openqa.selenium.firefox.{FirefoxDriver, FirefoxOptions}
import org.openqa.selenium.ie.{InternetExplorerDriver, InternetExplorerOptions}
import org.openqa.selenium.opera.{OperaDriver, OperaOptions}
import org.openqa.selenium.remote.{DesiredCapabilities, RemoteWebDriver}
import org.testng.annotations.AfterTest

import java.io.File
import java.text.SimpleDateFormat
import java.util.Date

class SeleniumTestÉxecuter {



  //val extent= new ExtentReports(System.getProperty("user.dir")+"/test-output/ExtentReport.html",true)

  val browser = property.getProperty("browser")
  val headless = property.getProperty("headless").toBoolean


 implicit val driver=getDriver()



  @AfterTest
  def  endTest():Unit={

  }
  def getScreenShot(name:String): String ={
    val dateName= new SimpleDateFormat("yyyyMMddmmss").format(new Date())
   val source= driver.getScreenshotAs(OutputType.FILE)
   val destination="./FailedTestScreenshots/"+name+dateName+".png"
   val finalDestination=new File(destination);
    FileUtils.copyFile(source,finalDestination)
   finalDestination.getAbsolutePath

  }


    def openPage()={
      driver.get(property.getProperty("url"))
    }
    private  def getDriver():RemoteWebDriver= {


    browser.toLowerCase match {
      case "chrome" =>
         WebDriverManager.chromedriver().setup()
        val options = new ChromeOptions()

        new ChromeDriver(options)
      case "firefox" =>
        WebDriverManager.firefoxdriver().setup()
        val options = new FirefoxOptions()
        new FirefoxDriver(options)
      case "ie" =>
        var capabilities = getDesiredCapabilities
        capabilities.setCapability("ignoreProtectedModeSettings", true)
        capabilities.setCapability("ignoreZoomSetting", true)
        WebDriverManager.iedriver().setup()
        var options = new InternetExplorerOptions(capabilities)
        if (headless)
          throw new IllegalStateException("sorry doesn't suport headless mode")

        new InternetExplorerDriver(options)
      case "opera"  =>
        WebDriverManager.operadriver().setup()
        val options = new OperaOptions()
        if (headless)
          options.addArguments("--headless")
        new OperaDriver()
      case "edge" =>
        WebDriverManager.edgedriver().setup()
        new EdgeDriver();
      case _ => throw new IllegalArgumentException("unknown browser in config.properties")
    }

  }
  def getDesiredCapabilities:DesiredCapabilities=  property.getProperty("browser") match {
    case "ie"       => DesiredCapabilities.internetExplorer();
    case "ie11"     => DesiredCapabilities.internetExplorer();
    case "chrome"   => DesiredCapabilities.chrome();
    case "firefox"  => DesiredCapabilities.firefox();
    case "edge"     => DesiredCapabilities.edge();

  }







}
