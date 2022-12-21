package com.gmw.view.tos;

import field.tos.ExistingFieldTO;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Builder
@EqualsAndHashCode
public class ExistingViewTO {
    private Long id;
    private Long gameId;
    @Setter
    private List<ExistingFieldTO> fields;
}
