// teacher.js
$(function(){
    $(".main_menu a:nth-child(3)").addClass("active");
    $("#search_cate").click(function(){
        $(".category_search").css("display", "block");
    });
    $("#cate_search_close").click(function(){
        $(".category_search").css("display", "");
    });
    $("#cate_keyword").keyup(function(e){
        if(e.keyCode == 13) $("#cate_search_btn").trigger("click");
    })
    $("#cate_search_btn").click(function(){
        $.ajax({
            url:"/product/cate?keyword="+$("#cate_keyword").val(),
            type:"get",
            success:function(r) {
                console.log(r);
                $(".search_result ul").html("");
                for(let i=0; i<r.length; i++) {
                    // let str_status = "";
                    // if(r[i].pi_status == 1) str_status = "어떤상태1"
                    // if(r[i].pi_status == 2) str_status = "어떤상태2"
                    // if(r[i].pi_status == 3) str_status = "어떤상태3"
                    let tag = 
                        '<li>'+
                            '<a href="#" data-cate-seq="'+r[i].ci_seq+'">'+r[i].ci_name+'</a>'+
                            // '<span class="status'+r[i].pi_status+'">'+str_status+'</span>'+
                        '</li>';
                    $(".search_result ul").append(tag);
                }

                $(".search_result ul a").click(function(e){
                    e.preventDefault(); // a태그의 링크 기능 제거
                    let seq = $(this).attr("data-cate-seq");
                    let name = $(this).html();

                    $("#product_cate_name").attr("data-cate-seq", seq);
                    $("#product_cate_name").val(name);

                    $(".search_result ul").html("");
                    $("#cate_keyword").val("");
                    $(".category_search").css("display", "");
                })
            }
        })
    })
    $("#add_pro").click(function(){
        let product_name = $("#product_name").val();
        // let product_cate = $("#product_cate option:selected").val();
        let product_price = $("#product_price").val();
        let product_sub = $("#product_sub").val();
        let product_cnt = $("#product_cnt").val();
        let product_status = $("#product_status option:selected").val();

        if(product_name == undefined) {
            alert("상품명을 입력해주세요");
            return;
        }
        if(product_sub == '') {
            alert("상품 설명을 입력해주세요");
            return;
        }

        let data = {
            pi_name:product_name,
            // pi_ci_seq:product_cate,
            pi_sub:product_sub,
            pi_price:product_price,
            pi_cnt:product_cnt,
            pi_status:product_status
        }
        $.ajax({
            type:"post",
            url:"/product/add",
            data:JSON.stringify(data),
            contentType:"application/json",
            success:function(e) {
                alert(e.message);
                if(e.status)
                    location.reload();
            }
        })
    })
    $("#add_product").click(function(){
        $(".popup_wrap").css("display", "block");
        $("#modify_pro").css("display", "none");
        $("#cancel_pro").css("display", "inline-block");
        $(".popup .top_area h2").html("상품 추가");
        $(".popup .top_area p").html("상품 정보를 입력해주세요");;
    })
    

    $("#search_btn").click(function(){
        let type = $("#search_type option:selected").val();
        let keyword = $("#keyword").val();

        location.href = "/product?type="+type+"&keyword="+keyword;
    })
    $(".delete_btn").click(function(){
        if(confirm("상품을 삭제하시겠습니까?\n(이 동작은 되돌릴 수 없습니다.)") == false) return;
        let seq = $(this).attr("data-seq");

        $.ajax({
            type:"delete",
            url:"/product/delete?seq="+seq,
            success:function(r) {
                alert(r.message);
                location.reload();
            }
        })
    })
    let modify_data_seq = 0;

    $(".modify_btn").click(function(){
        modify_data_seq = $(this).attr("data-seq");
        $(".popup_wrap").addClass("open");
        $("#add_pro").css("display", "none");
        $("#modify_pro").css("display", "inline-block");
        $(".popup .top_area h2").html("상품 수정");
        $(".popup .top_area p").html("수정할 내용을 입력해주세요");

        $.ajax({
            type:"get",
            url:"/product/get?seq="+$(this).attr("data-seq"),
            success:function(r) {
                console.log(r);
                $("#product_name").val(r.data.pi_name);
                $("#product_cate").val(r.data.pi_ci_seq);
                $("#product_sub").val(r.data.pi_sub);
                $("#product_price").val(r.data.pi_price);
                $("#product_cnt").val(r.data.pi_cnt);
                $("#product_status").val(r.data.pi_status).prop("selected", true);
            }
        })
    })

    $("#modify_pro").click(function(){
        // alert(modify_data_seq)
        if(confirm("수정하시겠습니까?") == false) return;

        let product_name = $("#product_name").val();
        let product_cate = $("#product_cate").val();
        let product_sub = $("#product_sub").val();
        let product_price = $("#product_price").val();
        let product_cnt = $("#product_cnt").val();
        let product_status = $("#product_status option:selected").val();

        let data = {
            pi_seq:modify_data_seq,
            pi_name:product_name,
            pi_ci_seq:product_cate,
            pi_sub:product_sub,
            pi_price:product_price,
            pi_cnt:product_cnt,
            pi_status:product_status
        }
        $.ajax({
            type:"patch",
            url:"/product/update",
            data:JSON.stringify(data),
            contentType:"application/json",
            success:function(r) {
                alert(r.message);
                location.reload();
            }
        })
    })

    $("#cancel_pro").click(function(){
        if(confirm("취소하시겠습니까?\n(입력된 내용은 저장되지 않습니다.)") == false) return;

        $("#product_name").val("");
        $("#product_sub").val("");
        $("#product_price").val("");
        $("#product_cnt").val("");
        $("#product_status").val("1").prop("selected", true);
        $("#product_cate").val("1").prop("selected", true);

        $(".popup_wrap").css("display", "");
    })
})