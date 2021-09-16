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
      val suiteName = suite.getResults
      val suiteResults = suite.getResults
      val extent: ExtentReports = new ExtentReports(System.getProperty("user.dir") + s"/test-output/${suite.getName.replace(" ", "_")}Report.html", true)
      extent.addSystemInfo("url", property.getProperty("url"))
      extent.addSystemInfo("browser", property.getProperty("browser"))
      extent.addSystemInfo("suite", suite.getName)

      suiteResults.values().forEach { sr =>
        val tc = sr.getTestContext()
        tc.getFailedTests().getAllResults.forEach(results =>
          showResults(results, extent)
        )
        tc.getPassedTests().getAllResults.forEach(results =>
          showResults(results, extent)
        )
        extent.flush()
        extent.close()
      }

    }

  }

  def showResults(results: ITestResult, extent: ExtentReports) = {
    val selenium = results.getInstance().asInstanceOf[SeleniumTestÉxecuter]
    import selenium._
    val extentTest: ExtentTest = extent.startTest(results.getTestClass.getName + "." + results.getMethod.getMethodName);
    if (results.getStatus() == ITestResult.FAILURE) {
      extentTest.log(LogStatus.FAIL, "Test Case failed " + results.getName)

      extentTest.log(LogStatus.FAIL, "Test Case failed " + results.getThrowable)

      val screenshotPath = getScreenShot(results.getName());

      extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(screenshotPath))
    }
    else if (results.getStatus == ITestResult.SUCCESS) {
      extentTest.log(LogStatus.PASS, "Test Case passed " + results.getName)
    }
    else if (results.getStatus == ITestResult.SKIP) {
      extentTest.log(LogStatus.SKIP, "Test Case SKIPPED IS " + results.getName())
    }
    extent.endTest(extentTest)


  }
}
