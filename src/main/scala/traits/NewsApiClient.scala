package dev.jszafran
package traits

import models.{NewsSource, SourceTopHeadline}

trait NewsApiClient {
  def getSources(language: Option[String]): List[NewsSource]
  def getTopHeadlines(sources: List[NewsSource]): List[SourceTopHeadline]
}
