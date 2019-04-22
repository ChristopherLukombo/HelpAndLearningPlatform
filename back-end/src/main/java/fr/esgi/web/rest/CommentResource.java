package fr.esgi.web.rest;

import java.net.URI;
import java.net.URISyntaxException;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.esgi.service.CommentService;
import fr.esgi.service.dto.CommentDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
@Api(value = "Comment")
@RestController
@RequestMapping("/api")
public class CommentResource {

	private static final Logger LOGGER = LoggerFactory.getLogger(SubscriptionResource.class);

	private final CommentService commentService;

	@Autowired
	public CommentResource(CommentService commentService) {
		this.commentService = commentService;
	}

	/**
	 *  POST  /comments : save a comment.
	 * @param commentDTO
	 * @return comment
	 * @throws URISyntaxException
	 */
	@ApiResponses(value = {
	        @ApiResponse(code = 201, message = "Successful register"),
	        @ApiResponse(code = 400, message = "Bad request"),
	        @ApiResponse(code = 401, message = "Unauthorized"),
	        @ApiResponse(code = 403, message = "Acces denied"),
	        })
	@PostMapping("/comments")
	public ResponseEntity<Object> createComment(@RequestBody @Valid CommentDTO commentDTO) throws URISyntaxException {
		LOGGER.debug("REST request to save a comment: {}", commentDTO);
		
		final CommentDTO comment = commentService.save(commentDTO);
		
		return ResponseEntity.created(new URI("/api/comments" + comment.getId()))
				.build();
	}
}
