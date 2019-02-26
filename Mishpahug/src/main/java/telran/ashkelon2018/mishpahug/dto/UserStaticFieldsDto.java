package telran.ashkelon2018.mishpahug.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
@Getter
public class UserStaticFieldsDto {

	List<String> confession;

	List<String> gender;

	List<String> maritalStatus;

	List<String> foodPreferences;

	List<String> languages;

	List<String> holiday;
	
	public UserStaticFieldsDto() {
		List<String> confession = new ArrayList<>();
		confession.add("religious");
		confession.add("irreligious");
		this.confession = confession;
		List<String> gender = new ArrayList<>();
		gender.add("male");
		gender.add("female");
		this.gender = gender;
		List<String> maritalStatus = new ArrayList<>();
		maritalStatus.add("married");
		maritalStatus.add("single");
		maritalStatus.add("couple");
		this.maritalStatus = maritalStatus;
		List<String> foodPreferences = new ArrayList<>();
		foodPreferences.add("vegetarian");
		foodPreferences.add("kosher");
		foodPreferences.add("non-vegetarian");
		this.foodPreferences = foodPreferences;
		List<String> languages = new ArrayList<>();
		languages.add("Hebrew");
		languages.add("English");
		languages.add("Russian");
		this.languages = languages;
		List<String> holiday = new ArrayList<>();
		holiday.add("Pesah");
		holiday.add("Shabbat");
		holiday.add("Other");
		this.holiday = holiday;
	}


}
