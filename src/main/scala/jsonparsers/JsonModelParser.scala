package dev.jszafran
package jsonparsers

import models.{NewsSource, SourceTopHeadline}

object JsonModelParser {
  def parseNewsSources(data: geny.Readable): List[NewsSource] = {
    ujson
      .read(data)("sources")
      .arr
      .map(obj => NewsSource(id = obj("id").str, name = obj("name").str))
      .toList
  }

  def parseTopHeadlines(data: geny.Readable): List[SourceTopHeadline] = {
    ujson
      .read(data)("articles")
      .arr
      .map(obj =>
        SourceTopHeadline(
          title = obj("title").str,
          sourceId = obj("source")("id").str
        )
      )
      .toList
  }
}
