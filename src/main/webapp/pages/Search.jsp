<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Search Training</title>
<style type="text/css">
	<%@include file="../WEB-INF/css/T002.css" %>
</style>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>

</head>
<body>
	<div class="search-container">
		<div class="search-container__dan">
				<div class="search-container__text">
					Login > Search Customer
				</div>
			<div class="search-container__context">
				<div class="search-container__logo">
					<div>Welcome:</div>
				</div>
				<a href="./T001.do" class="search-container__logout">
					Log Out
				</a>
			</div>
			<div class="search-container__line"></div>
			<form id="searchForm">
				<div class="search-container__handalSearch">
					<div class="search-container__handalSearch--margin handalSearch-customerName">
						<div class="handalSearch-customercommon handalSearch-customerName__text">Customer Name</div>
						<input id="txtCustomerName" class="input_Customer--common" name="txtCustomerName" maxLength = "50" value="${searchResult.name }"/>
					</div>
					<div class="search-container__handalSearch--margin handalSearch-customerSex">
						<div class="handalSearch-customercommon handalSearch-customerSex__text">Sex</div>
						<select name="sex" class="input_Customer--select" id="cboSex">
						    <option value="">blank</option>
						    <option value="0" ${searchResult.sex == "0" ? 'selected' : ''}>Male</option>
							<option value="1" ${searchResult.sex == "1" ? 'selected' : ''}>Female</option>
						</select>
					</div>
					<div class="search-container__handalSearch--margin handalSearch-BirthdayFrom">
						<div class="handalSearch-customercommon handalSearch-BirthdayFrom__text">Birthday</div>
						<input id="txtBirthdayForm" class ="input_Customer--common txtCustomerValidateFROM" name ="txtBirthdayFromName" maxLength ="10" value="${searchResult.birthDayFrom}"/>
						<label for="html" class="handalSearch-customercommon handalSearch-BirthdayFrom__ngangcach">～</label>
						<input id="txtBirthdayTo" class="input_Customer--common txtCustomerValidateTO" name ="txtBirthdayToName" maxLength ="10" value="${searchResult.birthDayTo}"/>		
					</div>
					<div class="handalSearch-btnSearch">
						<button type="button" onclick="searchCustomers()" id="btnSearch">Search</button>
					</div>
				</div>
			</form>
			<div class="search-container__btnContext--chuyenhuong">
			<input type="hidden" id="pagePagination" value=""/>	
		    <div class="search-container__btnContext--start">
		    	<button name="pageAction" value="first">&lt;&lt;</button>
	     		<button name="pageAction" value="previous">&lt;</button>
		        <label for="html" class="search-container__btnContext--textstart">Previous</label>
		    </div>
		    <div class="search-container__btnContext--end">
		        <label for="html" class="search-container__btnContext--textend">Next</label>

		     	<button type="button" name="pageAction" value="next">&gt;</button>  
		        <button type="button" onclick="lastPagination()">&gt;&gt;</button>
		    </div>
		</div>
		<table class="search-container__table" id="sortableTable">
	       <thead>
	        <tr id="header">
       	 		<th><input type="checkbox" id="checkAll" name="checkboxAll" value="" onclick="toggleAllCheckboxes()"></th>
       	 		<th>Customer ID</th>
       	 		<th>Customer Name</th>
       	 		<th>Sex</th>
      	 		<th>Birthday</th>
      	 		<th>Address</th>
	        </tr>
	        </thead>
	        <tbody>
	       	<c:forEach var="customer" items="${searchResult.dataSearch}">
                <tr>
                 	<td><input type="checkbox" id="selecValueCheckBox" value="${customer.customerId}"></td>
                    <td><a href="./edit/${customer.customerId}">${customer.customerId}</a></td>
                    <td>${customer.customerName}</td>
                    <td>${customer.sex == 0 ? 'Male' : 'Female'}</td>
                    <td>${customer.birthDay}</td>
                    <td>${customer.address}</td>
                </tr>
            </c:forEach>
			</tbody>
    	</table>
		<div class="search-container__btnnav">
			<button  class="btn-import__class search-container__nav-btnAdd" onclick="rediEdit()">Add New</button>
			<button type="button" onclick="deleteSelectedCustomers()" class ="search-container__nav-btnAdd">Delete</button>
			<a href="./Import.do" class="btn-import__class">Import</a>
		</div>
	</div>
</div>
<script src="./src/js/Search.js"></script>
</body>
</html>