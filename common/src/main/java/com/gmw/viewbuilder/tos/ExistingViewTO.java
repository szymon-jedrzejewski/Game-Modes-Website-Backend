package com.gmw.viewbuilder.tos;

import lombok.Data;

import java.util.List;

@Data
public class ExistingViewTO {
    private Long id;
    private Long gameId;
    private List<ExistingFieldTO> fields;
}
