package com.gmw.api.rest;

import com.gmw.api.rest.activity.comment.CreateCommentActivity;
import com.gmw.api.rest.activity.comment.DeleteCommentActivity;
import com.gmw.api.rest.activity.comment.FindAllCommentsForModActivity;
import com.gmw.api.rest.activity.comment.UpdateCommentActivity;
import com.gmw.comment.tos.ExistingCommentTO;
import com.gmw.comment.tos.NewCommentTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @GetMapping("/findCommentsForMod/{modId}")
    public ResponseEntity<List<ExistingCommentTO>> findComments(@PathVariable Long modId) {
        FindAllCommentsForModActivity activity = new FindAllCommentsForModActivity(modId);
        return activity.execute();
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createComment(@RequestBody NewCommentTO comment) {
        CreateCommentActivity activity = new CreateCommentActivity(comment);
        return activity.execute();
    }

    @PutMapping("/update")
    public ResponseEntity<Void> updateComment(@RequestBody ExistingCommentTO existingCommentTO) {
        UpdateCommentActivity activity = new UpdateCommentActivity(existingCommentTO);
        return activity.execute();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        DeleteCommentActivity activity = new DeleteCommentActivity(id);
        return activity.execute();
    }
}
