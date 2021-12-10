package com.shopping.shopping_project.data;

import java.util.Date;

import lombok.Data;
@Data
public class MemberVO {
    private Integer mi_seq;
    private String mi_name;
    private String mi_birth;
    private String mi_phone_num;
    private String mi_id;
    private String mi_pwd;
    private Integer mi_grade;
    private Integer mi_status;
    private Date mi_reg_dt;
    private Date mi_mod_dt;
}
