package dev.jszafran
package sinks

import java.nio.file.Paths

class TestSink(val dirPath: String) {
  def loadHeadlines(): Unit = {
    val files = List("file1.txt", "file2.txt", "file3.txt")
    files.foreach(f => {
      val file = Paths.get(dirPath, f).toFile
      println(file.getAbsolutePath)
    })
  }
}
