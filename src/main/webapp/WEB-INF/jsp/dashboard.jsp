<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
<!-- Bootstrap -->
<link href=" css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href=" css/font-awesome.css" rel="stylesheet" media="screen">
<link href=" css/main.css" rel="stylesheet" media="screen">
</head>
<body>
    <header class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <a class="navbar-brand" href="../cdb/Dashboard"> Application - Computer Database </a>
        </div>
    </header>

    <section id="main">
        <div class="container">
            <h1 id="homeTitle">
              ${page.nbComputerRequest} Computers found
            </h1>
            <div id="actions" class="form-horizontal">
                <div class="pull-left">
                    <form id="searchForm" action="#" method="GET" class="form-inline">

                        <input type="search" id="searchbox" name="search" class="form-control" placeholder="Search name" />
                        <input type="submit" id="searchsubmit" value="Filter by name"
                        class="btn btn-primary" />
                    </form>
                </div>
                <div class="pull-right">
                    <a class="btn btn-success" id="addComputer" href="../cdb/AddComputer">Add Computer</a> 
                    <a class="btn btn-default" id="editComputer" href="#" onclick="$.fn.toggleEditMode();">Edit</a>
                </div>
            </div>
        </div>

        <form id="deleteForm" action="#" method="POST">
            <input type="hidden" name="selection" value="">
        </form>

        <div class="container" style="margin-top: 10px;">
            <table class="table table-striped table-bordered">
                <thead>
                    <tr>
                        <!-- Variable declarations for passing labels as parameters -->
                        <!-- Table header for Computer Name -->

                        <th class="editMode" style="width: 60px; height: 22px;">
                            <input type="checkbox" id="selectall" /> 
                            <span style="vertical-align: top;">
                                 -  <a href="#" id="deleteSelected" onclick="$.fn.deleteSelected();">
                                        <i class="fa fa-trash-o fa-lg"></i>
                                    </a>
                            </span>
                        </th>
                        <th>
                            <a href="?order=computer.name">Computer name</a>
                        </th>
                        <th>
                            <a href="?order=computer.introduced">Introduced date</a>
                        </th>
                        <!-- Table header for Discontinued Date -->
                        <th>
                            <a href="?order=computer.discontinued">Discontinued date</a>
                        </th>
                        <!-- Table header for Company -->
                        <th>
                            <a href="?order=company.name">Company</a>
                        </th>

                    </tr>
                </thead>
                <!-- Browse attribute computers -->
              
                <tbody id="results">
                   <c:forEach items="${computerList}" var="computer" >
   
              		<tr>
                        <td class="editMode">
                            <input type="checkbox" name="cb" class="cb" value="${computer.id.get()}">
                        </td>
                        <td>
                            <a href="../cdb/EditComputer?id=${computer.id.get()}" onclick="">${computer.name}</a>
                        </td>
                        <td>${computer.introduced}</td>
                        <td>${computer.discontinued}</td>
                        <td>${computer.company_id.name}</td>

                    </tr>
  				</c:forEach>                 
                    
                </tbody>
            </table>
        </div>
    </section>

    <footer class="navbar-fixed-bottom">
        <div class="container text-center">
            <ul class="pagination">
                 <c:if test="${page.numPage != 1}">
                <li>
                    <a href="?num=${page.numPage-1}" aria-label="Previous">
                      <span aria-hidden="true">&laquo;</span>
                  </a>
              </li>
               </c:if>
              <c:if test="${tableauAffichage[0]!= -1}">
              <li><a href="?num=${ tableauAffichage[0]}">${tableauAffichage[0]}</a></li>
              </c:if>
              <c:if test="${tableauAffichage[1]!= -1}">
             <li><a href="?num=${ tableauAffichage[1]}">${tableauAffichage[1]}</a></li>
              </c:if>
              <c:if test="${tableauAffichage[2]!= -1}">
              <li><a href="?num=${ tableauAffichage[2]}">${tableauAffichage[2]}</a></li>
              </c:if>
              <c:if test="${tableauAffichage[3]!= -1}">
              <li><a href="?num=${ tableauAffichage[3]}">${tableauAffichage[3]}</a></li>
              </c:if>
              <c:if test="${tableauAffichage[4]!= -1}">
              <li><a href="?num=${ tableauAffichage[4]}">${tableauAffichage[4]}</a></li>
              </c:if>
           
              <c:if test="${page.numPage != page.getNbPageMax()}">
              <li>
                <a href="?num=${page.numPage+1}" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
            </c:if>
        </ul>

        <div class="btn-group btn-group-sm pull-right" role="group" >
        <form action="${pageContext.request.contextPath}/Dashboard" >
            <button type="submit" name="button" value = "button1" class="btn btn-default">10</button>
            <button type="submit" name="button" value = "button2" class="btn btn-default">50</button>
            <button type="submit" name="button" value = "button3" class="btn btn-default">100</button>
        </form>
        </div>

    </footer>
<script src="./js/jquery.min.js"></script>
<script src="./js/bootstrap.min.js"></script>
<script src="./js/dashboard.js"></script>

</body>
</html>