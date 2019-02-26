package telran.ashkelon2018.mishpahug.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@Getter
@NoArgsConstructor
public class AddEventRequestDto {

	String title;
	String holiday;
	AddressDto address;
	String confession;
	@JsonFormat(pattern="yyyy-MM-dd")
	LocalDate date;
	@JsonFormat(pattern="HH:mm:ss")
	LocalTime time;
	int duration;
	List<String> food;
	String description;
}
