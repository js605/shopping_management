package com.shopping.shopping_project.mapper;

import java.util.List;

import com.shopping.shopping_project.data.MemberHistoryVO;
import com.shopping.shopping_project.data.MemberVO;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    public List<MemberVO> getMemberInfo(Integer offset, String keyword);

    public Integer getMemberCount(String keyword);

    public void addMember(MemberVO data);

    public void deleteMember(Integer seq);

    public MemberVO getMemberInfoBySeq(Integer seq);

    public void updateMember(MemberVO data);

    public Integer selectLatestDateSeq();

    public void insertMemberHistory(MemberHistoryVO data);
}
