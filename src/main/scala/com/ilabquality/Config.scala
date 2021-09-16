package com.ilabquality

import java.io.FileInputStream
import java.util.Properties

object Config {
  val property = new Properties()
 private val input = new FileInputStream("./src/main/resources/config.properties");
   property.load(input)

}
