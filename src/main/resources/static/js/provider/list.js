$(function () {
    var list = $("#express-list");
    ajax({
        type : "GET",
        url : "/expressList",
        success : function (rd) {
            if (rd.code == 1) {
                for(var i = 0; i < rd.data.length; i++) {
                    list.append("<tr><th>" + rd.data[i].nickName  + "</th>");
                    list.append("<th>" + rd.data[i].expressName + "</th>");
                    list.append("<th>" + rd.data[i].price + "</th>");
                    list.append("<th>" + rd.data[i].createAt + "</th>");
                }
            }
        }

    })
})