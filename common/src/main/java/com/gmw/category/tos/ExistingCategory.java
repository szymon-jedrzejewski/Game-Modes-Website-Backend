package com.gmw.category.tos;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ExistingCategory extends NewCategory {
    private Long id;

    @Builder
    public ExistingCategory(String name, Long id) {
        super(name);
        this.id = id;
    }
}
