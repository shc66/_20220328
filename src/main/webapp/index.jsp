<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
        <h1>停车场车辆管理系统</h1>
        <form action="/show" method="post" id="frm">
            <input type="hidden" id="rid" value="${param.region_id}"/>
            <input type="hidden" name="pageIndex" id="pageIndex" value="1"/>
            <select id="region_id" name="region_id">
            </select>
            <input type="submit" value="查询"/>
            <input type="button" value="添加" onclick="location.href='add.jsp'"/>
        </form>
        <table border="1" width="700px">
                <tr
                >
                    <td>车牌号</td>
                    <td>型号</td>
                    <td>车库分区</td>
                    <td>停车日期</td>
                    <td>停车状态</td>
                    <td>操作</td>
                </tr>
                <c:forEach items="${page.list}" var="r">
                    <tr
                            <c:if test="${r.status==0}">style="color: red" </c:if>
                    >
                        <td>${r.car_num}</td>
                        <td>${r.brand}</td>
                        <td>${r.name}</td>
                        <td>
                            <fmt:formatDate value="${r.add_time}" pattern="yyyy-MM-dd HH:mm"/>
                            <%--<fmt:formatDate value="${releaseTime}" pattern="yyyy-MM-dd HH:mm"/>--%>
                        </td>
                        <%--<td>${r.add_time}</td>--%>
                        <td>
                            <c:if test="${r.status==0}">停车中</c:if>
                            <c:if test="${r.status==1}">已驶离</c:if>
                        </td>
                        <td>
                            <a href="javascript:void(0)" onclick="update(${r.id},this)">
                                <c:if test="${r.status==0}">驶离</c:if>
                            </a>
                            <c:if test="${r.status==1}">已驶离</c:if>
                            <a href="javascript:void(0)" onclick="del(${r.id},this)">ajax删除</a>
                            <a  href="/toupdate?id=${r.id}">修改</a>
                        </td>
                    </tr>
                </c:forEach>
        </table>
        <c:if test="${page.pageCount>0 }">
            第${page.pageIndex }页/共${page.pageCount }页
        </c:if>

        <c:if test="${page.pageIndex>1 }">
            <a href="javaScript:topage(1)">首页</a>
            <a href="javaScript:topage(${page.pageIndex-1})">上一页</a>
        </c:if>
        <c:forEach var="i" begin="1" step="1" end="${page.pageCount}">
            <a href="javaScript:topage(${i})" >${i}</a>
        </c:forEach>

        <c:if test="${page.pageIndex<page.pageCount }">
            <a href="javaScript:topage(${page.pageIndex+1})">下一页</a>
            <a href="javaScript:topage(${page.pageCount})">尾页</a>
        </c:if>
        <div id="msg"></div>
        ${msg}
</body>
</html>
<script src="${pageContext.request.contextPath}/statics/js/jquery-1.12.4.js"></script>
<script>
    //隔行换色
    $("tr:even:not(:first)").css("background","yellow");

    function topage(pageIndex){
        //给隐藏域的pageIndex赋值
        $("#pageIndex").val(pageIndex);
        //调用下拉列表的提交事件
        $("#frm").submit();
    }

    $.ajax({
        url:"/xiala",//请求的url
        success:function(data) {//data：返回数据（json对象）
            if (data != null) {
                $("#region_id").html("");
                var rid=$("#rid").val();
                var options = "<option value=\"-1\">---请选择---</option>";
                for (var i = 0; i < data.length; i++) {
                    if (data[i].id==rid){
                        options += "<option selected='selected' value=" + data[i].id + ">" + data[i].name + "</option>";
                    } else{
                        options += "<option  value=" + data[i].id + ">" + data[i].name + "</option>";
                    }
                }
                $("#region_id").html(options);
            }
        }
    })
    //ajax异步删除
    function del(id,a) {
            if (confirm("确认要删除么?")){
                $.ajax({
                    url:"/delete",
                    data:"id="+id,
                    success:function(data){
                        if (data=="y"){
                            //删除成功!
                            $("#msg").html("删除成功!");
                            //从DOM从删除此行
                            $(a).parents("tr").remove();
                        }
                    }
                });
            }
    }
    //修改停车状态
    function update(id,a) {
        if (confirm("请确认该车是否已驶离?")) {
                $.ajax({
                    url:"/update",
                    data:"id="+id,
                    success:function (data) {
                        if (data=="ok"){
                            //修改成功!
                            $(a).parent().prev().html("已驶离");
                            //字体变为黑色
                            $(a).parents("tr").css("color","black");
                            $(a).parent().html("已驶离");
                        }
                    }
                });
        }
    }
</script>