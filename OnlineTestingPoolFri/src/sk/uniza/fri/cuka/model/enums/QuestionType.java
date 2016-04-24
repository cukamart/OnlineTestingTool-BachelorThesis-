package sk.uniza.fri.cuka.model.enums;

public enum QuestionType {
	ABCD("ABCD"), TEXT("text");

	private final String value;

	private QuestionType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
