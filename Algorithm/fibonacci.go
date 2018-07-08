package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
)

func fibonacci(x int) int {
	if x > 1 {
		return fibonacci(x-1) + fibonacci(x-2)
	} else {
		return x
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

	fmt.Printf("%d", fibonacci(n))
}
