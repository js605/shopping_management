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
    <link rel="stylesheet" href="/assets/css/index.css">
    <%@include file="/WEB-INF/includes/header.jsp"%>
    <script>
        $(function(){
            $(".main_menu a:nth-child(1)").addClass("active")
        })
    </script>
</head>
<body>
    <main>
        <h1>쇼핑몰관리 대시보드 (ShoppingMall Management Dashboard)</h1>
        <div class="content_area">
            <div class="member_info">
                <h2><i class="fas fa-users"></i> 회원 관리</h2>
                <p>총 회원 : <span>${cnt.member[0]}명</span></p>
                <p>일반 회원 : <span>${cnt.member[1]}명</span></p>
                <p>VIP 회원 : <span>${cnt.member[2]}명</span></p>
                <p>탈퇴 대기 : <span>${cnt.member[3]}명</span></p>
                <p><i class="far fa-clock"></i> 업데이트 날짜 : <span>2021-12-10 12:00:00</span></p>
            </div>
            <div class="product_info">
                <h2><i class="fas fa-shopping-cart"></i> 상품 관리</h2>
                <p>총 상품 수 : <span>${cnt.product[0]}개</span></p>
                <p>판매 중 상품 : <span>${cnt.product[1]}개</span></p>
                <p>입고 대기 상품 : <span>${cnt.product[2]}개</span></p>
                <p><i class="far fa-clock"></i> 업데이트 날짜 : <span>2021-12-10 12:00:00</span></p>
            </div>
            <div class="review_info">
                <h2><i class="fas fa-thumbs-up"></i> 리뷰 관리</h2>
                <p>작성된 리뷰 : <span>${cnt.review[0]}건</span></p>
                <p>5점 리뷰 : <span>${cnt.review[1]}건</span></p>
                <p>4점 리뷰 : <span>${cnt.review[2]}건</span></p>
                <p>3점 리뷰 : <span>${cnt.review[3]}건</span></p>
                <p>2점 리뷰 : <span>${cnt.review[4]}건</span></p>
                <p>1점 리뷰 : <span>${cnt.review[5]}건</span></p>
                <p><i class="far fa-clock"></i> 업데이트 날짜 : <span>2021-12-10 12:00:00</span></p>
            </div>
            <div class="questions_info">
                <h2><i class="far fa-question-circle"></i> 문의 관리</h2>
                <p>총 문의 : <span>${cnt.questions[0]}건</span></p>
                <p>답변 대기 : <span>${cnt.questions[1]}건</span></p>
                <p>답변 완료 : <span>${cnt.questions[2]}건</span></p>
                <p><i class="far fa-clock"></i> 업데이트 날짜 : <span>2021-12-10 12:00:00</span></p>
            </div>
            </div>
        </div>
    </main>
</body>
</html>