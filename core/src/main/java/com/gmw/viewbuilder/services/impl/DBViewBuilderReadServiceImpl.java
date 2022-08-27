package com.gmw.viewbuilder.services.impl;

import com.gmw.viewbuilder.services.DBViewBuilderReadService;
import com.gmw.viewbuilder.tos.ExistingViewTO;

import java.util.List;

public class DBViewBuilderReadServiceImpl implements DBViewBuilderReadService {
    @Override
    public ExistingViewTO obtainViewById(Long viewId) {
        return null;
    }

    @Override
    public ExistingViewTO obtainViewByUserId(Long userId) {
        return null;
    }

    @Override
    public List<ExistingViewTO> obtainViewsByUserId(Long userId) {
        return null;
    }
}
