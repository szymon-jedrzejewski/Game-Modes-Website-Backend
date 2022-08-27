package com.gmw.api.rest;

import com.gmw.api.rest.activity.view.CreateViewActivity;
import com.gmw.api.rest.tos.NewViewTO;
import com.gmw.viewbuilder.tos.ViewTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/view")
public class CreateViewController {

    @PostMapping("/create")
    public ResponseEntity<Void> createView(@RequestBody NewViewTO newView) {
        CreateViewActivity createViewActivity = new CreateViewActivity(newView);
        return createViewActivity.execute();
    }
}
