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
    <link rel="stylesheet" href="/assets/css/product_list.css">
    <script src="/assets/js/product.js"></script>
</head>
<body>
    <main>
        <h1><i class="fas fa-shopping-cart"></i> 상품 관리</h1>
        <button id="add_product"><i class="fas fa-plus-circle"></i> 상품 추가</button>
        <div class="content_area">
            <div class="menu_area">
                <div class="search_box">
                    <select id="search_type">
                        <option value="cate">카테고리</option>
                        <option value="name"
                            <c:if test="${data.type=='name'}">selected</c:if>
                        >상품명</option>
                    </select>
                    <input type="text" id="keyword" placeholder="검색어 입력" value="${data.keyword}">
                    <button id="search_btn"><i class="fas fa-search"></i></button>
                </div>
                <button id="reset_btn">초기화</button>
            </div>
            <div class="table_area">
                <table>
                    <thead>
                        <tr>
                            <th>상품 번호</th>
                            <th>카테고리</th>
                            <th>상품명</th>
                            <th>상품 설명</th>
                            <th>가격</th>
                            <th>재고</th>
                            <th>상품상태</th>
                            <th>등록일</th>
                            <th>수정일</th>
                            <th>조작</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:if test="${data.list.size()==0}">
                            <tr>
                                <td id="nodata" colspan="10">데이터가 없습니다.</td>
                            </tr>
                        </c:if>
                        <c:forEach items="${data.list}" var="p">
                            <tr>
                                <td>${p.pi_seq}</td>
                                <td>${p.category_name}</td>
                                <td>${p.pi_name}</td>
                                <td>${p.pi_sub}</td>
                                <td>${p.pi_price}</td>
                                <td>${p.pi_cnt}</td>
                                <td class="product_status">
                                    <c:if test="${p.pi_status == 1}">
                                        <span style="background-color:rgb(17, 226, 27)">판매중</span>
                                    </c:if>
                                    <c:if test="${p.pi_status == 2}">
                                        <span style="background-color:rgb(255, 110, 26)">품절 임박</span>
                                    </c:if>
                                    <c:if test="${p.pi_status == 3}">
                                        <span style="background-color:rgb(255, 23, 23)">품절</span>
                                    </c:if>
                                </td>
                                <td>${p.pi_reg_dt}</td>
                                <td>${p.pi_mod_dt}</td>
                                <td>
                                    <button class="modify_btn" data-seq="${p.pi_seq}"><i class="far fa-pencil-alt"></i></button>
                                    <button class="delete_btn" data-seq="${p.pi_seq}"><i class="fas fa-minus-circle"></i></button>
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
                        <a href="/product?offset=${(i-1)*10}&keyword=${data.keyword}">${i}</a>
                    </c:forEach>
                </div>
                <button id="next"><i class="fas fa-chevron-right"></i></button>
            </div>
        </div>
    </main>
    <div class="popup_wrap">
        <div class="popup" id="product_add">
            <div class="top_area">
                <div class="ico">
                    <i class="fas fa-shopping-cart"></i>
                </div>
                <h2>회원 추가</h2>
                <p>회원 정보를 입력해주세요</p>
            </div>
            <div class="content_area">
                <input type="text" id="product_name" placeholder="상품명">
                <select id="product_cate">
                    <option value="1">상의</option>
                    <option value="2">하의</option>
                    <option value="3">신발</option>
                    <option value="4">아우터</option>
                    <option value="5">악세사리</option>
                </select>
                <input type="text" id="product_sub" placeholder="상품 설명">
                <input type="text" id="product_price" placeholder="가격">
                <input type="text" id="product_cnt" placeholder="재고">
                <select id="product_status">
                    <option value="1">판매중</option>
                    <option value="2">품절 임박</option>
                    <option value="3">품절</option>
                </select>
            </div>
            <div class="btn_area">
                <button id="add_pro">등록하기</button>
                <button id="modify_pro">수정하기</button>
                <button id="cancel_pro">취소하기</button>
            </div>
        </div>
    </div>
</body>
</html>