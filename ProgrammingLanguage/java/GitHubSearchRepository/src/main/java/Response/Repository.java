package Response;

public class Repository {

	private int Id;
	private String Name;
	private String FullName;
	private User Owner;

	Repository(int Id, String Name, String FullName, User Owner) {
		this.Id = Id;
		this.setName(Name);
		this.setFullName(FullName);
		this.setOwner(Owner);
	}

	public int getId() {
		return this.Id;
	}

	public void setId(int Id) {
		this.Id = Id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getFullName() {
		return FullName;
	}

	public void setFullName(String fullName) {
		FullName = fullName;
	}

	public User getOwner() {
		return Owner;
	}

	public void setOwner(User owner) {
		Owner = owner;
	}
}
