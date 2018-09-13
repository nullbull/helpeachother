$("#login_btn").click(function () {
    var userName = $.trim($("#userName").val());
    var passWord = $.trim($("#passWord").val());
    var rememeberMe = $.trim($("#rememeberMe").val());
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
        "rememberMe" : rememeberMe
    };
    $.ajax({
        type : "POST",
        url : "/login",
        data : data,
        success:function (r) {
            if (r.code == 1) {
                $("#mess").innerText("登陆成功");
                window.location.href = "/test";
            }else {
                $("#mess").innerText("用户名或密码不正确");
            }
        }
    })
})