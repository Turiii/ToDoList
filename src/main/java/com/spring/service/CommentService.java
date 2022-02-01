package com.spring.service;

import com.spring.dao.Comment;
import com.spring.dao.TaskEntity;
import com.spring.dao.UserEntity;
import com.spring.dto.CommentRequest;
import com.spring.dto.CommentResponse;
import com.spring.mapper.CommentMapper;
import com.spring.repository.CommentRepository;
import com.spring.repository.TaskRepository;
import com.spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    CommentRepository commentRepository;

    @Autowired
    CommentMapper commentMapper;

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserRepository userRepository;


    public void addCommentToList(CommentRequest comment) {
        TaskEntity taskEntity = taskRepository.findById(comment.getTask_id())
                .orElseThrow(NoSuchElementException::new);
        UserEntity userEntity = userRepository.findById(comment.getOwner_id())
                .orElseThrow(() -> new UsernameNotFoundException("Username with given ID was not found"));

        Comment mappedComment = commentMapper.fromDTO(comment);
        mappedComment.setCommentOwner(userEntity.getId());
        mappedComment.setTaskEntity(taskEntity.getId());
        commentRepository.save(mappedComment);
    }

    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }

    public CommentResponse getComment(Long id) {
        return commentRepository.findById(id).map(commentMapper::fromDAO).orElseThrow(NoSuchElementException::new);
    }

    @Transactional
    public void editComment(Long id, String request) {
        Comment commentToReplace = commentRepository
                .findById(id)
                .orElseThrow(IllegalArgumentException::new);

        commentToReplace.setCommentContent(request);
        commentRepository.save(commentToReplace);
    }

    public List<CommentResponse> getAllTaskComments(Long taskId) {
        List<Comment> comments = commentRepository.findAllByTaskEntity(taskId);
        return comments.stream()
                .map(commentMapper::fromDAO)
                .collect(Collectors.toList());
    }
}
