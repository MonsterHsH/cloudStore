<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<!-- <form action="fileDownLoadTest" enctype="multipart/form-data" method="post">    
上传用户：<input type="text" name="username"><br/>
          上传文件1：<input type="file" name="file1"><br/>
          上传文件2：<input type="file" name="file2"><br/>
          <input type="submit" value="提交">
</form> -->
	<form action="fileDownLoadTest" enctype="multipart/form-data" method="post">
		上传用户：<input type="text" name="username"><br>
		上传文件1：<input type="file" name="file1"><br>
		 上传文件2：<input type="file" name="file2"><br>
		<input type="submit" value="提交">
	</form>
	<img alt="图片" src="<%=basePath%>/img/timg.jpg">



</body>
</html>