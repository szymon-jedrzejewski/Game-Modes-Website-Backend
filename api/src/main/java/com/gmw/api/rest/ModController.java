package com.gmw.api.rest;

import com.gmw.api.rest.activity.mod.*;
import com.gmw.api.rest.activity.mod.tos.ModDTO;
import com.gmw.fieldvalue.tos.SearchFieldValue;
import com.gmw.mod.tos.ExistingModTO;
import com.gmw.mod.tos.NewModTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mod")
public class ModController {

    @GetMapping("/getMod/{modId}")
    public ResponseEntity<ModDTO> obtainMod(@PathVariable Long modId) {
        FindModByIdActivity activity = new FindModByIdActivity(modId);
        return activity.execute();
    }

    @GetMapping("/findModsByGameId/{gameId}")
    public ResponseEntity<List<ExistingModTO>> obtainModsByGameId(@PathVariable Long gameId) {
        FindModsByGameIdActivity activity = new FindModsByGameIdActivity(gameId);
        return activity.execute();
    }

    @PostMapping("/findModsBySearchValues")
    public ResponseEntity<List<ExistingModTO>> obtainModsByGameId(@RequestBody List<SearchFieldValue> searchValues) {
        FindModsBySearchValuesActivity activity = new FindModsBySearchValuesActivity(searchValues);
        return activity.execute();
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<ExistingModTO>> obtainAllMods() {
        FindAllModsActivity activity = new FindAllModsActivity();
        return activity.execute();
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createMod(@RequestBody NewModTO mod) {
        CreateModActivity activity = new CreateModActivity(mod);
        return activity.execute();
    }

    @PutMapping("/update")
    public ResponseEntity<Void> updateMod(@RequestBody ExistingModTO existingModTO) {
        UpdateModActivity activity = new UpdateModActivity(existingModTO);
        return activity.execute();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteMod(@PathVariable Long id) {
        DeleteModActivity activity = new DeleteModActivity(id);
        return activity.execute();
    }
}
