package etherjava.domain.model.account;

public class Address {
	private String addressAsString;

	public Address(String addressAsString) {
		this.addressAsString = addressAsString;
	}

	public String addressToString() {
		return this.addressAsString;
	}
}
