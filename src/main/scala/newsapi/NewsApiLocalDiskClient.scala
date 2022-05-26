package dev.jszafran
package newsapi

import traits.NewsApiClient
import models.{NewsSource, SourceTopHeadline}

import jsonparsers.JsonModelParser

import scala.io.Source

class NewsApiLocalDiskClient(
    val sourcesResourceName: String,
    val topHeadlinesResourceName: String
) extends NewsApiClient {

  override def getSources(language: Option[String]): List[NewsSource] = {
    JsonModelParser.parseNewsSources(
      Source.fromResource(sourcesResourceName).getLines.next
    )
  }

  override def getTopHeadlines(
      sources: List[NewsSource]
  ): List[SourceTopHeadline] = {
    JsonModelParser.parseTopHeadlines(
      Source.fromResource(topHeadlinesResourceName).getLines.next
    )
  }
}
