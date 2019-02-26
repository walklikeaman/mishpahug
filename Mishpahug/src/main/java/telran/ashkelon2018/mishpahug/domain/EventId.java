package telran.ashkelon2018.mishpahug.domain;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class EventId {
	String owner;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	LocalDateTime date;
}
