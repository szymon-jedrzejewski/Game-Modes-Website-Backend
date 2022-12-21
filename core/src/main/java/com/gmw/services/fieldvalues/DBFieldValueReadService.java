package com.gmw.services.fieldvalues;

import com.gmw.fieldvalue.tos.SearchFieldValue;
import com.gmw.services.exceptions.ResourceNotDeletedException;
import com.gmw.services.exceptions.ResourceNotFoundException;

import java.util.List;

public interface DBFieldValueReadService {
    List<Long> obtainModsIsBySearchValues(List<SearchFieldValue> searchFieldValues) throws ResourceNotDeletedException, ResourceNotFoundException;
}
