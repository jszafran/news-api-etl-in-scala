package dev.jszafran
package models

case class SourceAggregatedTopHeadlines(
    source_id: String,
    top_headlines: List[TopHeadline]
)
