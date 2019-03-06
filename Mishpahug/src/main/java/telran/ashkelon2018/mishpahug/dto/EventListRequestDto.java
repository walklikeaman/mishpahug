package telran.ashkelon2018.mishpahug.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor

public class EventListRequestDto {
	Location location;
	Filter filters;

}
