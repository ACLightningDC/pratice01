<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<body>
	<div>
		<jsp:include page="userHeader.jsp"/>
	</div>
	
	<c:if test="${showPage ne null }">
		<section>
			<div>
				<jsp:include page="${showPage}"/>
			</div>
		</section>
	</c:if>
		
	<div>
		<jsp:include page="userFooter.jsp"/>
	</div>
</body>
</html>