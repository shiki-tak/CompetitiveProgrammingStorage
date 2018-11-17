package main

import (
	"log"
	"net/http"
	"os"

	"github.com/gin-gonic/gin"
    "github.com/line/line-bot-sdk-go/linebot"
	"fmt"
	"time"
	"math/rand"
)

func main() {
	port := os.Getenv("PORT")

	if port == "" {
		log.Fatal("$PORT must be set")
	}

	router := gin.New()
	router.Use(gin.Logger())

	router.POST("/hook", func(c *gin.Context) {
        client := &http.Client{Timeout: time.Duration(15 * time.Second)}
        bot, err := linebot.New(os.Getenv("CHANNEL_SECRET"), os.Getenv("CHANNEL_ACCESS_TOKEN"), linebot.WithHTTPClient(client))
        if err != nil {
            fmt.Println(err)
            return
        }
		events, err := bot.ParseRequest(c.Request)
		if err != nil {
            log.Print("error...")
			return
		}
		for _, event := range events {
			if event.Type == linebot.EventTypeMessage {
				switch message := event.Message.(type) {
                case *linebot.TextMessage:
                    resMessage := getResMessage(message.Text)
                    fmt.Println("resMessage: " + resMessage)
                    if resMessage != "" {
                        postMessage := linebot.NewTextMessage(resMessage)
                        if _, err = bot.ReplyMessage(event.ReplyToken, postMessage).Do(); err != nil {
                            log.Print(err)
                        }
                    }
				}
			}
		}
    })
	router.Run(":" + port)
}

func getResMessage(reqMessage string) (message string) {
    resMessages := [6]string{"わかるわかる", "それで？それで？", "からの〜？", "えぇぇ〜!（◎ー◎；）", "それはすごい！", "かんぺきですね"}

    rand.Seed(time.Now().UnixNano())
    r := rand.Intn(10)
    if (r >= 0 && r <= 5) {
        message = resMessages[r]
    } else {
        message = reqMessage + "じゃねーよw"
    }
    return
}