package telran.ashkelon2018.mishpahug.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
@Getter
@AllArgsConstructor
public class Filter {
	LocalDate dateFrom; //[yyyy-MM-dd]
	LocalDate dateTo; //[yyyy-MM-dd]
	String holidays; 
	String confession;
	String food;
}
