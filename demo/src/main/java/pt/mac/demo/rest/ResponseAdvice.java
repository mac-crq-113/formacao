package pt.mac.demo.rest;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author pms
 * @since 08-03-2018 <br>
 *        <br>
 *
 */
@Slf4j
@RestControllerAdvice
public class ResponseAdvice implements ResponseBodyAdvice<Object> {

	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		return true;
	}

	@ExceptionHandler(value = { Throwable.class })
	@ResponseBody
	public BackendResponse<ErrorResponse> handleException(Throwable ex, WebRequest request) {
		log.error(ex.getMessage(), ex);
		return new BackendResponse<ErrorResponse>(ErrorResponse.fromException(ex), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
			Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
			ServerHttpResponse response) {

		if (selectedContentType.equals(MediaType.APPLICATION_JSON)) {

			if (body instanceof BackendResponse) {
				response.setStatusCode(((BackendResponse<?>) body).getStatus());
				return body;
			} else if (body instanceof String) {
				return body;
			}
			
			//Para não mostrar o dialog de autenticação do browser
			if(((ServletServerHttpResponse) response).getServletResponse().getStatus()==401) {
				response.setStatusCode(HttpStatus.FORBIDDEN);
			}

			return new BackendResponse<Object>(body,
					HttpStatus.resolve(((ServletServerHttpResponse) response).getServletResponse().getStatus()));

		} else {
			return body;
		}
	}

}