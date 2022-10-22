<%@page import="com.spring.board.vo.UserVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@include file="/WEB-INF/views/common/common.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>list</title>
</head>
<script type="text/javascript">




	$j(document).ready(function(){
		
		
		
		$j("#checkall").click(function(){
			if($j("#checkall").is(":checked")){
				$j("input[name=typeArr]").prop("checked", true);
			}else{
				$j("input[name=typeArr]").prop("checked", false);
			}
		});
		
		$j("input[name=typeArr]").click(function() {
			var all = $j("input[name=typeArr]").length;
			var checked = $j("input[name=typeArr]:checked").length;

			if(all != checked) $j("#checkall").prop("checked", false);
			else $j("#checkall").prop("checked", true); 
		});
		
		
		$j('#checkBtn').click(function() {
			
			var arr = [];
			
			$j('input[name=typeArr]:checked').each(function(i) {
				arr.push($j(this).val());
			});
			console.log(arr);
			
			$j.ajax({
			    url : "/board/boardListTest.do",
			    dataType: "html",
			    raditional: true,
			    data: {arr : arr},
			    type: "POST",
			    success: function(data)
			    {
					alert("성공");
					
					$j('#boardAll').empty();
					$j('#boardAll').html(data)
					
			    },
			    error: function (jqXHR, textStatus, errorThrown)
			    {
			    	alert("실패");
			    	
			    } 
			});
			
			
			
		})
	
		$j('#logout').click(function() {
			$j.ajax({
				url : "/user/userLogoutAction.do",
				dataType: "json",
			    type: "POST",
			    success: function(data, textStatus, jqXHR)
			    {
					
					if (data.success == "Y") {
					alert("로그아웃 되었습니다.");
						
					}
					location.href = "/board/boardList.do";
			    },
			    error: function (jqXHR, textStatus, errorThrown)
			    {
			    	alert("실패");
			    }
				
			})
			
		})
			$j('#excelDown').click(function() {
			$j.ajax({
				url : "/excel/download1.do",
				dataType: "json",
			    type: "POST",
			    success: function(data, textStatus, jqXHR)
			    {
					alert("yy")
					if (data.success == "Y") {
					alert("y");
						
					}
					location.href = "/board/boardList.do";
			    },
			    error: function (jqXHR, textStatus, errorThrown)
			    {
			    	alert(jqXHR.responseText);
			    }
				
			})
			
		})
		
		
		

	
	});

</script>
<body>
<div>session값 : ${login}</div>
<div>session아이디 : ${login.userId}</div>


<table id="boardAll"  align="center">

	<tr>
		<td align="left">
			<c:choose>
				<c:when test="${login == null}">
					<a href="/user/userLogin.do">login</a>
					<a href="/user/userJoin.do">join</a>
				</c:when>
				<c:when test="${login != null}">
					${login.userName}
				</c:when>
			</c:choose>
			<span style="float: right;">total : ${totalCnt}</span>
		</td>
	</tr>
	<tr>
		<td>
			<table id="boardTable" border = "1">
				<tr>
					<td width="80" align="center">
						Type
					</td>
					<td width="40" align="center">
						No
					</td>
					<td width="300" align="center">
						Title
					</td>
				</tr>
				<c:forEach items="${boardList}" var="list">
					<tr>
						<td align="center">
							${list.codeName}
						</td>
						<td>
							${list.boardNum}
						</td>
						<td>
							<a href = "/board/${list.boardType}/${list.boardNum}/boardView.do?pageNo=${pageNo}">${list.boardTitle}</a>
						</td>
					</tr>	
				</c:forEach>
			</table>
			
		</td>
	</tr>

	<tr>
		<td align="right">
		<c:choose>
			<c:when test="${login==null}">
			<a href ="/board/boardWrite.do">글쓰기</a>
			
			<!-- <a href="#" id="excelDown">ExcelDownload</a> -->
			

			</c:when>
			<c:when test="${login!= null}">
			<a href ="/board/boardWrite.do">글쓰기</a>
			<a href="#" id="logout">로그아웃</a>
			
			</c:when> 
		</c:choose>
		</td>
		</tr> 
		
		<tr>
		<td align="right">
			<form style="display: inline;" action="/excel/download1.do" method="post">
	   			<input type="submit" value='엑셀 다운로드'>
			</form>
			<form style="display: inline;" action="/excel/upload.do" method="get">
	   			<input type="submit" value='엑셀 업로드'>
			</form>
			<form style="display: inline;" action="/excel/calendar.do" method="get">
	   			<input type="submit" value='엑셀 달력'>
			</form>
		</td>
		</tr>
	
</table>	
<table   align="center">


	<tr>
	<td>
	<label><input type="checkbox"  id="checkall">전체</label>
	<c:forEach items="${codeList}" var="codeList">
		<label><input type="checkbox" name="typeArr" value="${codeList.codeId}">${codeList.codeName}</label>
	</c:forEach>
	<button type="button" id="checkBtn">조회</button>
	</td>
	</tr>
</table>
</body>
</html>