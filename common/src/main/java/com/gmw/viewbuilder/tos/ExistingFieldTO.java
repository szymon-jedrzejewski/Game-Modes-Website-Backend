package com.gmw.viewbuilder.tos;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ExistingFieldTO extends NewFieldTO {
    private Long id;
}
