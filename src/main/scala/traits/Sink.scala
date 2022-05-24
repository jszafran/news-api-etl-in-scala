package dev.jszafran
package traits

import models.SourceAggregatedTopHeadlines

trait Sink {
  def loadHeadlines(
      sourceTopHeadlines: List[SourceAggregatedTopHeadlines],
      timestamp: String
  ): Unit
}
