package com.gmw.services.mod;

import com.gmw.mod.tos.ExistingModTO;
import com.gmw.mod.tos.NewModTO;
import com.gmw.services.exceptions.ResourceNotCreatedException;
import com.gmw.services.exceptions.ResourceNotDeletedException;
import com.gmw.services.exceptions.ResourceNotUpdatedException;

public interface DBModService {
    void createMod(NewModTO newMod) throws ResourceNotCreatedException;
    void deleteMod(Long modId) throws ResourceNotDeletedException;
    void updateMod(ExistingModTO existingModTO) throws ResourceNotUpdatedException;
}
