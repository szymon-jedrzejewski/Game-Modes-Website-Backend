package com.gmw.services.fieldvalues;

import com.gmw.fieldvalue.tos.SearchFieldValue;
import com.gmw.services.exceptions.ResourceNotFoundException;

import java.util.List;

public interface DBFieldValueReadService {
    List<Long> obtainModsIdsBySearchValues(List<SearchFieldValue> searchFieldValues) throws ResourceNotFoundException;
    List<Long> obtainFieldValuesIdsByModId(Long modId) throws ResourceNotFoundException;
}
