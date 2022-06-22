<%@page import="webapp.utility.ServletUtility"%>
<body>
<%@ include file="header.jsp"%>
<h3 style="color: green;"><%=ServletUtility.getSuccessMessage(request)%></h3>
<h1>Welcome User</h1>
<img src="../images/image4.jpg" alt="Welcome" style="max-width: 640px; max-height: 426px;">
<%@ include file="footer.jsp"%>
</body>
</html>