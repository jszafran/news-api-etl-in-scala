package dev.jszafran
package newsapi

import traits.NewsApiClient

import models.{NewsSource, SourceTopHeadline}

class NewsApiInMemoryClient(
    val sources: List[NewsSource],
    sourceTopHeadlines: List[SourceTopHeadline]
) extends NewsApiClient {
  // simple in-memory client that will be used in testing

  override def getSources(language: Option[String]): List[NewsSource] =
    this.sources

  override def getTopHeadlines(
      sources: List[NewsSource]
  ): List[SourceTopHeadline] = this.sourceTopHeadlines
}
