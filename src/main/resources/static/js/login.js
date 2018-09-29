$("#login_btn").click(function () {
    var userName = $.trim($("#userName").val());
    var passWord = $.trim($("#passWord").val());
    var rememeberMe = $("#rememeberMe").is(':checked');
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


        success : function (rd) {
            if (rd.code == 1) {
                if(rd.msg == 1)
                    window.location.href = "/user";//用户
                else
                    window.location.href = "/userB";//跑腿
            }else {
                //$("#mess").innerText("用户名或密码不正确");
                alert("用户名或密码不正确");
            }
        }
    });
})