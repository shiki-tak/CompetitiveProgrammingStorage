package main

import (
	"log"
	"time"
	"bytes"
	"fmt"
	"github.com/aws/aws-sdk-go/aws"
	"github.com/aws/aws-sdk-go/aws/session"
	"github.com/aws/aws-sdk-go/service/s3"
)

const REGION = "ap-northeast-1"
const BUCKET_NAME = "lisa-line-bot-develop"

func main() {

	sess, err := session.NewSession(aws.NewConfig().WithRegion(REGION))
	if err != nil {
		fmt.Println(err.Error())
		return
	}

	svc := s3.New(sess)

	// put
	data := []byte("BBBBBB")
	key := "AAA.txt"

	params := &s3.PutObjectInput{
		Bucket:        aws.String(BUCKET_NAME),
		Key:           aws.String(key),
		Body:          bytes.NewReader(data),
		ContentLength: aws.Int64(int64(len(data))),
		ContentType:   aws.String("text/plain"),
	}
	if _, err = svc.PutObject(params); err != nil {
		fmt.Println(err.Error())
		return
	}

	// bucket list
	keys := []string{}
	err = svc.ListObjectsPages(&s3.ListObjectsInput{
		Bucket: aws.String(BUCKET_NAME),
	}, func(p *s3.ListObjectsOutput, last bool) (shouldContinue bool) {
		for _, obj := range p.Contents {
			keys = append(keys, *obj.Key)
			fmt.Println(*obj.Key)
		}
		return true
	})

	if err != nil {
		fmt.Println(err.Error())
		return
	}

	// get
	resp, _:= svc.GetObjectRequest(&s3.GetObjectInput{
		Bucket: aws.String(BUCKET_NAME),
		Key:    aws.String("uploads/yatsugatake_yuhi.JPG"),
	})

	urlStr, err3 := resp.Presign(15 * time.Minute)
 
	if err3 != nil {
		log.Println("Failed to sign request", err)
	}
	 
	log.Println("The URL is", urlStr)
	buf := new(bytes.Buffer)
	buf.ReadFrom(resp.Body)
	fmt.Println(keys[0] + " -> " + buf.String())
}
