package com.spring.mapper;

import com.spring.dao.Comment;
import com.spring.dao.UserEntity;
import com.spring.dto.CommentRequest;
import com.spring.dto.CommentResponse;
import com.spring.repository.CommentRepository;
import com.spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

    @Autowired
    CommentRepository repository;
    @Autowired
    UserRepository userRepository;

    public Comment fromDTO(CommentRequest request){
        Comment comment = new Comment();
        comment.setCommentContent(request.getContent());
        return comment;
    }

    public CommentResponse fromDAO(Comment comment){

        UserEntity userEntity= userRepository.findById(comment.getCommentOwner())
                .orElseThrow(()->new UsernameNotFoundException("Comment username not found"));

        return CommentResponse.builder()
                .owner(userEntity.getUsername())
                .dateOfEdition(comment.getDateOfEdition())
                .dateOfCreation(comment.getDateOfCreation())
                .content(comment.getCommentContent())
                .build();
    }
}
