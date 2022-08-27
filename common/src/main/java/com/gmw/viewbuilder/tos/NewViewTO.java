package com.gmw.viewbuilder.tos;

import lombok.Data;

import java.util.List;

@Data
public class NewViewTO {
    Long gameId;
    List<NewFieldTO> fields;
}
