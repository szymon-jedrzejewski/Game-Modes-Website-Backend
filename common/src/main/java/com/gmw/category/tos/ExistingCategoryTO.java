package com.gmw.category.tos;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ExistingCategoryTO extends NewCategoryTO {
    private Long id;

    @Builder
    public ExistingCategoryTO(String name, Long id) {
        super(name);
        this.id = id;
    }
}
