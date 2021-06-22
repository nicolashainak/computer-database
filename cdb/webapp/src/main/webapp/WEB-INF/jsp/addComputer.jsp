
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
   <!DOCTYPE html>
<html>
<head>
<title><fmt:message key="label.addComputer"/></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="./resources/css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="./resources/css/font-awesome.css" rel="stylesheet" media="screen">
<link href="./resources/css/main.css" rel="stylesheet" media="screen">
</head>
<body>
    <header class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <a class="navbar-brand" href="../cdb/Dashboard"> <fmt:message key="label.applicationName"/> </a>
        </div>
    </header>

    <section id="main">
        <div class="container">
            <div class="row">
                <div class="col-xs-8 col-xs-offset-2 box">
                    <h1><fmt:message key="label.addComputer"/></h1>
                    <form href="../cdb/Dashboard" action="AddComputer" method="POST" name="formAddComputer">
                        <fieldset>
                            <div class="form-group">
                                <label for="computerName"><fmt:message key="label.computerName"/></label>
                                <input type="text" name="name" class="form-control" id="computerName" placeholder="Computer name"required="required">
                            </div>
                            <div class="form-group">
                                <label for="introduced"><fmt:message key="label.computerIntroduced"/></label>
                                <input type="date" name="introduced" class="form-control" id="introduced" placeholder="Introduced date">
                            </div>
                            <div class="form-group">
                                <label for="discontinued"><fmt:message key="label.computerDiscontinued"/></label>
                                <input type="date" name = "discontinued" class="form-control" id="discontinued" placeholder="Discontinued date">
                            </div>
                            <div class="form-group">
                                <label for="companyId"><fmt:message key="label.company"/></label>
                                <select class="form-control" name ="company" id="companyId" >
                                    <option value="0">--</option>
                                     <c:forEach items="${listCompany}" var="company" >
                                     	<option value="${company.id}">${company.name}</option>
                                     </c:forEach>
                                </select>
                            </div>                  
                        </fieldset>
                        <div class="actions pull-right">
                            <input  type="submit" value="<fmt:message key="label.add"/>" class="btn btn-primary">
                            <fmt:message key="label.or"/>
                            <a href="../cdb/Dashboard" class="btn btn-default"><fmt:message key="label.cancel"/></a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>
    <script src="./resources/js/jquery.min.js"></script>
	<script src="./resources/js/bootstrap.min.js"></script>
    <script src="./resources/js/formValidation.js"></script>
</body>
</html>