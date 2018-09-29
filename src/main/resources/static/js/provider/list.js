$(function () {
    /*var list = $("#form");*/
    var list = $("#express-list");
    var expressType = "";
    var beginTime = "";
    var endTime = "";
    var lowPrice = "";
    var highPrice = "";
    var data = {
      "expressType" : expressType,
      "beginTime" : beginTime,
      "endTime" : endTime,
      "lowPrice" : lowPrice,
      "highPrice" : highPrice
    };
    $.ajax({
        type : "Post",
        url : "/expressList",
        data : {"params" : JSON.stringify(data)},
        success : function (rd) {
            if (rd.code == 1) {
                alert("llllllllllll");
                for(var i = 0; i < rd.data.length; i++) {
                    alert(rd.data[i].id);
                    list.append("<tr>");
                    list.append("<td>"+"vfdv"+"</td>");
                    list.append("<td>" + rd.data[i].id + "</td>");
                    list.append("<td>" + rd.data[i].nickName  + "</td>");
                    list.append("<td>" + rd.data[i].expressName + "</td>");
                    list.append("<td>" + rd.data[i].locationName + "</td>");
                    list.append("<td>" + rd.data[i].price + "</td>");
                    list.append("<td>" + rd.data[i].createdAt + "</td>");
                   /* list.append("<a href='/expressOrder/"+ rd.data[i].id + "'>抢单00</a>")*/
                    list.append("</tr>");
                }
            }
        }
    })
})