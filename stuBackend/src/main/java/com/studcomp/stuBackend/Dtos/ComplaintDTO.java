package com.studcomp.stuBackend.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComplaintDTO {
    private int id;
    private String mail;
    private String description;
    private String title;
    private String sub;
    private String status;
    private String response;
    private String date;
}
