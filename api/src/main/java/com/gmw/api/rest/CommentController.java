package com.gmw.api.rest;

import com.gmw.api.rest.activity.comment.CreateCommentActivity;
import com.gmw.api.rest.activity.comment.DeleteCommentActivity;
import com.gmw.api.rest.activity.comment.FindAllCommentsActivity;
import com.gmw.api.rest.activity.comment.UpdateCommentActivity;
import com.gmw.comment.tos.ExistingCommentTO;
import com.gmw.comment.tos.NewCommentTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
public class CommentController {

    @GetMapping("/findAllByModId/{modId}")
    public ResponseEntity<List<ExistingCommentTO>> findAllComments(@PathVariable Long modId) {
        FindAllCommentsActivity activity = new FindAllCommentsActivity(modId);
        return activity.execute();
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createComment(@RequestHeader(HttpHeaders.AUTHORIZATION) String token,
                                              @RequestBody NewCommentTO comment) {
        CreateCommentActivity activity = new CreateCommentActivity(comment, token);
        return activity.execute();
    }

    @PutMapping("/update")
    public ResponseEntity<Void> updateComment(@RequestHeader(HttpHeaders.AUTHORIZATION) String token,
                                              @RequestBody ExistingCommentTO existingCommentTO) {
        UpdateCommentActivity activity = new UpdateCommentActivity(existingCommentTO, token);
        return activity.execute();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteComment(@RequestHeader(HttpHeaders.AUTHORIZATION) String token,
                                              @PathVariable Long id) {
        DeleteCommentActivity activity = new DeleteCommentActivity(id, token);
        return activity.execute();
    }
}
