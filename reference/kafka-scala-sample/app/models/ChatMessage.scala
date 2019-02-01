package models

import play.api.libs.json.Json

case class ChatMessage (userId: String, message: String, timestamp: Long)

object ChatMessage {
    implicit val jsonWrites = Json.writes[ChatMessage]
    implicit val jsonReads = Json.reads[ChatMessage]
}