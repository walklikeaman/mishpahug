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
	
	public EventListResponseDto(List<EventDto> content, int page, int size) {
		this.content = content;
		this.totalElements = content.size();
		this.totalPages = content.size() % size > 0 ? content.size() / size + 1 : content.size() / size;
		this.size = size;	
		this.numberOfElements = (number >= (content.size() % size > 0 ? content.size() / size + 1 : content.size() / size) ? true : false)  ? content.size() % size : size; 
		this.first = number ==  0 ? true : false;
		this.last = number >= (content.size() % size > 0 ? content.size() / size + 1 : content.size() / size) ? true : false;
		this.sort = new Sort(Sort.Direction.DESC, "dateFrom");
	}
}
