package com.gmw.api.rest;

import com.gmw.api.rest.activity.rating.CreateRatingActivity;
import com.gmw.api.rest.activity.rating.DeleteRatingActivity;
import com.gmw.api.rest.activity.rating.UpdateRatingActivity;
import com.gmw.rating.tos.ExistingRatingTO;
import com.gmw.rating.tos.NewRatingTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rating")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
public class RatingController {

    @PostMapping("/create")
    public ResponseEntity<Void> createRating(@RequestHeader(HttpHeaders.AUTHORIZATION) String token,
                                             @RequestBody NewRatingTO rating) {
        CreateRatingActivity activity = new CreateRatingActivity(rating, token);
        return activity.execute();
    }

    @PutMapping("/update")
    public ResponseEntity<Void> updateRating(@RequestHeader(HttpHeaders.AUTHORIZATION) String token,
                                             @RequestBody ExistingRatingTO existingRatingTO) {
        UpdateRatingActivity activity = new UpdateRatingActivity(existingRatingTO, token);
        return activity.execute();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteRating(@RequestHeader(HttpHeaders.AUTHORIZATION) String token,
                                             @PathVariable Long id) {
        DeleteRatingActivity activity = new DeleteRatingActivity(id, token);
        return activity.execute();
    }
}
