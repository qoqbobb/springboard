<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/views/common/common.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ExcelUpload</title>
</head>
<script type="text/javascript">
</script>
<body>
<form action="/excel/uploadAction.do" method="post" enctype="multipart/form-data">
	<input type="file" name="file">
    <input type="submit" value="제출" />
</form>

</body>
</html>