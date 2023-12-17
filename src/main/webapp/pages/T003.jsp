<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit - Training</title>
<style type="text/css">
	<%@include file="/WEB-INF/css/T003.css"%>
</style>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
</head>
<body>
	<div id="main">
		<div class = "edit-container">
			<div class = "edit-container__header">
				<span class = "edit-container__headerText edit-container__headerText">Login > Search Customer > Edit Customer Info</span>
				<div class = "edit-container__wellcom">
				<div class = "edit-container__wellcomLeft edit-container__headerText">
				Welcome: ABC
				
					<div class = "edit-container__wellcomname">
						
					</div>
				
				</div>
					<div class="edit-container__wellcomRight edit-container__headerText">
						<a href ="#">Log Out</a>
					</div>
				</div>
				<div class="line-br">Edit</div>
		</div>
		<div class="main-edit__cotainer">
			<div class="edit-container__content">
				<div class="edit-container__content--error">Thông báo lỗi</div>
				<div class="edit-container__content-Id edit-container__end">
					<label class="edit-container__content-IdText edit-context__minWith">Customer Id</label>
					<label for=dtoCustomerId class="edit-container__content-IdLable edit-container__headerText" id ="lblCustomerID">${editResponse.customerId}</label>
				</div>
				<div class="edit-container__content-Name edit-context__minWith">
					<label class="edit-container__contentName-input edit-container__headerText">Customer Name</label>
					<input id="txtCustomerName" type="text" class="form-with__input" name="customerName" maxlength="50" value="${editResponse.customerName}"/>
				</div>
					<div class="edit-container__content-Sex edit-context__minWith">
					<label class="edit-container__content-Sexlable edit-container__headerText">Sex</label>
					   <select id="cboSex" name="sex">
					        <option value="" class="cbooption"></option>
					        <option class="cbooption" value="0" ${editResponse.sex == 0 ? 'selected' : ''}>Male</option>
						  	<option class="cbooption" value="1" ${editResponse.sex == 1 ? 'selected' : '' }>Female</option>
						</select>
					</div>
				<div class="edit-container__content-Birthday edit-context__minWith">
					<label class="edit-container__contentBirthday-input">Birthday</label>
					<input id="txtCustomerBirthday"  class="form-with__input" value="${editResponse.birthDay }" name ="birthDay" maxlength="10"/>			
				</div>
				<div class="edit-container__content-Email edit-context__minWith">
					<label class="edit-container__contentEmail-input edit-container__headerText">Email</label>
					<input id ="txtCustomerEmail" type="text"  class="form-with__input" name ="email" maxlength="40" value="${editResponse.email }" />
				</div>
				<div class="edit-container__content-Address edit-context__minWith">			
					<label class="edit-container__contentAddress-input edit-container__headerText">Address</label>
					<textarea id="txaAddress" name="address">${editResponse.address}</textarea>
				</div>
				<div class="edit-container__btnContent">
					<button type="button" id="btn-save" onclick="saveCustomer()" class="edit-container__btnContent-Save">Save</button>
					<button type="button"  id="btnClearEdit" onclick="clearForm()" class="edit-container__btnContent-Clear">Clear</button>
				</div>
			</div>
		</div>
		</div>
	</div>
	<script>
		function saveCustomer() {
			var customerId = +($("#lblCustomerID").text());
			var name = $("#txtCustomerName").val();
		    var sex = $("#cboSex").val();
		    var birthDay = $("#txtCustomerBirthday").val();
		    var email = $("#txtCustomerEmail").val();
		    var address = $("#txaAddress").val();
		    
		    $.ajax({
		    	type: "POST",
		    	url: window.location.origin + "/Customer/edit", 
		    	data: {
		    		customerId: customerId,
		    		customerName: name,
		            sex: sex,
		            birthDay: birthDay,
		            email: email,
		            address: address
		    	},
		    	success: function(response) {
		    		if (response === 'success') {
		    			window.location.href = "/Customer/Search";
		    		}
		    		
		        },
		        error: function(error) {
		            console.error("Error searching: " + error.responseText);
		        }
		    })
		}
	</script>
</body>
</html>