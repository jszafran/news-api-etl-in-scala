package dev.jszafran
package newsapi

import models.{NewsSource, SourceTopHeadline}
import traits.NewsApiClient

class NewsApiHttpClient(
    val apiKey: String,
    val apiUrl: String = "https://newsapi.org/v2/"
) extends NewsApiClient {
  private val session =
    requests.Session(headers = Map("X-Api-Key" -> this.apiKey))

  override def getTopHeadlines(
      sources: List[NewsSource]
  ): List[SourceTopHeadline] = ???

  override def getSources(language: Option[String]): List[NewsSource] = {
    val queryString = language match {
      case Some(value) => s"?language=$value"
      case None        => ""
    }
    val resp = makeGetRequest(s"sources$queryString")
    parseSources(resp)
  }

  private def makeGetRequest(endpoint: String): requests.Response =
    session.get(s"$apiUrl/$endpoint")

  private def parseSources(resp: requests.Response): List[NewsSource] = {
    ujson
      .read(resp.bytes)("sources")
      .arr
      .map(obj => NewsSource(id = obj("id").str, name = obj("name").str))
      .toList
  }
}
