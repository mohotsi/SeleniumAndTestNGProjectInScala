package com.ilabquality.page

import com.ilabquality.WebElementImplicit.Cuddle
import org.openqa.selenium.remote.RemoteWebDriver

import scala.util.Random

case class JobPage(applicantName: String =Random.alphanumeric.take(20).mkString,
                   email:String=Random.alphanumeric.take(10).mkString+"@ilabquality.com",
                   cellNumber: String =  Random.nextInt(999999999).toString.replace("\\d{9}","0\\d{2} \\d{3} \\d{4}").mkString,
                   message:String=Random.alphanumeric.take(80).mkString,
                   url:String="C:\\Users\\thapelo.mohotsi\\Workspace\\I_labAutomationProject\\src\\main\\resources\\files\\Automation_Assessment.pdf")(implicit driver:RemoteWebDriver) {




  def provideMessage(text:String)=messageElement.sendKeys(text)
  def clickSendApplication()=sendButton.clickRetry()

  def fillFormButDoNotUploadFile() = {

    clickApplyOnline()
    provideApplicantName()
    provideEmail()
    provideCellNumber()
    provideMessage(message)

    clickSendApplication()

  }

  private def provideCellNumber()={
    driver.findElementById("phone").sendKeys(cellNumber)

  }
  private def sendButton=driver.findElementByXPath("//input[@value='Send Application']")
  private  def applyOnline=driver.findElementByPartialLinkText("Apply Online")
  private def provideEmail()= driver.findElementById("email").sendKeys(email)

  private  def messageElement=driver.findElementById("message")
  private  def applicantNameElement=driver.findElementById("applicant_name")
  private  def provideApplicantName() = applicantNameElement.waitForIt().sendKeys(applicantName)
  private  def clickApplyOnline() = applyOnline.scrollTo().clickRetry()





}