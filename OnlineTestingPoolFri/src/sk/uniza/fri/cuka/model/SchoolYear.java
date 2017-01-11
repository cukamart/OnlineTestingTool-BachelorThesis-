package sk.uniza.fri.cuka.model;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class SchoolYear {

	@SuppressWarnings("deprecation")
	public int getCurrentSchoolYear(Date date) {

		int month = date.getMonth() + 1;
		int year = date.getYear() + 1900;

		if (month >= 1 && month <= 8) {
			year--;
		}

		return year;
	}

	public String getStringRepresentationOfSchoolYear(Date date) {
		int year = getCurrentSchoolYear(date);
		return getCurrentSchoolYear(date) + "/" + (++year);
	}

}
