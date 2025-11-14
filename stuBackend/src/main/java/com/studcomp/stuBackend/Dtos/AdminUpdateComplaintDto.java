package com.studcomp.stuBackend.Dtos;

import lombok.Data;

@Data
public class AdminUpdateComplaintDto {
    private int id;
    private String status;
    private String response;
}
