package dev.jszafran
package sinks

import models.SourceAggregatedTopHeadlines
import traits.Sink

import com.github.tototoshi.csv._

import java.io.File
import java.nio.file.Paths

class LocalDiskCSVSink(val dirPath: String) extends Sink {
  override def loadHeadlines(
      sourceTopHeadlines: List[SourceAggregatedTopHeadlines],
      timestamp: String
  ): Unit = {
    def createDirIfNotExists(dir: File): Unit =
      if (!dir.exists()) { dir.mkdir() }

    createDirIfNotExists(Paths.get(dirPath).toFile)

    sourceTopHeadlines.foreach(s => {
      createDirIfNotExists(Paths.get(dirPath, s.sourceId).toFile)
      val csvFile =
        Paths.get(dirPath, s.sourceId, s"${timestamp}_headlines.csv").toFile
      val writer = CSVWriter.open(csvFile)
      writer.writeAll(s.topHeadlines.map(x => List(x.title)))
    })
  }
}
