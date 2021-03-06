package com.shopping.shopping_project.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.shopping.shopping_project.data.MemberHistoryVO;
import com.shopping.shopping_project.data.MemberVO;
import com.shopping.shopping_project.mapper.MemberMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    @Autowired
    MemberMapper mapper;
    public Map<String, Object> getMemberList(Integer offset, String keyword) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        if(offset == null) {
            offset = 0;
            resultMap.put("offset", offset);
        }
        if(keyword == null) {
            keyword = "%%";
            resultMap.put("keyword", "");
        }

        else{
            resultMap.put("keyword", keyword);
            keyword = "%"+keyword+"%";
        }

        List<MemberVO> list = mapper.getMemberInfo(offset, keyword);

        Integer cnt = mapper.getMemberCount(keyword);
        Integer page_cnt = cnt / 10;
        if(cnt % 10 > 0) page_cnt++;

        // Integer page_cnt = cnt / 10 + (cnt%10>0?1:0);

        resultMap.put("status", true);
        resultMap.put("total", cnt);
        resultMap.put("pageCnt", page_cnt);
        resultMap.put("list", list);
        return resultMap;
    }
    public Map<String, Object> addMember(MemberVO data) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

        if(data.getMi_name() == null || data.getMi_name().equals("")) {
            resultMap.put("status", false);
            resultMap.put("message", "이름을 입력하세요.");
            return resultMap;
        }
        if(data.getMi_birth() == null || data.getMi_birth().equals("")) {
            resultMap.put("status", false);
            resultMap.put("message", "생년월일을 입력하세요.");
            return resultMap;
        }
        if(data.getMi_phone_num() == null || data.getMi_phone_num().equals("")) {
            resultMap.put("status", false);
            resultMap.put("message", "전화번호를 입력하세요.");
            return resultMap;
        }
        if(data.getMi_id() == null || data.getMi_id().equals("")) {
            resultMap.put("status", false);
            resultMap.put("message", "아이디을 입력하세요.");
            return resultMap;
        }
        if(data.getMi_pwd() == null || data.getMi_pwd().equals("")) {
            resultMap.put("status", false);
            resultMap.put("message", "비밀번호을 입력하세요.");
            return resultMap;
        }
        mapper.addMember(data);
        resultMap.put("status", true);
        resultMap.put("message", "회원이 추가되었습니다.");

        Integer seq = mapper.selectLatestDateSeq();
        MemberHistoryVO history = new MemberHistoryVO();
        history.setMemh_mi_seq(seq);
        history.setMemh_type("new");
        String content = data.getMi_name()+"|"+data.getMi_birth()+"|"+data.getMi_phone_num()+"|"+data.getMi_id()+"|"+data.getMi_status();
        history.setMemh_content(content);
        mapper.insertMemberHistory(history);

        return resultMap;
    }
    public Map<String, Object> deleteMember(Integer seq) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        mapper.deleteMember(seq);
        resultMap.put("status", true);
        resultMap.put("message", "회원이 삭제되었습니다.");

        MemberHistoryVO history = new MemberHistoryVO();
        history.setMemh_mi_seq(seq);
        history.setMemh_type("delete");
        mapper.insertMemberHistory(history);

        return resultMap;
    }
    public Map<String, Object> getMemberInfoBySeq(Integer seq) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        mapper.getMemberInfoBySeq(seq);
        resultMap.put("status", true);
        resultMap.put("data", mapper.getMemberInfoBySeq(seq));
        return resultMap;
    }
    public Map<String, Object> updateMemberInfo(MemberVO data) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

        mapper.updateMember(data);

        resultMap.put("status", true);
        resultMap.put("message", "수정되었습니다.");

        MemberHistoryVO history = new MemberHistoryVO();
        history.setMemh_mi_seq(data.getMi_seq());
        history.setMemh_type("update");
        String content = data.getMi_name()+"|"+data.getMi_birth()+"|"+data.getMi_phone_num()+"|"+data.getMi_id()+"|"+data.getMi_status();
        history.setMemh_content(content);
        mapper.insertMemberHistory(history);

        return resultMap;
    }
}
