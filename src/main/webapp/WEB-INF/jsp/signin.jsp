<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="page" value="/jsp/login.jsp" scope="request"/>
<fmt:setLocale value="${not empty sessionScope.locale ? sessionScope.locale : pageContext.request.locale}"
               scope="session"/>
<fmt:setBundle basename="messages"/>
<html>
<head>
    <title><fmt:message key="title.signin"/></title>
</head>
<body>
<div>

    <!-- Login form -->
    <form action="<c:url value="/controller"/>" method="post">
        <input type="hidden" name="command" value="signin"/>
        <label for="signinField"><fmt:message key="label.signin"/></label>
        <input type="text"
               name="signin"
               value=""
               id="signinField" pattern="^[(\w)-]{4,20}" required=""
               placeholder="<fmt:message key = "placeholder.signin"/>"/>

        <label for="passwordField"><fmt:message key="label.password"/></label>
        <input type="password"
               name="password"
               id="passwordField"
               pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+={};:><.,/?`~±§-])(?=[^\r\n\t\f\v]+$).{8,20}$"
               required=""
               placeholder="<fmt:message key = "placeholder.password"/>"/>
        <input type="submit" value="<fmt:message key="button.login"/>"/>

        <div>${sessionScope.errorLoginPassword}</div>
    </form>
</div>

</body>
</html>