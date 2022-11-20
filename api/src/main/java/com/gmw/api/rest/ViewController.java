package com.gmw.api.rest;

import com.gmw.api.rest.activity.view.CreateViewActivity;
import com.gmw.api.rest.activity.view.DeleteViewActivity;
import com.gmw.api.rest.activity.view.FindViewActivity;
import com.gmw.api.rest.activity.view.UpdateViewActivity;
import com.gmw.view.tos.ExistingViewTO;
import com.gmw.view.tos.NewViewTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/view")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
public class ViewController {

    @PostMapping("/create")
    public ResponseEntity<Void> createView(@RequestBody NewViewTO newView) {
        CreateViewActivity createViewActivity = new CreateViewActivity(newView);
        return createViewActivity.execute();
    }

    @GetMapping("/getViewByGameId/{gameId}")
    public ResponseEntity<ExistingViewTO> getViewByGameId(@PathVariable Long gameId) {
        FindViewActivity activity = new FindViewActivity(gameId);
        return activity.execute();
    }

    @PutMapping("/update")
    public ResponseEntity<Void> updateView(@RequestBody ExistingViewTO existingViewTO) {
        UpdateViewActivity activity = new UpdateViewActivity(existingViewTO);
        return activity.execute();
    }

    @DeleteMapping("/delete/{viewId}")
    public ResponseEntity<Void> deleteView(@PathVariable Long viewId) {
        DeleteViewActivity activity = new DeleteViewActivity(viewId);
        return activity.execute();
    }
}
