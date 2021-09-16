package com.ilabquality.page

import org.openqa.selenium.remote.RemoteWebDriver

case class HomePage()(implicit driver:RemoteWebDriver) {
def careers=driver.findElementByLinkText("CAREERS")



}
