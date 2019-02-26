package telran.ashkelon2018.mishpahug.dto;

import lombok.Getter;

@Getter

public class InviteToEventResponseDto {
	String userId;
	boolean isInvited;

	public InviteToEventResponseDto(String userId) {
		this.userId = userId;
		this.isInvited = true;
	}

}
