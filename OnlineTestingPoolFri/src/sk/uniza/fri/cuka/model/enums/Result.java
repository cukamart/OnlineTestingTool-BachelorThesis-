package sk.uniza.fri.cuka.model.enums;

public enum Result {
	INCORRECT(0), CORRECT(1);

	private final int value;

	private Result(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

}
