package com.ilabquality

case class TestCase(step:String, next: TestCase=null){


  def addTestStep(testStep:String): Unit ={
    TestCase(testStep, this)
  }
  def display(here:TestCase=this):String={
      if(here==null)
        ""
       else
        here.step+"\n" +display(here.next)

  }
}
