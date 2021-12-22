package com.shopping.shopping_project.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.shopping.shopping_project.mapper.DashboardMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {
    @Autowired DashboardMapper mapper;

    public Map<String, Object> getCounts() {
        List<Integer> memberCntList = new ArrayList<Integer>();
        memberCntList.add(mapper.getTotalMemberCnt());
        memberCntList.add(mapper.getNormalMemberCnt());
        memberCntList.add(mapper.getVIPMemberCnt());
        memberCntList.add(mapper.getWaitMemberCnt());
        
        List<Integer> productCntList = new ArrayList<Integer>();
        productCntList.add(mapper.getTotalProductCnt());
        productCntList.add(mapper.getSaleProductCnt());
        productCntList.add(mapper.getWaitProductCnt());
        
        List<Integer> reviewCntList = new ArrayList<Integer>();
        reviewCntList.add(mapper.getTotalReviewCnt());
        reviewCntList.add(mapper.getFiveReviewCnt());
        reviewCntList.add(mapper.getFourReviewCnt());
        reviewCntList.add(mapper.getThreeReviewCnt());
        reviewCntList.add(mapper.getTwoReviewCnt());
        reviewCntList.add(mapper.getOneReviewCnt());
        
        List<Integer> questionsCntList = new ArrayList<Integer>();
        questionsCntList.add(mapper.getTotalQuestionsCnt());
        questionsCntList.add(mapper.getWaitQuestionsCnt());
        questionsCntList.add(mapper.getDoneQuestionsCnt());
        
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("member", memberCntList);
        map.put("product", productCntList);
        map.put("review", reviewCntList);
        map.put("questions", questionsCntList);
        return map;
    }
    public Map<String, Object> getUpdateDate() {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

        resultMap.put("member", mapper.getMemberUpdateDate());
        resultMap.put("product", mapper.getProductUpdateDate());

        return resultMap;
    }
}
