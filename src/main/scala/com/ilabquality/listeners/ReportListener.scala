package com.ilabquality.listeners

import com.ilabquality.SeleniumTestÉxecuter
import com.ilabquality.Config.property
import com.relevantcodes.extentreports.{ExtentReports, ExtentTest, LogStatus}
import org.testng.xml.XmlSuite
import org.testng.{IReporter, ISuite, ITestResult}

import java.util

class ReportListener extends IReporter {

  override def generateReport(xmlSuites: util.List[XmlSuite], suites: util.List[ISuite], outputDirectory: String): Unit = {

    suites.forEach { suite =>
      val suiteResults = suite.getResults
      val extent: ExtentReports = new ExtentReports(System.getProperty("user.dir") + s"/target/test-output/${suite.getName.replace(" ", "_")}Report.html", true)
      extent.addSystemInfo("url", property.getProperty("url"))
      extent.addSystemInfo("browser", property.getProperty("browser"))
      extent.addSystemInfo("suite", suite.getName)

      suiteResults.values().forEach { sr =>
        val tc = sr.getTestContext()
        tc.getFailedTests().getAllResults.forEach(results =>
          putTestScriptResults(results, extent)
        )
        tc.getPassedTests().getAllResults.forEach(results =>
          putTestScriptResults(results, extent)
        )
        extent.flush()
        extent.close()
      }

    }

  }

  def putTestScriptResults(results: ITestResult, extent: ExtentReports) = {
    val selenium = results.getInstance().asInstanceOf[SeleniumTestÉxecuter]
    import selenium._
    val extentTest: ExtentTest = extent.startTest(results.getTestClass.getName + "." + results.getMethod.getMethodName);
    driver.manage().window().fullscreen()
    results.getStatus() match {
      case ITestResult.FAILURE =>
        extentTest.log(LogStatus.FAIL, "Test Case failed " + results.getName)
        extentTest.log(LogStatus.FAIL, "Test Case failed " + results.getThrowable)
        val screenshotPath = getScreenShot(results.getName());
        extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(screenshotPath))
      case ITestResult.SUCCESS =>
        extentTest.log(LogStatus.PASS, "Test Case passed " + results.getName)
      case ITestResult.SKIP => extentTest.log(LogStatus.SKIP, "Test Case SKIPPED IS " + results.getName())

    }
    extent.endTest(extentTest)

  }
}
