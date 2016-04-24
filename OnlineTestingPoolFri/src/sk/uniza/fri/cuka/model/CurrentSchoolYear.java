package sk.uniza.fri.cuka.model;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class CurrentSchoolYear {

	@SuppressWarnings("deprecation")
	public int getCurrentSchoolYear() {
		Date now = new Date();

		int month = now.getMonth() + 1;
		int year = now.getYear() + 1900;

		if (month >= 1 && month <= 8) {
			year--;
		}

		return year;
	}

	public String getStringRepresentationOfSchoolYear() {
		int year = getCurrentSchoolYear();
		return getCurrentSchoolYear() + "/" + (++year);
	}
}
