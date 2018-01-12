$(function () {
    $(".submit").click(function () {
        var name = $("#value_1").val().trim();
        var password = $("#value_2").val().trim();
        $.post('../backend/doLogin',{name:name,password:password},function (rs) {
            if(rs.success){
                alert("success");
            }else {
                alert("failed");
            }
        },'json')
    })
});