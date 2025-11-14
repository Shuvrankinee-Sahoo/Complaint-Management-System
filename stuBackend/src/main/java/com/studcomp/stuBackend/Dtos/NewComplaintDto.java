package com.studcomp.stuBackend.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewComplaintDto {
    private String title;
    private String subject;
    private String description;
}
