$(function () {
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
                for(var i = 0; i < rd.data.length; i++) {
                    list.append("<tr><th>" + rd.data[i].nickName  + "</th>");
                    list.append("<th>" + rd.data[i].expressName + "</th>");
                    list.append("<th>" + rd.data[i].locationName + "</th>");
                    list.append("<th>" + rd.data[i].price + "</th>");
                    list.append("<th>" + rd.data[i].createdAt + "</th>");
                    list.append("</tr>");
                }
            }
        }

    })
})