<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>上传</title>
</head>
<body>
<%--设计上传表单的时候，注意点1 enctype 注意点2 method="post" 注意点3 name="file" --%>
<form action="upload" method="post" enctype="multipart/form-data">
    <%--name叫控件名，action  file--%>
    <input type="file" name="file"/><br>
    <input type="submit" value="upload"/>
</form>
<a href="download?filename=Snip20181206_1.png">download</a>
</body>
</html>
