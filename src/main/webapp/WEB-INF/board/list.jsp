<%--
  Created by IntelliJ IDEA.
  User: gksql
  Date: 2021-08-18
  Time: 오후 7:20
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<a href="/board/register.do">등록</a>

<script>

    let num = '${param.bno}'//url에있는 bno값을 가져온다(문자열 처리하기위해 ''사용)

    if (num) { /*값이 있는지 없는지 확인*/
        alert(num)//확인을 누르기전까지 모두 멈춤(위험한 코드) 뒤로가기해도 경고창 뜸
        window.history.replaceState(null,'',"/board/list");//뒤로가도 앞으로가도 url값 처리
    }

</script>
    <h1>List Page</h1>


    <h4>${pageMaker}</h4>
<form action="/board/list.do" method="get">
    <input type="hidden" name="page" value="1"><%--hidden은 사용자한테 안보이게 숨겨놓는것, 그리고 values값은 어디로 가든 기본 페이지 값을 1로정함--%>
    <select name="size">
        <option value="10" ${pageMaker.size == 10? "selected":""}>10</option><%--옵션으로 페이지 사이즈를 정해놓고 다른 페이지로 이동해도 고정하려고 사용함--%>
        <option value="20" ${pageMaker.size == 20? "selected":""}>20</option>
        <option value="50" ${pageMaker.size == 50? "selected":""}>50</option>
        <option value="100" ${pageMaker.size == 100? "selected":""}>100</option>
    </select>
    <button type="submit">적용</button>
</form>
<ul>
    <c:forEach items="${dtoList}" var="dto">
        <li>
            <div>
                <div>${dto.bno}</div>
                <div><a href="/board/read.do?bno=${dto.bno}&page${pageMaker.page}&size${pageMaker.size}">${dto.title}</a></div>
                <div>${dto.viewcount}</div>
            </div>
        </li>
    </c:forEach>
    <hr/>

    <ul class="pageList">

        <c:if test="${pageMaker.prev}">//ifd에는 test를 쓰는데 boolean타입이여서 사용
            <li><a href="/board/list.do?page=${pageMaker.start -1}&size=${pageMaker.size}">PREV</a></li>
        </c:if>

        <c:forEach begin="${pageMaker.start}" end="${pageMaker.end}" var="page">
            <li><a href="/board/list.do?page=${page}">${page}</a></li>
        </c:forEach>

        <c:if test="${pageMaker.next}">
            <li><a href="/board/list.do?page=${pageMaker.end +1}&size=${pageMaker.size}">NEXT</a></li>
        </c:if>
    </ul>
</ul>
</body>
</html>
