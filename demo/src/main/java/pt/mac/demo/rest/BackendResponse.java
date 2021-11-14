package pt.mac.demo.rest;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonView;

import pt.mac.demo.DemoViews;

/**
 *
 * @author pms
 * @since 08-03-2018 <br>
 *        <br>
 *
 */
@lombok.Getter
@lombok.Setter
@lombok.AllArgsConstructor
@JsonView(DemoViews.Any.class)
public class BackendResponse<T> {
	protected T body;
	protected HttpStatus status;

}
