import java.util.ArrayList;
import java.util.List;

public class HeapSort {
	static List<Integer> heap = new ArrayList<>();

	public static void main(String[] args) {
		for (int i = 1; i <= 16; i++) {
			heap.add(i);
		}

		int n = heap.size();
		buildHeap(n);
		printHeap();

		heap.add(17);
		sort();

		heap.add(0);
		sort();

		printHeap();
	}

	public static void sort() {
		int n = heap.size();
		buildHeap(n);
//		for (int i = n - 1; i >= 1; i--) {
//			swap(0, i);
//			heapify(0, i);
//		}
	}

	public static void buildHeap(int n) {
		for (int i = n / 2 - 1; i >= 0; i--) {
			heapify(i, n);
		}
	}

	public static void heapify(int idx, int max) {
		int largest = idx;			// 親のheap[idx]がどちらの子よりも小さくないと家庭する
		int left = 2 * idx + 1;
		int right = 2 * idx + 2;

		if (left < max && heap.get(left) > heap.get(idx)) {
			largest = left;		// 左の子が親より大きい
		}
		if (right < max && heap.get(right) > heap.get(largest)) {
			largest = right;	// 右の子が親と左の兄弟より大きい
		}
		if (largest != idx) {
			swap(idx, largest);
			heapify(largest, max);
		}
	}

	public static void printHeap() {
		System.out.println("After Heap Sort");
		for (int i = 0; i < heap.size(); i++) {
			System.out.println("heap[" + i + "]: " + heap.get(i));
		}
	}

	private static void swap(int idx, int largest) {
		int tmp = heap.get(idx);
		heap.set(idx, heap.get(largest));
		heap.set(largest, tmp);
	}
}
