package dev.jszafran
package newsapi

import models.{NewsSource, SourceTopHeadline}
import traits.NewsApiClient

import jsonparsers.JsonModelParser

class NewsApiHttpClient(
    val apiKey: String,
    val apiUrl: String = "https://newsapi.org/v2/"
) extends NewsApiClient {

  private val session =
    requests.Session(headers = Map("X-Api-Key" -> this.apiKey))

  private def makeGetRequest(endpoint: String): requests.Response =
    session.get(s"$apiUrl/$endpoint")

  override def getSources(language: Option[String]): List[NewsSource] = {
    val queryString = language match {
      case Some(value) => s"?language=$value"
      case None        => ""
    }
    val resp = makeGetRequest(s"sources$queryString")
    JsonModelParser.parseNewsSources(resp)
  }

  override def getTopHeadlines(
      sources: List[NewsSource]
  ): List[SourceTopHeadline] = {
    val queryString = sources match {
      case Nil => ""
      case _   => s"?sources=${sources.map(_.id).mkString(",")}&page_size=100"
    }
    val resp = makeGetRequest(s"top-headlines/$queryString")
    JsonModelParser.parseTopHeadlines(resp)
  }
}
