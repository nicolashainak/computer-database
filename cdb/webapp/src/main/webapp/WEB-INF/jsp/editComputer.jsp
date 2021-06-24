<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<title><fmt:message key="label.editComputer"/></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="./resources/css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="./resources/css/font-awesome.css" rel="stylesheet" media="screen">
<link href="./resources/css/main.css" rel="stylesheet" media="screen">
</head>
<body>
    <header class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <a class="navbar-brand" href="../webapp/Dashboard"> <fmt:message key="label.applicationName"/> </a>
        </div>
    </header>
    <section id="main">
        <div class="container">
            <div class="row">
                <div class="col-xs-8 col-xs-offset-2 box">
                    <div class="label label-default pull-right">
                        id: ${id}
                    </div>
                    <h1><fmt:message key="label.editComputer"/></h1>

                    <form action="EditComputer" method="POST">
                        <input type="hidden" name="id" value="${id}" id="id"/>
                        <fieldset>
                            <div class="form-group">
                                <label for="computerName"><fmt:message key="label.computerName"/></label>
                                <input type="text" name="name" class="form-control" id="computerName" placeholder="Computer name" value="${computer.name}">
                            </div>
                            <div class="form-group">
                                <label for="introduced"><fmt:message key="label.computerIntroduced"/></label>
                                <input type="date" name="introduced"class="form-control" id="introduced" placeholder="Introduced date" value="${computer.introduced}">
                            </div>
                            <div class="form-group">
                                <label for="discontinued"><fmt:message key="label.computerDiscontinued"/></label>
                                <input type="date" name="discontinued"class="form-control" id="discontinued" placeholder="Discontinued date" value="${computer.discontinued}">
                            </div>
                            <div class="form-group">
                                <label for="companyId"><fmt:message key="label.company"/></label>
                                <select class="form-control" name="company" id="companyId" >
                                    <option value="0">--</option>
                                    <c:forEach items="${listCompany}" var="company" >
                                    	
                                     	<option value="${company.id}"
                                     	<c:if test="${company.id == computer.company_id.id}">
                                     	selected
                                     	</c:if>>${company.name}</option>
                                     	
                                     </c:forEach>
                                </select>
                            </div>            
                        </fieldset>
                        <div class="actions pull-right">
                            <input type="submit" value="<fmt:message key="label.edit"/>" class="btn btn-primary">
                            <fmt:message key="label.or"/>
                            <a href="../webapp/Dashboard" class="btn btn-default"><fmt:message key="label.cancel"/></a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>
</body>
</html>