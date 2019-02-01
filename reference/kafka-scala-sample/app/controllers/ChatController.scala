package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.libs.json._
import models._

@Singleton
class ChatController @Inject() extends Controller {
    
    // 一覧を取得
    def index = Action {
        var chatMessage = ChatMessage.apply("test-userId", "test-tweet", System.currentTimeMillis())
        // TODO: message一覧を取得
        Ok(Json.toJson(chatMessage))
    }

    // 新しいメッセージを送信
    def create = Action { implicit request =>
        // TODO: decode, encodeはservicesへ
        // TODO: hbaseにregister
        val params : Option[JsValue] = request.body.asJson
        val json = params.get

        // encode
        val result: JsResult[ChatMessage] = json.validate[ChatMessage]
        val chatMessage: ChatMessage = result.get
        println(chatMessage.userId + " " + chatMessage.message)

        // decode
        val decodeJson: JsValue = Json.toJson(chatMessage)
        println(decodeJson.toString())

        Ok("")
    }

    // def update = Action {

    // }

    // def delete = Action {

    // }

    // def get = Action {

    // }
}