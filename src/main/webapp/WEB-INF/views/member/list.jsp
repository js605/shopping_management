<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <%@include file="/WEB-INF/includes/header.jsp"%>
    <link rel="stylesheet" href="/assets/css/member_list.css">
    <script src="/assets/js/member.js"></script>
</head>
<body>
    <main>
        <h1><i class="fas fa-users"></i> 회원 관리</h1>
        <button id="add_member"><i class="fas fa-plus-circle"></i> 회원 추가</button>
        <div class="content_area">
            <div class="menu_area">
                <div class="search_box">
                    <input type="text" id="keyword" placeholder="검색어 입력" value="${data.keyword}">
                    <button id="search_btn"><i class="fas fa-search"></i></button>
                </div>
                <button id="reset_btn">초기화</button>
            </div>
            <div class="table_area">
                <table>
                    <thead>
                        <tr>
                            <th>회원 번호</th>
                            <th>이름</th>
                            <th>생년월일</th>
                            <th>전화번호</th>
                            <th>아이디</th>
                            <th>비밀번호</th>
                            <th>회원상태</th>
                            <th>등록일</th>
                            <th>수정일</th>
                            <th>조작</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:if test="${data.total==0}">
                            <tr>
                                <td id="nodata" colspan="6">데이터가 없습니다.</td>
                            </tr>
                        </c:if>
                        <c:forEach items="${data.list}" var="m">
                            <tr>
                                <td>${m.mi_seq}</td>
                                <td>${m.mi_name}</td>
                                <td>${m.mi_birth}</td>
                                <td>${m.mi_phone_num}</td>
                                <td>${m.mi_id}</td>
                                <td>********</td>
                                <td>${m.mi_status}</td>
                                <!-- <c:if test="${m.mi_status == 0}">일반 회원</c:if>
                                <c:if test="${m.mi_status == 1}">VIP 회원</c:if>
                                <c:if test="${m.mi_status == 2}">탈퇴 대기</c:if> -->
                                <td>${m.mi_reg_dt}</td>
                                <td>${m.mi_mod_dt}</td>
                                <td>
                                    <button class="modify_btn" data-seq="${m.mi_seq}"><i class="far fa-pencil-alt"></i></button>
                                    <button class="delete_btn" data-seq="${m.mi_seq}"><i class="fas fa-minus-circle"></i></button>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="pager_area">
                <button id="prev"><i class="fas fa-chevron-left"></i></button>
                <div class="pagers">
                    <c:forEach begin="1" end="${data.pageCnt}" var="i">
                        <a href="/member?offset=${(i-1)*10}&keyword=${data.keyword}">${i}</a>
                    </c:forEach>
                </div>
                <button id="next"><i class="fas fa-chevron-right"></i></button>
            </div>
        </div>
    </main>
    <div class="popup_wrap">
        <div class="popup" id="member_add">
            <div class="top_area">
                <div class="ico">
                    <i class="fas fa-school"></i>
                </div>
                <h2>회원 추가</h2>
                <p>회원 정보를 입력해주세요</p>
            </div>
            <div class="content_area">
                <input type="text" id="mem_name" placeholder="회원 이름"><br>
                <input type="text" id="mem_birth" placeholder="생년월일">
                <input type="text" id="mem_phone_num" placeholder="전화번호">
                <input type="text" id="mem_id" placeholder="아이디">
                <input type="text" id="mem_pwd" placeholder="비밀번호">
                <select id="mem_status">
                    <option value="1">일반회원</option>
                    <option value="2">VIP</option>
                    <option value="3">탈퇴대기</option>
                </select>
            </div>
            <div class="btn_area">
                <button id="add_mem">등록하기</button>
                <button id="modify_mem">수정하기</button>
                <button id="cancel_mem">취소하기</button>
            </div>
        </div>
    </div>
</body>
</html>