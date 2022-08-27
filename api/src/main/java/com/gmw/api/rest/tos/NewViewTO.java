package com.gmw.api.rest.tos;

import lombok.Data;

import java.util.List;

@Data
public class NewViewTO {
    Long gameId;
    List<FieldTO> fields;
}
