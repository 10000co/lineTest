<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>라인 테스트</title>
<script src="resources/jquery-3.3.1.min.js" type="text/javascript"></script>
<script>

$(function(){	
	$('#ajaxSend').on('click',sendFn);
});

function sendFn() {
	
	var accessToken = $('#accessToken').val(); 
	var msg = $('#msg').val(); 
	
// 	$.ajax({
// 		method : 'GET',
// 		url : 'http://192.168.0.100:5000/',
// 		data : 'accessToken=' + accessToken + '&msg=' + msg,
// 		dataType : 'json',
// 		success : function(resp) {
// 			alert(JSON.stringify(resp));
// 		}
// 	});

	var dataArr = {
			'accessToken' : accessToken,
			'msg' : msg
	};

// 	url : 'http://222.112.225.113:5000',
// url : 'http://192.168.0.100:5000'
	
	$.ajax({
		method : 'POST',
		url : 'http://222.112.225.113:5000',
		data : dataArr,
		contentType : 'application/x-www-form-urlencoded; charset=utf-8',
		dataType : 'json',
		success : function(resp) {
			alert(JSON.stringify(resp));
		}
	});
	
}

</script>
</head>
<body>

<form action="http://192.168.0.100:5000" class="form-lineNotify" method="POST">
	인증키 : <input id="accessToken" name="accessToken" type="password" /><br/><br/>
	
	<input id="msg" name="msg" type="text" />
	<input id="sendBtn" type="submit" value="보내기" />
	<input id="ajaxSend" type="button" value="ajax 로 보내기" />
</form>
</body>
</html>