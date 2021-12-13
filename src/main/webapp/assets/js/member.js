$(function(){
    $(".main_menu a:nth-child(2)").addClass("active")
    $("#add_member").click(function(){
        // alert("학과 추가 팝업 열기");
        $(".popup_wrap").addClass("open");
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
})
