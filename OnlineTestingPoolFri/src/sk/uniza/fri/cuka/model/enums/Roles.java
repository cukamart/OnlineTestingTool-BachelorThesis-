package sk.uniza.fri.cuka.model.enums;

public enum Roles {
	TEACHER("ucitelia"), STUDENT("studenti");

	private final String value;

	private Roles(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
