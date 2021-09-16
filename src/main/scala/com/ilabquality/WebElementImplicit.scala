package com.ilabquality

import org.openqa.selenium.WebElement
import org.openqa.selenium.remote.RemoteWebDriver
import org.openqa.selenium.support.ui.{ExpectedConditions, Select, WebDriverWait}

import java.util.concurrent.TimeUnit
import scala.util.Random

object WebElementImplicit {
  implicit class Cuddles(webElements: List[WebElement])(implicit driver:RemoteWebDriver){
    def waitUntilContains(itemText:String,seconds:Int=10):List[WebElement]={
           if(seconds==0)
             throw  new NoSuchElementException("the webElements doesn't contain the WebElement with text = "+itemText)
          if(webElements.filter(_.getText==itemText).nonEmpty)
            webElements
            else {
            Thread.sleep(1000)
            waitUntilContains(itemText,seconds-1)
          }
    }
  }
  implicit class Cuddle(webElement: =>WebElement)(implicit driver:RemoteWebDriver){
    def scrollTo()={
      driver.executeScript("arguments[0].scrollIntoView(true);", webElement);
      webElement
    }
    def waitForIt(timeOutInSeconds: Int = 10):WebElement = {
      driver.manage().timeouts() implicitlyWait(timeOutInSeconds, TimeUnit.SECONDS)
      webElement
    }
    def isDisplayed(timeOutInSeconds: Int = 10) = {
      val wait: WebDriverWait = new WebDriverWait(driver, timeOutInSeconds);
      wait.until(ExpectedConditions.invisibilityOf(webElement))
      webElement.click()

    }

    def clickRetry(timeOutInSeconds: Int = 10) = {
      val wait: WebDriverWait = new WebDriverWait(driver, timeOutInSeconds);
      wait.until(ExpectedConditions.elementToBeClickable(webElement))
      webElement.click()

    }
    def select(shownText:String)={
      val select= new Select(webElement)
      select.selectByVisibleText(shownText)
    }
    def highlight()={
      driver.executeScript(s"arguments[0].setAttribute('style', 'background: GreenYellow; border: GreenYellow;color:black;');", webElement)
    }
    def circle()(implicit driver: RemoteWebDriver): Unit = {
      val width  = webElement.getSize.width + 10;
      val height = webElement.getSize.height + 10
      val left   = webElement.getLocation.x - 5;
      val top    = webElement.getLocation.y
      val bug    = Random.nextString(1000).take(4)
      driver.executeScript(
        s"""var node = document.createElement("div");node.setAttribute("class","$bug");arguments[0].appendChild(node);""".stripMargin,
        driver.findElementByXPath("//body")
      )

    }



  }
}
