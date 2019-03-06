package telran.ashkelon2018.mishpahug.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)

public class Filter {
	LocalDate dateFrom; //[yyyy-MM-dd]
	LocalDate dateTo; //[yyyy-MM-dd]
	String holidays; 
	String confession;
	String food;
}
