package com.example.fim.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Relation {
    private Integer id;
    private Long userId;
    private Long friendId;
    private Long relationshipTime;
    private Long strangeTime;
    private String friendNickname;
}
