package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
)

// 接点の構造体
type Node struct {
	p int
	l int
	r int
}

// 接点を入れるための配列
var T []Node

func output(u int) {

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
	sc.Split(bufio.ScanWords)
	n := nextInt()
	s := make([]int, 0)
	T = make([]Node, 10)

	for i := 0; i < n; i++ {
		T[i].p = -1
		T[i].l = -1
		T[i].r = -1
	}
	for i := 0; i < n; i++ {
		s = append(s, nextInt())
	}
	for i := 0; i < n; i++ {
		fmt.Println(s[i])
	}
}
