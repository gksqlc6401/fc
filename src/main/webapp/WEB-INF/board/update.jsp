<%--
  Created by IntelliJ IDEA.
  User: gksql
  Date: 2021-08-19
  Time: 오후 11:22
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<div>${boardDTO}</div>

<form action="/board/update.do" method="post">
    <input type="hidden" name="bno" value=${bno}>
    <input type="text" name="title" value="제목">
    <input type="text" name="content" value="내용">
    <button type="submit">등록</button>
</form>
</body>
</html>
