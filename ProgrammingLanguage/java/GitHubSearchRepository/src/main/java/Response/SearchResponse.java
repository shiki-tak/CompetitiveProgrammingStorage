package Response;

public class SearchResponse<Item> {

	private int TotalCount;
	private Item[] items;

	SearchResponse(int TotalCount, Item[] items) {
		this.setTotalCount(TotalCount);
		this.setItems(items);
	}

	public int getTotalCount() {
		return TotalCount;
	}

	public void setTotalCount(int totalCount) {
		TotalCount = totalCount;
	}

	public Item[] getItems() {
		return items;
	}

	public void setItems(Item[] items) {
		this.items = items;
	}
}
