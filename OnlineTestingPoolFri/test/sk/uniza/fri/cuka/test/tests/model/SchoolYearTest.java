package sk.uniza.fri.cuka.test.tests.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import sk.uniza.fri.cuka.model.SchoolYear;

// parametrizovany test, skusi previest na 30.8.2016 na sk. rok
// a 1.9.2016 na sk.rok - august / september su medzne podmienky, sa meni sk. rok
@RunWith(Parameterized.class)
public class SchoolYearTest {
	
	private Date date;
	private String expectedYear;

	public SchoolYearTest(Date date, String expectedYear) {
		this.date = date;
		this.expectedYear = expectedYear;
	}
	
	@SuppressWarnings("deprecation")
	@Parameters
	public static Collection<Object[]> data() {
		Date date = new Date();
		date.setYear(116);
		date.setMonth(8);
		date.setDate(1);
		
		Date date2 = new Date();
		date2.setYear(116);
		date2.setMonth(7);
		date2.setDate(30);
		
		return Arrays.asList(new Object[][] { {date, "2016/2017"}, {date2, "2015/2016"} });
	}

	// na základe dátumu vráti šk. rok (medzná podmienka august/september) 
	@Test
	public void schoolYearTest() {
		SchoolYear schoolYear = new SchoolYear();

		String year = schoolYear.getStringRepresentationOfSchoolYear(date);	
		Assert.assertEquals(expectedYear, year);
	}
}
