$(function () {
    var val = $("#expressInfo option:selected").val();
    var child = $("#expressInfo");
    child.empty();
        $.ajax({
            type : "GET",
            url : "expressInfo",
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

})
$("#loction").change(function () {
    var val = $("#loction option:selected").val();
    var child = $("#locationChild");
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
$("#pub_btn").click(function () {
    var expressType = $("#expressInfo option:selected").val();
    var getCode = $("#getCode").val().trim();
    var locationId = $("#locationChild option:selected").val();
    var price = $("#price").val().trim();
    var message = $("#message").val().trim();
    var data = {
        "expressType" : expressType,
        "getCode" : getCode,
        "locationId" : locationId,
        "price" : price,
        "message" : message
    };
    $.ajax({
            type : "POST",
            url : "",
            data : {"params" : JSON.stringify(data)},
            success : function (rd) {
                if (rd.getCode == 1) {

                }
            }

        }
    )
})