$("#s1").change(function () {
    var val = $("#s1 option:selected").val();
    if (val == 2) {
        $("#studentId").hidden=false;
        $("#picture").hidden=false;
    }
}),
$("#loction").change(function () {
    var val = $("#loction option:selected").val();
    var child = $("#locationId");
    child.empty();
    if (val != 0) {
        $.ajax({
            type : "GET",
            url : "location/" + val,
            success : function (rd) {
                console.log(rd);
                for(var i = 0; i < rd.data.length; i++) {
                    child.append("<option value=" + rd.data[i].id + ">" + rd.data[i].name +"</option>");
                }
                if (rd == 1) {
                    alert(rd.data);
                }
            }
        })
    }
})
$("#register_btn").click(function () {
    var userName = $.trim($("#userName").val());
    var passWord = $.trim($("#passWord").val());
    var email = $.trim($("#email").val());
    var phone = $.trim($("#phone").val());
    var location_id = $("#locationId option:selected").val();
    var alipayId = $.trim($("#alipayId").val());
    var wechatId = $.trim($("#wechatId").val());
    var qqNumber = $.trim($("#qqNumber").val());
    if (userName == "") {
        alert("用户名不能为空");
        $("#mess").text =  "用户名不能为空";
        return;
    }
    else if (passWord == "") {
        $("#mess").innerText = "密码不能为空";
        return;
    }


    $.post("/doRegister", $("#loginForm").serialize(), function (rd) {
        if (rd.code == 0) {
            alert("成功！");
            location.href = "/register_ok";
            return false;
        }else {
            alert(rd.msg);
            $("#mess").innerText("用户名或密码不正确");
        }
    } )

});