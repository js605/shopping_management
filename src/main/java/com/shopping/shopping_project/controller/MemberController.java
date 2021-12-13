package com.shopping.shopping_project.controller;


import java.util.Map;


import com.shopping.shopping_project.service.MemberService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MemberController {
    @Autowired
    MemberService service;
    @GetMapping("/member")
    public String getMember(Model model, @RequestParam @Nullable Integer offset) {
        Map<String, Object> resultMap = service.getMemberList(offset);
        model.addAttribute("data", resultMap);

        return "/member/list";
    }
}
