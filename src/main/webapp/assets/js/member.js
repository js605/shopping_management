$(function(){
    $(".main_menu a:nth-child(2)").addClass("active")
    $("#add_member").click(function(){
        // alert("학과 추가 팝업 열기");
        $(".popup_wrap").addClass("open");
        $("#add_mem").css("display", "inline-block");
        $("#modify_mem").css("display", "none");
        $(".popup .top_area h2").html("회원 추가");
        $(".popup .top_area p").html("회원 정보를 입력해주세요");
    })
    $("#add_mem").click(function(){
        if(confirm("회원을 등록하시겠습니까?") == false) return;
        let mem_name = $("#mem_name").val();
        let mem_birth = $("#mem_birth").val();
        let mem_phone_num = $("#mem_phone_num").val();
        let mem_id = $("#mem_id").val();
        let mem_pwd = $("#mem_pwd").val();
        let mem_status = $("#mem_status option:selected").val();

        let data = {
            mi_name:mem_name,
            mi_birth:mem_birth,
            mi_phone_num:mem_phone_num,
            mi_id:mem_id,
            mi_pwd:mem_pwd,
            mi_status:mem_status
        }

        $.ajax({
            type:"post",
            url:"/member/add",
            data:JSON.stringify(data),
            contentType:"application/json",
            success:function(r) {
                alert(r.message);
                if(r.status)
                location.reload();
            }
        })
    })
    $("#cancel_mem").click(function(){
        // alert("학과 등록 취소");
        if(confirm("취소하시겠습니까?\n(입력된 정보는 저장되지 않습니다.)") == false) return;

        $("#mem_name").val("");
        $("#mem_birth").val("");
        $("#mem_phone_num").val("");
        $("#mem_id").val("");
        $("#mem_pwd").val("");
        $("#mem_status").val("1").prop("selected", true);

        $(".popup_wrap").removeClass("open");
    })

    $(".delete_btn").click(function(){
        if(confirm("회원을 삭제하시겠습니까?\n(이 동작은 되돌릴 수 없습니다.)") == false) return;
        let seq = $(this).attr("data-seq");

        $.ajax({
            type:"delete",
            url:"/member/delete?seq="+seq,
            success:function(r) {
                alert(r.message);
                location.reload();
            }
        })
    });
    let modify_data_seq = 0;

    $(".modify_btn").click(function(){
        modify_data_seq = $(this).attr("data-seq");
        $(".popup_wrap").addClass("open");
        $("#add_mem").css("display", "none");
        $("#modify_mem").css("display", "inline-block");
        $(".popup .top_area h2").html("회원 수정");
        $(".popup .top_area p").html("수정할 회원 정보를 입력해주세요");
        $.ajax({
            type:"get",
            url:"/member/get?seq="+$(this).attr("data-seq"),
            success:function(r) {
                console.log(r);
                $("#mem_name").val(r.data.mi_name);
                $("#mem_birth").val(r.data.mi_birth);
                $("#mem_phone_num").val(r.data.mi_phone_num);
                $("#mem_id").val(r.data.mi_id);
                $("#mem_pwd").val(r.data.mi_pwd);
                $("#mem_status").val(r.data.mi_status).prop("selected", true);
            }
        })
    })
    $("#modify_mem").click(function(){
        // alert(modify_data_seq)
        if(confirm("수정하시겠습니까?") == false) return;

        let mem_name = $("#mem_name").val();
        let mem_birth = $("#mem_birth").val();
        let mem_phone_num = $("#mem_phone_num").val();
        let mem_id = $("#mem_id").val();
        let mem_pwd = $("#mem_pwd").val();
        let mem_status = $("#mem_status option:selected").val();

        let data = {
            mi_seq:modify_data_seq,
            mi_name:mem_name,
            mi_birth:mem_birth,
            mi_phone_num:mem_phone_num,
            mi_id:mem_id,
            mi_pwd:mem_pwd,
            mi_pwd:mem_status
        }
        $.ajax({
            type:"patch",
            url:"/member/update",
            data:JSON.stringify(data),
            contentType:"application/json",
            success:function(r) {
                alert(r.message);
                location.reload();
            }
        })
    })
    $("#search_btn").click(function(){
        location.href="/member?keyword="+$("#keyword").val();
    })
    $("#keyword").keydown(function(e){
        console.log(e.keyCode)
        if(e.keyCode == 13) {
            $("#search_btn").trigger("click");
        }
    })
})
