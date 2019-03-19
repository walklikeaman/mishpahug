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
		confession.add("Religious");
		confession.add("Irreligious");
		this.confession = confession;
		List<String> gender = new ArrayList<>();
		gender.add("Male");
		gender.add("Female");
		this.gender = gender;
		List<String> maritalStatus = new ArrayList<>();
		maritalStatus.add("Single");
		maritalStatus.add("Married");
		maritalStatus.add("Divorced");
		maritalStatus.add("In a civil union");
		maritalStatus.add("In a ralationship");
		maritalStatus.add("Widowed");
		this.maritalStatus = maritalStatus;
		List<String> foodPreferences = new ArrayList<>();
		foodPreferences.add("Kosher");
		foodPreferences.add("Vegetarian");		
		foodPreferences.add("Any");
		this.foodPreferences = foodPreferences;
		List<String> languages = new ArrayList<>();
		languages.add("Hebrew");
		languages.add("English");
		languages.add("French");
		languages.add("Russian");
		languages.add("Spain");
		this.languages = languages;
		List<String> holiday = new ArrayList<>();
		holiday.add("Pesach");
		holiday.add("Shabat");
		holiday.add("Rosh Hashana");
		holiday.add("Purim");
		holiday.add("Sukkot");
		this.holiday = holiday;
	}


}
