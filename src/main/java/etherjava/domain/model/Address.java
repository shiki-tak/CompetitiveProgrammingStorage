package etherjava.domain.model;

public class Address {
	private String addressAsString;

	public Address(String addressAsString) {
		this.addressAsString = addressAsString;
	}

	public String addressToString() {
		return this.addressAsString;
	}
}
