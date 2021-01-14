<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jcafe/upload.jsp</title>
</head>
<body>
<!--사진업로드 위치 C:\Dev\workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\JavaCafe\jcafe  -->
	<form method="post" enctype='multipart/form-data' action='../FileUpload'>
	<input type='file' name='attach1'/>
	<input type='submit' value='파일업로드'/>
	</form>
</body>
</html>