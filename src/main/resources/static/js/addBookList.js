$('#registerForm').submit(function() {
    var queryString = $("form[name=registerForm]").serialize() ;

    $.ajax({
        url: '/registerBook',
        processData: false,
        contentType: false,
        data: queryString,
        type: 'POST',
        dataType: 'json',
        success: function () {
            console.log("성공");
        },
        error(){
            console.log("실패");
        }
    });
});