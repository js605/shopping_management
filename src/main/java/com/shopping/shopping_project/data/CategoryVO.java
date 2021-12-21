package com.shopping.shopping_project.data;

import java.util.Date;

import lombok.Data;

@Data
public class CategoryVO {
    private Integer ci_seq;
    private String ci_name;
    private Date ci_reg_dt;
    private Date ci_mod_dt;
}
