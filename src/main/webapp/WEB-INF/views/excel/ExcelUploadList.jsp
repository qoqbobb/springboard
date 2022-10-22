<%@page import="com.spring.board.vo.UserVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/views/common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>



<table id="boardAll"  align="center">

<tr>
		<td>
			<table id="boardTable" border = "1">
				<tr>
					<td width="80" align="center">
						NO
					</td>
					<td width="40" align="center">
						제목
					</td>
					<td width="300" align="center">
						내용
					</td>
						<td width="80" align="center">
						작성자
					</td>
						<td width="80" align="center">
						타입
					</td>
					</td>
						<td width="80" align="center">
						분류
					</td>
				</tr>
				<c:forEach items="${datas}" var="list">
					<tr>
						<td align="center">
							${list.boardNum}
						</td>
						<td>
							${list.boardTitle}
						</td>
						<td>
							${list.boardComment}
						</td>
							<td>
							${list.creator}
						</td>
							<td>
							${list.boardType}
						</td>
							<td>
							${list.codeName}
						</td>
					</tr>
					</c:forEach>	
			</table>
			
		</td>
	</tr>
</table>
</body>
</html>