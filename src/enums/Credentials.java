package enums;

public enum Credentials {

	TEXT_HEIGHT(50),
	CARTESIAN(10),
	DIAGONAL(14),

	;

	private int credential;

	private Credentials(int credential) {
		this.credential = credential;
	}

	public int credential() {
		return this.credential;
	}

}
