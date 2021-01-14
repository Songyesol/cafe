<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jcafe/item.jsp</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<% //자바 영역입니다.
	String itemNo = request.getParameter("itemNo");
%> 
<script>
	$(function(){
		$.ajax({
			url :'../GetProductSurv',
			data:{item_no:<%=itemNo%>},
			dataType:'json',
			success : function(result){
				$('#item_no').val(result.item_no);
				$('#item').val(result.item);
				},
			error : function(result){
				console.log(result)}
		})
		
		$('#btnChange').on('click', function(){
			console.log($('$btnChange'));
		})
	}); //페이지를 로딩한다음에 마지막에 실행하겠다.
</script>
</head>
<body>
	<form type='text', id='item_no', name="item_no"></form>
	<form type='text', id='item', name="item"></form>
	<form type='submit', id='btnChange', name="btnChange"></form>
</body>
</html>