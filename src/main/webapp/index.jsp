<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="custom" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>HotelBooking</title>
</head>
<body>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="text"/>

<c:set var="text">
    <fmt:message key="welcome"/>
</c:set>

<custom:header text="${text}"/>
<div class="paper container" style="min-height: 100vh; padding-top: 220px">
    <h3 class="text-center">
        <fmt:message key="please"/>
        <form action="<c:url value="/controller"/>" method="get">
            <input type="hidden" name="command" value="to-signin">
            <input type="submit" name="submit" value="<fmt:message key="button.signin"/>  ">
        </form>

        <fmt:message key="or"/>
        <form action="<c:url value="/controller"/>" method="get">
            <input type="hidden" name="command" value="to-signup">
            <input type="submit" name="submit" value="<fmt:message key="button.signup"/> ">
        </form>
    </h3>
</div>

</body>
</html>

