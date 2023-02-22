package com.gmw.api.rest;

import com.gmw.api.rest.activity.rating.CreateRatingActivity;
import com.gmw.api.rest.activity.rating.DeleteRatingActivity;
import com.gmw.api.rest.activity.rating.UpdateRatingActivity;
import com.gmw.rating.tos.ExistingRatingTO;
import com.gmw.rating.tos.NewRatingTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rating")
public class RatingController {

    @PostMapping("/create")
    public ResponseEntity<Void> createRating(@RequestBody NewRatingTO rating) {
        CreateRatingActivity activity = new CreateRatingActivity(rating);
        return activity.execute();
    }

    @PutMapping("/update")
    public ResponseEntity<Void> updateRating(@RequestParam Long userId, @RequestBody ExistingRatingTO existingRatingTO) {
        UpdateRatingActivity activity = new UpdateRatingActivity(existingRatingTO, userId);
        return activity.execute();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteRating(@RequestParam Long userId, @PathVariable Long id) {
        DeleteRatingActivity activity = new DeleteRatingActivity(id, userId);
        return activity.execute();
    }
}
