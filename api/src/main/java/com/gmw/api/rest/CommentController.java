package com.gmw.api.rest;

import com.gmw.api.rest.activity.comment.CreateCommentActivity;
import com.gmw.api.rest.activity.comment.DeleteCommentActivity;
import com.gmw.api.rest.activity.comment.UpdateCommentActivity;
import com.gmw.comment.tos.ExistingCommentTO;
import com.gmw.comment.tos.NewCommentTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @PostMapping("/create")
    public ResponseEntity<Void> createComment(@RequestParam Long userId, @RequestBody NewCommentTO comment) {
        CreateCommentActivity activity = new CreateCommentActivity(comment, userId);
        return activity.execute();
    }

    @PutMapping("/update")
    public ResponseEntity<Void> updateComment(@RequestParam Long userId, @RequestBody ExistingCommentTO existingCommentTO) {
        UpdateCommentActivity activity = new UpdateCommentActivity(existingCommentTO, userId);
        return activity.execute();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteComment(@RequestParam Long userId, @PathVariable Long id) {
        DeleteCommentActivity activity = new DeleteCommentActivity(id, userId);
        return activity.execute();
    }
}
