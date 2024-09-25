package com.orka.callbridge.helper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

//For Alerts
public class Message {

	private String content;

	@Builder.Default
	private MessageType type = MessageType.green;

}
