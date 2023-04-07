package com.example.fim.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Apply {
    private Integer id;
    private Long sponsorId;
    private Long recipientId;
    private Long sponsorTime;
    private Integer sponsorStatus;
}
