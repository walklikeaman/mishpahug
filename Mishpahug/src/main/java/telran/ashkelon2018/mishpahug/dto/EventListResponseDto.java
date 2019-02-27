package telran.ashkelon2018.mishpahug.dto;

import java.util.List;

import org.springframework.data.domain.Sort;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor

public class EventListResponseDto {
	List<EventDto> content;
	int totalElements;
	int totalPages;
	int size;
	int number;
	int numberOfElements;
	boolean first;
	boolean last;
	Sort sort;
	
	public EventListResponseDto(List<EventDto> content) {
		this.content = content;
		this.totalElements = content.size();
		this.totalPages = content.size() % 12 > 0 ? content.size() / 12 + 1 : content.size() / 12;
		this.size = 12;
		this.first = true;
		this.sort = new Sort(Sort.Direction.DESC, "dateFrom");
	}
}
