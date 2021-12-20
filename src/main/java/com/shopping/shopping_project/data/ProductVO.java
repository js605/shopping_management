package com.shopping.shopping_project.data;

import java.util.Date;

import lombok.Data;

@Data
public class ProductVO {
    private Integer pi_seq;
    private Integer pi_ci_seq;
    private String pi_name;
    private String pi_sub;
    private Integer pi_price;
    private Integer pi_status;
    private Integer pi_cnt;
    private Date pi_reg_dt;
    private Date pi_mod_dt;

    private String category_name;
}
