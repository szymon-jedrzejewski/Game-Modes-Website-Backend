package com.gmw.viewbuilder.tos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewGameTO {
    private String name;
    private byte[] avatar;
    private String description;
}
