package telran.ashkelon2018.mishpahug.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class InviteToEventResponseDto {
	String userId;
	boolean isInvited;

	public InviteToEventResponseDto(String userId) {
		this.userId = userId;
		this.isInvited = true;
	}

}
