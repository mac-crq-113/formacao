package pt.mac.demo.rest;

import org.springframework.web.client.RestClientResponseException;

@lombok.Getter
@lombok.Setter
public class ErrorResponse {
	private int errorCode;
	private String message;
	private Throwable exception;

	public static ErrorResponse fromException(Throwable exp) {
		ErrorResponse er = new ErrorResponse();
		er.errorCode = -1;
		if (exp instanceof RestClientResponseException) {
			RestClientResponseException restExp = (RestClientResponseException) exp;
			er.setMessage(restExp.getResponseBodyAsString());
		} else {

			er.setMessage(exp.getMessage());
		}

		return er;
	}
}