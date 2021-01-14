$(function(){
	$.ajax({
		url:'../GetProdList',
		dataType:'json',
		success: showContents,
		error: showErrors
	})
})

function showContents(result) {
	console.log(result);
	let data = result;
	for(val of data){
		let elem_1,elem_2,elem_3;
	//첫번째 자식요소
	elem_1 = $('<a />').attr('href', 'item.jsp?itemNo='+val.item_no);
	let e1_img = $('<img />')
		.attr('src', '../images/'+ val.image)
		.attr('alt', val.alt);
	e1_img.addClass("card-img-top");
	elem_1.append(e1_img);

	//두번째 자식요소
	elem_2 = $('<div />').addClass("card-body");
	let e2_h4 = $('<h4 />').addClass("card-title").append($('<a />').attr('href', 'item.jsp?itemNo='+val.item_no).html(val.item));
	let krw_price = new Intl.NumberFormat("ko-KR",
										  {style:"currency", currency:'KRW'}).format(val.price);
	let e2_h5 = $('<h5 />').html(krw_price);
	let e2_p = $('<p />').addClass("card-text").html(val.content);
	elem_2.append(e2_h4,e2_h5,e2_p);

	//세번째 자식요소
	elem_3 = $('<div />').addClass("card-footer");
	let star = '';
	for(let i =0;i<val.likeIt;i++){
		star += '&#9733;';
	}
	let e3_small = $('<small />').addClass("text-muted");
	e3_small.html(star);
	elem_3.append(e3_small);

	let div_1,
		div_2;
	div_1 = $('<div />').addClass('col-lg-4 col-md-6 mb-4');
	div_2 = $('<div />').addClass('card h-100');
	div_1.append(div_2);
	div_2.append(elem_1, elem_2, elem_3);

	$('.col-lg-9 .row').append(div_1);

	}
	
	
}

function showErrors(result) {
	console.log(result);
}