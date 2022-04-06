<%--
  Created by IntelliJ IDEA.
  User: 19508
  Date: 2022/2/10
  Time: 14:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
        <form method="post" action="/add">
            <table border="1" width="300px">
                    <tr>
                        <td colspan="2"><h2>添加停车记录</h2></td>
                    </tr>
                    <tr>
                        <td>车牌号(*)</td>
                        <td>
                            <input type="text" name="car_num" id="car_num"/>
                        </td>
                    </tr>
                    <tr>
                        <td>车型(*)</td>
                        <td>
                            <input type="text" name="brand" id="brand"/>
                        </td>
                    </tr>
                    <tr>
                        <td>分区编号(*)</td>
                        <td>
                            <select name="region_id" id="region_id"></select>
                        </td>
                    </tr>
                    <tr>
                        <td>停车时间(*)</td>
                        <td>
                            <input type="text" name="add_time" id="add_time"/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <input type="submit" value="添加"/>
                        </td>
                    </tr>
            </table>
        </form>
</body>
</html>
<script src="${pageContext.request.contextPath}/statics/js/jquery-1.12.4.js"></script>
<script>
    $("form").submit(function () {
        var num=$("#car_num").val();
        var brand=$("#brand").val();
        var region_id=$("#region_id").val();
        var add_time=$("#add_time").val();
        if (num ==""||brand==""||region_id==-1||add_time==""){
            alert("请填写完整的停车记录信息!");
            return false;
        }
        var DATE_FORMAT = /^\d{4}-\d{2}-\d{2} \d{2}:\d{2}$/;
        if (DATE_FORMAT.test(add_time)){
            return true;
        } else{
            alert("日期格式不正确!");
            return false;
        }

        return true;
    });
    $.ajax({
        url:"/xiala",//请求的url
        success:function(data) {//data：返回数据（json对象）
            if (data != null) {
                $("#region_id").html("");
                var options = "<option value=\"-1\">请选择</option>";
                for (var i = 0; i < data.length; i++) {
                    options += "<option value=\"" + data[i].id + "\">" + data[i].name + "</option>";
                }
                $("#region_id").html(options);
            }
        }
    })
</script>
