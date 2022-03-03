
//책 목록에서 책 삭제
$(".deleteButton").on("click",function (){
    var deleteButtonId = $(this).attr("id") // 클릭한 클래스의 id 속성을 가져오게 함
    var bookIdFordelete = deleteButtonId.substring(12)
    $.ajax({
        type : "Post",
        url : "/deleteFromlist",
        async : false,
        data : {
            "bookId" : bookIdFordelete,
            deleteComplete : "deleteComplete"
        },
        success: function (data) {
            if(data.deleteComplete == true){
                $('#dialog_alarmDelete').dialog({
                    modal: true,
                    buttons: {
                        "확인": function() {
                            $(this).dialog('close');
                            location.href="/main";
                        }
                    }
                });
            }
        },
        error : function (){
            console.log("실패");
        }
    });
});

// 나의 책 목록에 추가
$(".addButton").on("click",function (){
    var addButtonId = $(this).attr("id")
    var bookIdForAdd = addButtonId.substring(9)
    $.ajax({
        type : "Post",
        url : "/addmylist",
        async : false,
        data : {
            "bookId" : bookIdForAdd,
            alreadyIn : "alreadyIn"
        },
        success: function (data) {
            if(data.alreadyIn == true){
                $('#dialog_alreadyGetIn').dialog({
                    modal: true,
                    buttons: {
                        "확인": function() {
                            $(this).dialog('close');
                        }
                    }
                });
            }else{
                $('#dialog_GetInComplete').dialog({
                    modal: true,
                    buttons: {
                        "확인": function() {
                            $(this).dialog('close');
                        }
                    }
                });
            }
        },
        error : function (){
            console.log("실패");
        }
    });
});
