package com.mobileBank.pojo;

import lombok.Data;

@Data
public class TUsersSearch {
    private Integer sex;
    private String birthPlace;
    private Integer grade_min;
    private Integer grade_max;
}
