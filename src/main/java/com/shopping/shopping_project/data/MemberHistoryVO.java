package com.shopping.shopping_project.data;

import java.util.Date;

import lombok.Data;

@Data
public class MemberHistoryVO {
    private Integer memh_seq;
    private String memh_type;
    private String memh_content;
    private Date memh_reg_dt;
    private Integer memh_mi_seq;
}
