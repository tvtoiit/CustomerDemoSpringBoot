<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login - Training</title>
<style type="text/css">
	<%@include file="../WEB-INF/css/T001.css" %>
</style>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
</head>
<style>
	.main-container {
    position: relative;
	}
	
	.customButton {
	    position: absolute;
	    top: 0;
	    right: 0;
	    padding: 10px 15px;
	    background-color: #4CAF50;
	    color: white;
	    cursor: pointer;
	    border: none;
	    border-radius: 5px;
	}

</style>
<body>

<form id="form-import" enctype="multipart/form-data" onsubmit="return handleImport()">
    <div class="main-container">
        <input type="text" id="importText" name="importText" readonly />
        <label for="fileInput" class="customButton">Browse</label>
        <input type="file" id="fileInput" name="file" style="display: none;" /> <!-- Đổi tên trường để phù hợp với form bean -->
    </div>
    <div class="btn-import">
        <button type="button" id="importButton" name="action" value="import">Import</button>
        <button type="button" id="cancelButton">Cancel</button>
    </div>
</form>
<script>
	document.getElementById('fileInput').addEventListener('change', handleFileSelect);
	    
	function handleFileSelect() {
	    var fileInput = document.getElementById('fileInput');
	    var importText = document.getElementById('importText');

	    // Lấy tên file từ input file
	    var fileName = fileInput.files[0].name;

	    // Hiển thị tên file trong input text
	    importText.value = fileName;
	}
	function handleImport() {
	    var fileInput = document.getElementById('fileInput');
	    var importText = document.getElementById('importText');
	
	    // Kiểm tra nếu không có file import
	    if (fileInput.files.length === 0) {
	        alert("File import is not existed!");
	        return false;
	    }
	
	    // Kiểm tra phần mở rộng của file
	    var fileName = fileInput.files[0].name;
	    var fileExtension = fileName.split('.').pop().toLowerCase();
	    if (fileExtension !== 'csv') {
	        alert("File import is invalid");
	        return false;
	    }
	
	    // Kiểm tra kích thước hoặc nội dung của file import
	    if (fileInput.files[0].size === 0) {
	        alert("File import is empty");
	        return false;
	    }
	    
	    return true;
	}
	
	
	 $(document).ready(function () {
         $("#importButton").click(function () {
             // Tạo FormData để chứa tập tin
             var formData = new FormData();
             formData.append("file", $("#fileInput")[0].files[0]);

             // Gửi AJAX request
             $.ajax({
                 url: "./upload",
                 type: "POST",
                 data: formData,
                 contentType: false,
                 processData: false,
                 success: function (response) {
                     // Xử lý phản hồi thành công
                     alert(response);
                 },
                 error: function (error) {
                     // Xử lý lỗi
                     alert("An error occurred: " + error.responseText);
                 }
             });
         });
     });
</script>


</body>
</html>