package com.shopping.shopping_project.mapper;

import com.shopping.shopping_project.data.MemberVO;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    public void insertMemberadd(MemberVO data);
}
