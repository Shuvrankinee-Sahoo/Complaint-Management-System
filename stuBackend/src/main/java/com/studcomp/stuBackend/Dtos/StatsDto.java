package com.studcomp.stuBackend.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatsDto {
    private long total;
    private long pending;
    private long in_progress;
    private long resolved;
    private long rejected;
}
