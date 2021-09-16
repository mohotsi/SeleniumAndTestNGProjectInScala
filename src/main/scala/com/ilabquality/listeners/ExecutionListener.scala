package com.ilabquality.listeners

import com.ilabquality.Config.property
import org.testng.IExecutionListener
import scalaj.http.Http

import scala.annotation.tailrec
import scala.util.Try

class ExecutionListener extends IExecutionListener {


  override def onExecutionStart(): Unit = {
    waitForServerToGoUp()
    super.onExecutionStart()
  }

  def waitForServerToGoUp() = {
    val startTime = System.currentTimeMillis()

    /**
     * tail annotation will prevent stack over flow
     *
     * @param connected
     */
    @tailrec
    def isServerUP(connected: Boolean = false): Unit = {
      val time: Int = property.getProperty("server.reboot.wait.InMinutes").toInt
      println(time)
      println(System.currentTimeMillis() - startTime)
      println(connected.toString)
      println("pedriod :" + ((System.currentTimeMillis() - startTime) / (60000)).toInt)
      val httpCode = Try {
        Http(property.getProperty("url")).asString.code == 200
      }.getOrElse(connected)

      val exceed = ((System.currentTimeMillis() - startTime) / (60000)).toInt >= property.getProperty("server.reboot.wait.InMinutes").toInt
      if (httpCode) {

        return
      } else if (exceed) {
        System.exit(0)
      }
      else
        isServerUP(httpCode)
    }

    isServerUP()
  }


}

