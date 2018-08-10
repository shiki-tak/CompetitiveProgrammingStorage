package Response;

public class User {

	private int  Id;
	private String Login;

	User(int Id, String Login) {
		this.setId(Id);
		this.setLogin(Login);
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getLogin() {
		return Login;
	}

	public void setLogin(String login) {
		Login = login;
	}
}
