package main

import (
	"fmt"
)

func Min(x, y int) int {
	if x > y {
		return y
	}
	return x
}

func main() {

	// ボトルの本数
	var n, pourNumber int
	fmt.Scan(&n, &pourNumber)

	// ボトルの容量
	capacity := make([]int, n)
	for i := range capacity {
		fmt.Scan(&capacity[i])
	}

	// 実際に入っている量
	bottles := make([]int, n)
	for i := range bottles {
		fmt.Scan(&bottles[i])
	}

	// 移動元のId
	fromId := make([]int, pourNumber)

	// 移動先のId
	toId := make([]int, pourNumber)

	// 移動元のIdを指定
	for i := range fromId {
		fmt.Scan(&fromId[i])
	}

	// 移動先のId
	for i := range toId {
		fmt.Scan(&toId[i])
	}

	// ジュースを移動したあとの実際に入っている量を計算する
	for i := 0; i < pourNumber; i++ {
		// 移動元と移動先のジュースの量を計算する
		// toIdBottlesとfromIdBottlesの合計を計算
		sum := bottles[toId[i]] + bottles[fromId[i]]

		// 少ない方を取得
		bottles[toId[i]] = Min(sum, capacity[toId[i]])

		bottles[fromId[i]] = sum - bottles[toId[i]]
	}

	// 出力
	for i := 0; i < n; i++ {
		if i == n-1 {
			fmt.Printf("%d\n", bottles[i])
		} else {
			fmt.Printf("%d ", bottles[i])
		}
	}
}
