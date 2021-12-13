package com.shopping.shopping_project.api;

import java.util.Map;

import com.shopping.shopping_project.data.MemberVO;
import com.shopping.shopping_project.service.MemberService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberAPIController {
    @Autowired MemberService service;

    @PostMapping("/member/add")
    public Map<String, Object> postDepartmentAdd(@RequestBody MemberVO data) {
        return service.addMember(data);
    }
    @DeleteMapping("/member/delete")
    public Map<String, Object> deleteMember(@RequestParam Integer seq) {
    return service.deleteMember(seq);
    }
}
