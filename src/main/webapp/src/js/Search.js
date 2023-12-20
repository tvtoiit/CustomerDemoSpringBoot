function rediEdit() {
	 window.location.href = "./edit";
}

function rediImport() {
	 window.location.href = "./import";
}

function searchCustomers() {
    // Get input values
    var name = $("#txtCustomerName").val();
    var sex = $("#cboSex").val();
    var birthdayFrom = $("#txtBirthdayForm").val();
    console.log(typeof(birthdayFrom));
    
    var birthdayTo = $("#txtBirthdayTo").val();
    
    $.ajax({
        type: "POST",
        url: "./Search",
        data: {
            name: name,
            sex: sex,
            birthdayFrom: birthdayFrom,
            birthdayTo: birthdayTo
        },
        success: function(response) {
        	document.open();
        	document.write(response);
        	document.close();
        },
        error: function(error) {
            console.error("Error searching: " + error.responseText);
        }
    });
}

function lastPagination() {
	var name = $("#txtCustomerName").val();
    var sex = $("#cboSex").val();
    var birthdayFrom = $("#txtBirthdayForm").val();
    var birthdayTo = $("#txtBirthdayTo").val();
    var pagePagination = $("#pagePagination").val();
    
    $.ajax({
    	type: "POST",
    	url: "./Search",
    	data: {
    		name: name,
            sex: sex,
            birthdayFrom: birthdayFrom,
            birthdayTo: birthdayTo,
            page: pagePagination,
            sMode: "last",
            statusBtn: true
    	},
    	success: function(response) {
    		console.log(response);
        	updateTable(response);
        },
        error: function(error) {
            console.error("Error searching: " + error.responseText);
        }
    })
    
}

function nextPagination() {
	var name = $("#txtCustomerName").val();
    var sex = $("#cboSex").val();
    var birthdayFrom = $("#txtBirthdayForm").val();
    var birthdayTo = $("#txtBirthdayTo").val();
    var pagePagination = $("#pagePagination").val();
}

function deleteSelectedCustomers() {
	var selectedValues = [];
	
	$("input[type=checkbox]:checked").each(function() {
        selectedValues.push($(this).val());
    });
	
    if (selectedValues.length === 0) {
        alert("Chưa chọn customer");
        return;
    }
	
    $.ajax({
        type: "POST",
        url: "./Delete",
        data: JSON.stringify({ selectedValues: selectedValues }),
        contentType: "application/json",
        success: function(response) {
            window.location.href = "./Search";
        },
        error: function(error) {
            console.error("Lỗi xóa: " + error.responseText);
        }
    });
}