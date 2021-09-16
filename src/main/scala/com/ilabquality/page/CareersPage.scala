package com.ilabquality.page

import com.ilabquality.WebElementImplicit.Cuddle
import org.openqa.selenium.By
import org.openqa.selenium.remote.RemoteWebDriver

import scala.collection.convert.ImplicitConversions.`collection AsScalaIterable`

case class CareersPage()(implicit driver:RemoteWebDriver) {
  def selectLocation(country:String)=driver.findElementsByClassName("vc_general").filter(_.getText==country).head.clickRetry();

 def currentOpening= driver.findElementByClassName("wpjb-job-list").waitForIt().findElements(By.tagName("a"))
}
