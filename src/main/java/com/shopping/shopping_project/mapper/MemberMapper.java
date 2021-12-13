package com.shopping.shopping_project.mapper;

import java.util.List;

import com.shopping.shopping_project.data.MemberVO;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    public List<MemberVO> getMemberInfo(Integer offset);

    public Integer getMemberCount();

    public void addMember(MemberVO data);

    public void deleteMember(Integer seq);
}
