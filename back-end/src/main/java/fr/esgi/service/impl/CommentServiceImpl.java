package fr.esgi.service.impl;

import fr.esgi.dao.CommentRepository;
import fr.esgi.domain.Comment;
import fr.esgi.service.CommentService;
import fr.esgi.service.dto.CommentDTO;
import fr.esgi.service.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("CommentService")
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    private final CommentMapper commentMapper;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
    }

    @Transactional
    @Override
    public CommentDTO save(CommentDTO commentDTO) {
        Comment comment = commentMapper.CommentDTOToComment(commentDTO);
        comment = commentRepository.save(comment);
        return commentMapper.commentToCommentDTO(comment);
    }
}
