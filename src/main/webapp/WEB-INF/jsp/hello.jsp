<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>test</title>
    <%--http://localhost:8080/springmvc/css/hello.css--%>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/hello.css">
</head>
<body>
    <%--去修改的路上--%>
    <a href="toAddUser">新增用户</a>
    <form action="addUser" method="post">
        <input type="text" name="username"/><br>
        <input type="text" name="address.city"/><br>
        <input type="submit" value="click"/><br>
    </form>

    <a href="javascript:void(0)" onclick="deleteById()">删除</a>
    <form action="${pageContext.request.contextPath}/user/1" method="post" id="deleteForm">
        <input type="hidden" name="_method" value="DELETE"/>
    </form>

    <script>
        function deleteById() {
            //DOM对象
            var form = document.getElementById("deleteForm");
            //submit();DOM对象的方法
            form.submit();
        }
    </script>
</body>
</html>
