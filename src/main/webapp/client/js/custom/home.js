function sortBy(something){
	//var orderBy = 'NAME';
	$.ajax({
		url : "SortProductController",
		type : "GET",
		data : {
			orderBy : something			
		},
		success : function(result) {
			$("#list-product").html(result); // jquery
		},
		error : function(jqXhr, textStatus, errorThrown) {
			$("#list-product").html("<h1>Loi!</h1>");
			alert("Co loi xay ra");
		}
	});
	
}


$(document).ready(function(){
	
	$("#sort-by-id").click(function(){
		$("#sort-by-id").addClass("btn-selected");
		$("#sort-by-id").removeClass("btn-no-selected");
		$("#sort-by-name").removeClass("btn-selected");
		$("#sort-by-name").addClass("btn-no-selected");
		$("#sort-by-price").removeClass("btn-selected");
		$("#sort-by-price").addClass("btn-no-selected");
		sortBy('ID');
	});
	
	$("#sort-by-name").click(function(){
		$("#sort-by-name").addClass("btn-selected");
		$("#sort-by-name").removeClass("btn-no-selected");
		$("#sort-by-id").removeClass("btn-selected");
		$("#sort-by-id").addClass("btn-no-selected");
		$("#sort-by-price").removeClass("btn-selected");
		$("#sort-by-price").addClass("btn-no-selected");
		sortBy('NAME');
		
	});
	
	$("#sort-by-price").click(function(){
		$("#sort-by-price").addClass("btn-selected");
		$("#sort-by-price").removeClass("btn-no-selected");
		$("#sort-by-id").removeClass("btn-selected");
		$("#sort-by-id").addClass("btn-no-selected");
		$("#sort-by-name").removeClass("btn-selected");
		$("#sort-by-name").addClass("btn-no-selected");
		sortBy('PRICE');
		
	});
	
	
});

function searchForm(pageId) {
	var dataForm = {};
	dataForm.name = $("#txtName").val();
	dataForm.email = $("#txtEmail").val();
	dataForm.group = $("#selectGroup").val();
	dataForm.flag = "search";
	dataForm.pageId = pageId;
	// alert(dataForm.pageId);
	$.ajax({
		url : "PaginationController",
		type : "GET",
		data : dataForm,
		success : function(result) {
			$("#content").html(result); // jquery
		},
		error : function(jqXhr, textStatus, errorThrown) {
			$("#content").html("<h1>Loi!</h1>");
			alert("Co loi xay ra");
		}
	});

}