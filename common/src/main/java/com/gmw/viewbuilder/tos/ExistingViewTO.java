package com.gmw.viewbuilder.tos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExistingViewTO {
    private Long id;
    private Long gameId;
    private List<ExistingFieldTO> fields;
}
