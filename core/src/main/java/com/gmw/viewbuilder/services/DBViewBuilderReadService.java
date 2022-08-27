package com.gmw.viewbuilder.services;

import com.gmw.viewbuilder.tos.ExistingViewTO;

import java.util.List;

public interface DBViewBuilderReadService {
    ExistingViewTO obtainViewById(Long viewId);
    ExistingViewTO obtainViewByUserId(Long userId);
    List<ExistingViewTO> obtainViewsByUserId(Long userId);
}
