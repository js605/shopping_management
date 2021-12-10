package com.shopping.shopping_project.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DashboardMapper {
    public Integer getTotalMemberCnt();
    public Integer getNormalMemberCnt();
    public Integer getVIPMemberCnt();
    public Integer getWaitMemberCnt();

    public Integer getTotalProductCnt();
    public Integer getSaleProductCnt();
    public Integer getWaitProductCnt();

    public Integer getTotalReviewCnt();
    public Integer getFiveReviewCnt();
    public Integer getFourReviewCnt();
    public Integer getThreeReviewCnt();
    public Integer getTwoReviewCnt();
    public Integer getOneReviewCnt();
    
    public Integer getTotalQuestionsCnt();
    public Integer getWaitQuestionsCnt();
    public Integer getDoneQuestionsCnt();
}
