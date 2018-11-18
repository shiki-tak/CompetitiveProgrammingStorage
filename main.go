package main

import (
	"log"
	"net/http"
	"os"
	"fmt"
	"time"
	"math/rand"

	"github.com/gin-gonic/gin"
    "github.com/line/line-bot-sdk-go/linebot"
    "github.com/aws/aws-sdk-go/aws"
    "github.com/aws/aws-sdk-go/aws/credentials"
	"github.com/aws/aws-sdk-go/aws/session"
	"github.com/aws/aws-sdk-go/service/s3"
)

func main() {
	port := os.Getenv("PORT")

	if port == "" {
		log.Fatal("$PORT must be set")
	}

    // aws session
    awsCredential := AWSCredential{
        AccessKey:       os.Getenv("AWS_ACCESS_KEY_ID"),
        SecretAccessKey: os.Getenv("AWS_SECRET_KEY"),
        Region:          "ap-northeast-1",
        Bucket:          "lisa-line-bot-develop",
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
                    if message.Text == "夕日" {
                        url := awsCredential.ShowSignedURL("uploads/yatsugatake_yuhi.JPG")
                        
                    if _, err = bot.ReplyMessage(event.ReplyToken, linebot.NewImageMessage(url, url)).Do(); err != nil {
                            log.Print(err)
                        }
                    }
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

type AWSCredential struct {
    AccessKey       string
    SecretAccessKey string
    Region          string
    Bucket          string
}


func getResMessage(reqMessage string) (message string) {
    resMessages := [6]string{"わかるわかる", "それで？それで？", "からの〜？", "えぇぇ〜!（◎ー◎；）", "かんぺきですね"}

    rand.Seed(time.Now().UnixNano())
    r := rand.Intn(10)
    if (r >= 0 && r <= 4) {
        message = resMessages[r]
    } else {
        message = reqMessage + "じゃねーよw"
    }
    return
}

func (a *AWSCredential) ShowSignedURL(filename string) (url string) {
    session, err := session.NewSession(&aws.Config{
            Credentials: credentials.NewStaticCredentials(a.AccessKey, a.SecretAccessKey, ""),
            Region:      aws.String(a.Region),
    })
    if err != nil {
            fmt.Println(err.Error())
    }

    client := s3.New(session)
    req, _ := client.GetObjectRequest(&s3.GetObjectInput{
            Bucket: aws.String(a.Bucket),
            Key:    aws.String(filename),
    })
    url, err = req.Presign(15 * time.Minute)
    if err != nil {
            fmt.Println(err.Error())
    }
    fmt.Println(url)
    return
}
