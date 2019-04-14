package fr.esgi.web.rest;

import fr.esgi.service.CommentService;
import fr.esgi.service.dto.CommentDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api")
public class CommentResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(SubscriptionResource.class);

    private final CommentService commentService;

    @Autowired
    public CommentResource(CommentService commentService) {
        this.commentService = commentService;
    }

    public ResponseEntity<Object> createComment(@RequestBody CommentDTO commentDTO) throws URISyntaxException {
        final CommentDTO comment = commentService.save(commentDTO);

        return ResponseEntity.created(new URI("/api/comments" + comment.getId()))
                .build();
    }
}
