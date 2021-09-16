package com.ilabquality.test.REQ1000

import com.ilabquality.SeleniumTestÉxecuter
import com.ilabquality.WebElementImplicit.Cuddle
import com.ilabquality.page.{CareersPage, HomePage, JobPage}
import org.openqa.selenium.By
import org.testng.AssertJUnit
import org.testng.annotations.Test

import scala.collection.convert.ImplicitConversions.`collection AsScalaIterable`
class IlabPass extends SeleniumTestÉxecuter{
@Test
def one: Unit ={

  openPage()

  HomePage().careers.waitForIt(30).scrollTo().clickRetry()
  val careerPage= CareersPage()
    careerPage.selectLocation("South Africa")
careerPage.currentOpening.get(0).clickRetry()
  JobPage().fillFormButDoNotUploadFile()

  AssertJUnit.assertTrue(
    """ is validation message "You need to upload at least one file" should be displayed"  """
    ,driver.findElementByClassName("wpjb-errors").findElements(By.tagName("li")).toList.filter(_.getText==("You need to upload at least one file.")).nonEmpty )





}
}
