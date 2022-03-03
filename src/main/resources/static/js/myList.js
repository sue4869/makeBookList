//삭제 버튼
$(".deleteButton").on("click",function (){
    var deleteButtonId = $(this).attr("id") // 클릭한 클래스의 id 속성을 가져오게 함
    var bookIdFordelete = deleteButtonId.substring(12)
    $.ajax({
        type : "Post",
        url : "/deleteFromMylist",
        async : false,
        data : {
            "bookId" : bookIdFordelete,
            deleteComplete : "deleteComplete"
        },
        success: function (data) {
            console.log(data.deleteComplete);
            if(data.deleteComplete == true){
                $('#dialog_alarmDelete').dialog({
                    modal: true,
                    buttons: {
                        "확인": function() {
                            $(this).dialog('close');
                            location.href="/myList";
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