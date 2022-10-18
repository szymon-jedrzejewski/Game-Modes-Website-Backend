package com.gmw.viewbuilder.tos;

import lombok.Builder;

import java.util.List;

@Builder
public class ExistingViewTO {
    private Long id;
    private Long gameId;
    private List<ExistingFieldTO> fields;
}
