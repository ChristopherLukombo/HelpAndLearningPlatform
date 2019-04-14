package fr.esgi.service;

import fr.esgi.service.dto.CommentDTO;
import org.springframework.stereotype.Service;

@Service
public interface CommentService {

    CommentDTO save(CommentDTO commentDTO);
}
