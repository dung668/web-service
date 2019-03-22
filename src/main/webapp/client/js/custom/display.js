//let urlFile = 'data.json';
//let defaultPrice = 'Giá bán';
let listView = false;
//let result;
//let totalPage;
//let currentPage = 1;
//let itemPerPage = 12;

//pageClick = () => {
//	let start = (currentPage - 1) * itemPerPage;
//	let end = start + itemPerPage;
//}

/////////////////////////////////
///////////////////////////////////////

let listViewClick = () => {
	$('.item-product').addClass("item-product-list");
	$('.thumb').addClass("float-left");
	$('.info').addClass("info-list");
	$('.list-product').addClass("list-product-center");
	listView = true;
}

let gridViewClick = () => {
	$('.item-product').removeClass("item-product-list");
	$('.thumb').removeClass("float-left");
	$('.info').removeClass("info-list");
	$('.list-product').removeClass("list-product-center");
	listView = false;
}

//let likestBtnClick = () =>{
//	$('#newest').removeClass('btn-selected').addClass('btn-no-selected');
//	$('#salest').removeClass('btn-selected').addClass('btn-no-selected');
//	$('#price-filter').removeClass('btn-selected').addClass('btn-no-selected').html(defaultPrice);
//	$('#likest').removeClass('btn-no-selected').addClass('btn-selected');
//	arrangeList('like');
//}
//
//let newestBtnClick = () =>{
//	$('#likest').removeClass('btn-selected').addClass('btn-no-selected');
//	$('#salest').removeClass('btn-selected').addClass('btn-no-selected');
//	$('#price-filter').removeClass('btn-selected').addClass('btn-no-selected').html(defaultPrice);
//	$('#newest').removeClass('btn-no-selected').addClass('btn-selected');
//	arrangeList('id');
//}
//
//let salestBtnClick = () =>{
//	$('#newest').removeClass('btn-selected').addClass('btn-no-selected');
//	$('#likest').removeClass('btn-selected').addClass('btn-no-selected');
//	$('#price-filter').removeClass('btn-selected').addClass('btn-no-selected').html(defaultPrice);
//	$('#salest').removeClass('btn-no-selected').addClass('btn-selected')
//	arrangeList('saled');
//}

//let upDownPriceBtnClick = (title) =>{
//	$('#newest').removeClass('btn-selected').addClass('btn-no-selected');
//	$('#likest').removeClass('btn-selected').addClass('btn-no-selected');
//	$('#salest').removeClass('btn-selected').addClass('btn-no-selected');
//	$('#price-filter').removeClass('btn-no-selected').addClass('btn-selected').html(title);
//	arrangeList('upDownPrice');
//}
//
//let downUpPriceBtnClick = (title) =>{
//	$('#newest').removeClass('btn-selected').addClass('btn-no-selected');
//	$('#likest').removeClass('btn-selected').addClass('btn-no-selected');
//	$('#salest').removeClass('btn-selected').addClass('btn-no-selected');
//	$('#price-filter').removeClass('btn-no-selected').addClass('btn-selected').html(title);
//	arrangeList('downUpPrice');
//}
//
//let previousClick = () => {
//	if(currentPage == 1){
//		return;
//	}
//	currentPage--;
//	$('#page-' + currentPage).click();
//}
//
//let nextClick = () => {
//	if(currentPage == $('.page').length){
//		return;
//	}
//	currentPage++;
//	$('#page-' + currentPage).click();
//}

$(document).ready(function(){
	$('.list-view').click(listViewClick);
	$('.grid-view').click(gridViewClick);

//	$('#newest').click(newestBtnClick);
//	$('#likest').click(likestBtnClick);
//	$('#salest').click(salestBtnClick);
//	$('#up-down-price').click(function(){
//		let title = $(this).text();
//		upDownPriceBtnClick(title);
//	});
//	$('#down-up-price').click(function(){
//		let title = $(this).text();
//		downUpPriceBtnClick(title);
//	});

//	$(document).on('click', '.page', function(e){
//		currentPage = parseInt($(this).text());
//		$('.page').removeClass('current-page');
//		$(this).addClass('current-page');
//		pageClick();
//	});
//
//	$(document).on('click', '.previous', previousClick);
//	$(document).on('click', '.next', nextClick);
});