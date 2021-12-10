package com.shopping.shopping_project.api;

import java.util.LinkedHashMap;
import java.util.Map;

import com.shopping.shopping_project.data.MemberVO;
import com.shopping.shopping_project.mapper.MemberMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DashboardAPIController {
    @Autowired MemberMapper mapper;

    @PostMapping("/member/add")
        public Map<String, Object> postMemberAdd(@RequestBody MemberVO data) {
            Map<String, Object> resultmap = new LinkedHashMap<String, Object>();

            mapper.insertMemberadd(data);

            resultmap.put("status", true);
            resultmap.put("message", "회원 가입에 성공했습니다.");

            return resultmap;
        }
}
