package com.gmw.view.tos;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ExistingViewTO {
    private Long id;
    private Long gameId;
    private List<ExistingFieldTO> fields;
}
