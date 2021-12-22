package com.shopping.shopping_project.api;

import java.util.Map;

import com.shopping.shopping_project.data.MemberVO;
import com.shopping.shopping_project.service.MemberService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberAPIController {
    @Autowired MemberService service;

    @PostMapping("/member/add")
    public Map<String, Object> postMemberAdd(@RequestBody MemberVO data) throws Exception {
        return service.addMember(data);
    }
    @DeleteMapping("/member/delete")
    public Map<String, Object> deleteMember(@RequestParam Integer seq) {
    return service.deleteMember(seq);
    }
    @GetMapping("/member/get")
    public Map<String, Object> getMemberInfoBySeq(Integer seq) {
        return service.getMemberInfoBySeq(seq);
    }
    @PatchMapping("/member/update")
    public Map<String, Object> updateMember(@RequestBody MemberVO data) {
        return service.updateMemberInfo(data);
    }
}
