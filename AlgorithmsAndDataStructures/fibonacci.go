package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"log"
)

var fib [1000]int

func fibonacci(x int) int {
	if x <= 1 {
		return x
	} else {
		if fib[x] != 0 {
			return fib[x]
		} else {
			fib[x] = fibonacci(x - 1) + fibonacci(x - 2)
		}
		return fib[x]
	}
}

var sc = bufio.NewScanner(os.Stdin)

func nextInt() int {
	sc.Scan()
	i, e := strconv.Atoi(sc.Text())
	if e != nil {
		panic(e)
	}
	return i
}

func main() {
	n := nextInt()
	log.Println("calc start")
	fmt.Printf("%d\n", fibonacci(n))
	log.Println("calc end")
}
