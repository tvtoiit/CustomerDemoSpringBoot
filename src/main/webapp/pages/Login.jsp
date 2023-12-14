<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login - Training</title>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
</head>
<body>
	<div class="content">
		<div class="content-text">Login</div>
		<div class="content-login">
				<div class="content-login__header">
					<h3>LOGIN</h3>
					<div id="lblErrorMessage">
						 
					</div>
				</div>
				<div class="content-login__container">
					<div class="form-group">
						<label for="fullname" class="form-label form-label__userID">User
							Id: </label>
						<input name="userId" id="txtUserID" value="" maxlength="8" class="form-control" />
					</div>
					<div class="form-group__password">
						<label for="password" class="form-label">Password: </label>
						<input name="passWord" id="txtPassword"  value="" maxlength="8" class="form-control" />
					</div>
					<div class="form-group__btn">
						<button type="button" id="btnLogin" class="form-submit">Login</button>
						<button type="button" id="btnClear" class="form-submit">Clear</button>
					</div>
				</div>
		</div>
	</div>
	<script>
	$(document).ready(function() {
	    $("#btnLogin").click(function() {
	        var userId = $("#txtUserID").val();
	        var password = $("#txtPassword").val();

	        // Chuyển đối tượng JavaScript thành chuỗi JSON
	        var jsonData = JSON.stringify({
	            userId: userId,
	            passWord: password
	        });

	        // Gửi dữ liệu bằng AJAX
	        $.ajax({
	            type: "POST",
	            url: "./login",
	            contentType: "application/json", // Đặt kiểu nội dung là JSON
	            data: jsonData,
	            success: function(response) {
	                if (response === "success") {
	                    window.location.href = "./Search";
	                } 
	            },
	            error: function(error) {
	                var errorMessage = "";
	                if (error != null && error.responseJSON && error.responseJSON.errors) {
	                    errorMessage = error.responseJSON.errors[0].defaultMessage;
	                } else {
	                    errorMessage = error.responseText;
	                }
	                $("#lblErrorMessage").text(errorMessage);
	            }
	        });
	    });
	});
    </script>
</body>
</html>