package fr.esgi.web.rest;

import fr.esgi.config.ErrorMessage;
import fr.esgi.exception.HelpAndLearningPlatformException;
import fr.esgi.service.CommentService;
import fr.esgi.service.dto.CommentDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Comment.
 * @author christopher
 */
@Api(value = "Comment")
@RestController
@RequestMapping("/api")
public class CommentResource {

	

	private static final Logger LOGGER = LoggerFactory.getLogger(CommentResource.class);

	private final CommentService commentService;

	@Autowired
	public CommentResource(CommentService commentService) {
		this.commentService = commentService;
	}

	/**
	 * POST  /comments : save a comment.
	 * 
	 * @param commentDTO the comment to create
	 * @return the ResponseEntity with status 201 (Created) and with body the new comment.
	 * @throws URISyntaxException if the Location URI syntax is incorrect
	 */
	@ApiOperation(value = "Save a comment.")
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
					ErrorMessage.A_NEW_COMMENT_CANNOT_HAVE_ALREADY_HAVE_UN_ID);
		}
		final CommentDTO comment = commentService.save(commentDTO);
		return ResponseEntity.created(new URI("/api/comments" + comment.getId()))
				.body(comment);
	}

	/**
	 * PUT  /comments : update a comment.
	 * 
	 * @param commentDTO the comment to update
	 * @return the ResponseEntity with status 200 (Ok) and with body the updated comment.
	 */
	@ApiOperation(value = "Update a comment.")
	@PutMapping("/comments")
	public ResponseEntity<CommentDTO> updateComment(@RequestBody @Valid CommentDTO commentDTO) throws HelpAndLearningPlatformException {
		LOGGER.debug("REST request to update a comment: {}", commentDTO);
		if (null == commentDTO.getId()) {
			throw new HelpAndLearningPlatformException(HttpStatus.BAD_REQUEST.value(),
					ErrorMessage.A_COMMENT_CANNOT_HAVE_AN_ID);
		}
		final CommentDTO comment = commentService.update(commentDTO);
		return ResponseEntity.ok()
				.body(comment);
	}

	/**
	 * GET  /comments : get all comments.
	 * 
	 * @return the ResponseEntity with status 200 (Ok) and with body the comments.
	 */
	@ApiOperation(value = "Get all comments.")
	@GetMapping("/comments")
	public ResponseEntity<List<CommentDTO>> getAllComments() throws HelpAndLearningPlatformException {
		LOGGER.debug("REST request to get all comments");
		List<CommentDTO> commentsDTO = commentService.findAll();
		if (commentsDTO.isEmpty()) {
			throw new HelpAndLearningPlatformException(HttpStatus.NOT_FOUND.value(),
					ErrorMessage.NO_COMMENT);
		}
		return ResponseEntity.ok().body(commentsDTO);
	}

	/**
	 * GET  /comments : get comment by id.
	 * 
	 * @return the ResponseEntity with status 200 (Ok) and with body the comment.
	 */
	@ApiOperation(value = "Get comment by id.")
	@GetMapping("/comments/{id}")
	public ResponseEntity<CommentDTO> getComment(@PathVariable Long id) throws HelpAndLearningPlatformException {
		LOGGER.debug("REST request to get all comments");
		Optional<CommentDTO> commentDTO = commentService.findOne(id);
		if (commentDTO.isPresent()) {
			return ResponseEntity.ok()
					.body(commentDTO.get());
		} else {
			throw new HelpAndLearningPlatformException(HttpStatus.NOT_FOUND.value(),
					ErrorMessage.NO_COMMENT);
		}
	}

	/**
	 * DELETE  /comments : delete the comment by id.
	 * 
	 * @return the ResponseEntity with status 204 (No Content) and no comment.
	 */
	@ApiOperation(value = "Delete the comment by id.")
	@DeleteMapping("/comments/{id}")
	public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
		LOGGER.debug("REST request to delete comment : {}", id);
		commentService.delete(id);
		return ResponseEntity.noContent().build();
	}

	/**
	 * GET /comments/trick/{trickId} : get all comments by trickId.
	 * 
	 * @param trickId
	 * @return the ResponseEntity with status 200 (Ok) and with body the comments.
	 * @throws HelpAndLearningPlatformException if no comments are found. 
	 */
	@ApiOperation(value = "Get all comments by trickId.")
	@GetMapping("/comments/trick/{trickId}")
	public ResponseEntity<List<CommentDTO>> getAllCommentsByTrickId(@PathVariable Long trickId) throws HelpAndLearningPlatformException {
		LOGGER.debug("REST request to get all comments by trickId : {}", trickId);
		List<CommentDTO> commentsDTO = commentService.findAllByTrickId(trickId);
		if (commentsDTO.isEmpty()) {
			throw new HelpAndLearningPlatformException(HttpStatus.NOT_FOUND.value(),
					ErrorMessage.NO_COMMENT);
		}
		return ResponseEntity.ok().body(commentsDTO);
	}
}
