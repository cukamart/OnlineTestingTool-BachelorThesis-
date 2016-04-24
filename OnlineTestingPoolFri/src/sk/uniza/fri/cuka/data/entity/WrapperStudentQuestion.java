package sk.uniza.fri.cuka.data.entity;

import java.util.ArrayList;
import java.util.List;

public class WrapperStudentQuestion {

	private List<StudentQuestion> sQuestions = new ArrayList<>();

	public List<StudentQuestion> getsQuestions() {
		return sQuestions;
	}

	public void setsQuestions(List<StudentQuestion> sQuestions) {
		this.sQuestions = sQuestions;
	}

}
