package main

import "fmt"

/*
ジュースを移動元から移動先へ注ぐ
fromIdBottles: 移動元のボトルのジュースの量
toIdBottles: 移動先のボトルのジュースの量
fromIdCapacities: 移動元のボトルの容量
toIdCapacities: 移動先のボトルの容量
*/
func Pour(fromIdBottles, toIdBottles, fromIdCapacities, toIdCapacities int) (int, int) {

	// 注ぐ量を移動先の容量 - 移動先の現在の量 で計算する
	pourAmount := toIdCapacities - toIdBottles

	// もし、pourAmountが移動元のボトルの量よりも少なければ、pourAmountを移動する
	if pourAmount < fromIdBottles {
		toIdBottles += pourAmount
		fromIdBottles -= pourAmount

		// そうでなければ、移動元のジュースを全て移動する
	} else {
		toIdBottles += fromIdBottles
		fromIdBottles = 0
	}

	return fromIdBottles, toIdBottles
}

func main() {
	// n: ボトルの本数
	// pourNumber: 移動回数
	var n, pourNumber int
	fmt.Scan(&n, &pourNumber)

	// ボトルの容量
	capacities := make([]int, n)
	for i := range capacities {
		fmt.Scan(&capacities[i])
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
		newFromIdBottles, newToIdBottles := Pour(bottles[fromId[i]], bottles[toId[i]], capacities[fromId[i]], capacities[toId[i]])

		bottles[fromId[i]] = newFromIdBottles
		bottles[toId[i]] = newToIdBottles
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
