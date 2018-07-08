package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
)

func bubleSort(a []int, n int) {
	flag := 1
	count := 0
	for {
		flag = 0
		for j := n - 1; j > 0; j-- {
			if a[j] < a[j-1] {
				var v int
				v = a[j]
				a[j] = a[j-1]
				a[j-1] = v
				flag = 1
				count++
			}
		}
		if flag == 0 {
			break
		}
	}
	for i := 0; i < n; i++ {
		if i == n-1 {
			fmt.Printf("%d", a[i])
			fmt.Print("\n")
		} else {
			fmt.Printf("%d ", a[i])
		}
	}
	fmt.Println(count)
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
	a := make([]int, n)
	for i := 0; i < n; i++ {
		a[i] = nextInt()
	}
	bubleSort(a, n)
}
