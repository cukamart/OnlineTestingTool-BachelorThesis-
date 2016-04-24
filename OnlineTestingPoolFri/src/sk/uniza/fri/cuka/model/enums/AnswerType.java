package sk.uniza.fri.cuka.model.enums;

public enum AnswerType {
	NOTANSWERED(0), ANSWERED(1);

	private final int value;

	private AnswerType(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

}
