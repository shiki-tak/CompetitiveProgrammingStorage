package main

import (
	"fmt"
	"net/http"
)

func handler(rw http.ResponseWriter, r *http.Request) {
	fmt.Fprint(rw, "Hello, World!")
}

func main() {
	http.HandleFunc("/", handler)
	http.ListenAndServe(":8080", nil)
}
