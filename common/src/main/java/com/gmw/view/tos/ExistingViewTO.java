package com.gmw.view.tos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Builder
public class ExistingViewTO {
    private Long id;
    private Long gameId;
    @Setter
    private List<ExistingFieldTO> fields;
}
