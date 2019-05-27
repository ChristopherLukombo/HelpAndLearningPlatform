package fr.esgi.web.rest;

import fr.esgi.exception.HelpAndLearningPlatformException;
import fr.esgi.service.CommentService;
import fr.esgi.service.dto.CommentDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * REST controller for managing Comment.
 * @author christopher
 */
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
	 * @param commentDTO the comment to create
	 * @return the ResponseEntity with status 201 (Created) and with body the new comment.
	 * @throws URISyntaxException if the Location URI syntax is incorrect
	 */
	@ApiResponses(value = {
	        @ApiResponse(code = 201, message = "Successful register"),
	        @ApiResponse(code = 400, message = "Bad request"),
	        @ApiResponse(code = 401, message = "Unauthorized"),
	        @ApiResponse(code = 403, message = "Access denied"),
	        })
	@PostMapping("/comments")
	public ResponseEntity<CommentDTO> createComment(@RequestBody @Valid CommentDTO commentDTO) throws URISyntaxException, HelpAndLearningPlatformException {
		LOGGER.debug("REST request to save a comment: {}", commentDTO);
		if (null != commentDTO.getId()) {
			throw new HelpAndLearningPlatformException(HttpStatus.BAD_REQUEST.value(),
					"A new comment cannot already have an ID");
		}
		final CommentDTO comment = commentService.save(commentDTO);
		return ResponseEntity.created(new URI("/api/comments" + comment.getId()))
				.body(comment);
	}
}
