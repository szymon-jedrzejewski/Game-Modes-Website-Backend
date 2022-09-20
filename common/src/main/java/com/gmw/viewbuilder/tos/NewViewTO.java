package com.gmw.viewbuilder.tos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class NewViewTO {
    private Long gameId;
    private List<NewFieldTO> fields;
}
