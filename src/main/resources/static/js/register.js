$("#s1").change(function () {
    var val = $("#s1 option:selected").val();
    if (val == 2) {
        $("#studentId").hidden=false;
        $("#picture").hidden=false;
    }
}),

$("#register_btn").click(function () {
    var userName = $.trim($("#userName").val());
    var passWord = $.trim($("#passWord").val());
    var email = $.trim($("#email").val());
    var phone = $.trim($("#phone").val());
    var alipayId = $.trim($("#alipayId").val());
    var wechatId = $.trim($("#wechatId").val());
    var qqNumber = $.trim($("#qqNumber").val());
    if (userName === "") {
        $("#mess").innerText =  "用户名不能为空";
        return;
    }
    else if (passWord === "") {
        $("#mess").innerText = "密码不能为空";
        return;
    }
    var data = {
        "userName" : userName,
        "passWord" : passWord,
        "email" : email,
        "phone" : phone,
        "alipayId" : alipayId,
        "wechatId" : wechatId,
        "qqNumber" : qqNumber
    };
    $.ajax({
        type : "POST",
        url : "/doRegister",
        data : { "params": JSON.stringify(data) },
        success : function (rd) {
            alert(rd);
            if (rd.code == 1) {
                window.location.href = "/hello";
            }else {
                $("#mess").innerText("用户名或密码不正确");
            }
        }
    });
});