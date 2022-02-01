package com.spring.controller;

import com.spring.dto.CommentRequest;
import com.spring.dto.CommentResponse;
import com.spring.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments/")
public class CommentController {

    @Autowired
    CommentService commentService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "/add")
    public void addComment(@RequestBody CommentRequest comment) {
        commentService.addCommentToList(comment);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/all/{taskId}")
    public List<CommentResponse> getAllCommentsByTask(@PathVariable("taskId") Long taskId){
        return commentService.getAllTaskComments(taskId);
    }

    @DeleteMapping(path = "/delete/{id}")
    public void deleteComment(@PathVariable("id") Long commentID) {
        commentService.deleteComment(commentID);
    }

    @GetMapping(path = "/{id}")
    public CommentResponse getComment(@PathVariable("id") Long commentID) {
        return commentService.getComment(commentID);
    }

    @PutMapping(path = "/edit/{id}")
    public void editComment(@PathVariable("id") Long commentID, @RequestBody String request) {
        commentService.editComment(commentID,request);
    }
}
