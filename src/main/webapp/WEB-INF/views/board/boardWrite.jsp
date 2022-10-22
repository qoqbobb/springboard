<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/common.jsp"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>boardWrite</title>
</head>
<script type="text/javascript">

	$j(document).ready(function(){
		
		var cnt = 0;
		$j('input[name=insertAllNum]').attr('value',cnt);
		function addRow(i){
			var addInsert = '<tr class = "Type">'+
								'<td width="120" align="center">'+
								'<input type="checkbox" name="checkList">'+
								'Type'+
								'</td>'+
								'<td width="400">'+
								'<select name="insertList['+i+'].boardType">'+
								'<c:forEach items="${codeList}" var="codeList">'+
								'<option value="${codeList.codeId}">${codeList.codeName}</option>'+
								'</c:forEach>'+
								'</select>'+
								'</td>'+
								'</tr>'+	
						 	 '<tr class="Title">'+
								'<td width="120" align="center">'+
								
								'Title'+
								'</td>'+
			       			    '<td  width="400">'+
			       			    '<input name="insertList['+i+'].boardTitle" type="text" size="50" value="${board.boardTitle}">'+ 
			        		    '</td>'+
			      		 	 '</tr>'   +
			      		 	 '<tr class="Comment">'+
			        		 	'<td height="300" align="center">'+
			        		 	'Comment'+
			        		 	'</td>'+
			        		 	'<td valign="top">'+
							 	'<textarea name="insertList['+i+'].boardComment"  rows="20" cols="55" value="${board.boardComment}"/>'+
			        		 	'</td>'+
			     		  	'</tr>';		
			var lastComment = $j("tr[class=Comment]:last");
			lastComment.after(addInsert);
		}
		
		$j("#rowAdd").on("click",function(){
			cnt++;
			console.log("cnt:" +cnt);
			addRow(cnt);
			$j('input[name=insertAllNum]').attr('value',cnt);
		});
		
		
		$j("#rowDelete").on("click",function(){
			var checkedNum = $j("input:checkbox[name=checkList]:checked").length
			
			
			
			$j("input:checkbox[name=checkList]:checked").each(function(i,value){
				console.log(value);
				cnt--
				var type = value.parentNode.parentNode;
				console.log(type);
				
				var title =value.parentNode.parentNode.nextElementSibling;
				console.log(title);
				
				var comment =value.parentNode.parentNode.nextElementSibling.nextElementSibling;
				console.log(comment);
				
				$j(title).remove();
				$j(comment).remove();
				$j(type).remove();
				
				console.log("cnt : " + cnt)
				$j('input[name=insertAllNum]').attr('value',cnt);
			});
		});
		
		
		
		
		
		$j("#submit").on("click",function(){
			var $frm = $j('.boardWrite :input');
			var param = $frm.serialize();
			
			console.log("param");
			console.log(param)
			
			$j.ajax({
			    url : "/board/boardWriteAction.do",
			    dataType: "json",
			    type: "POST",
			    data : param,
			    success: function(data, textStatus, jqXHR)
			    {
					alert("등록완료");
					
					alert("메세지:"+data.success);
					
					/* location.href = "/board/boardList.do?pageNo="; */
					location.href = "/board/boardList.do";
			    },
			    error: function (jqXHR, textStatus, errorThrown)
			    {
			    	alert("실패");
			    }
			});
		});
		
		
	});
	

</script>
<body>
${login}
<form class="boardWrite">
	<table align="center">
		<tr>
			<td align="right">
			<input type="hidden" name="insertAllNum" >
			<input id="rowAdd" type="button" value="행추가">
			<input id="rowDelete" type="button" value="행삭제">
			<input id="submit" type="button" value="작성">
			</td>
		</tr>
		<tr>
			<td>
				<table border ="1"> 
					<tr class="Type">
						<td width="120" align="center">
						Type
						</td>
						<td width="400">
						<select name="insertList[0].boardType">
						<c:forEach items="${codeList}" var="codeList">
						<option value="${codeList.codeId}">${codeList.codeName}</option>
						</c:forEach>
						</select> 
						</td>
					</tr>
					<tr class="Title">
						<td  width="120" align="center">
						Title
						</td>
						<td width="400">
						<input name="insertList[0].boardTitle" type="text" size="50" value="${board.boardTitle}"> 
						</td>
					</tr>
					<tr class=Comment>
						<td height="300" align="center">
						Comment
						</td>
						<td valign="top">
						<textarea name="insertList[0].boardComment"  rows="20" cols="55">${board.boardComment}</textarea>
						</td>
					</tr>
					
					<tr>
						<td align="center">
						Writer
						</td>
						<td width="400">
						 <c:choose>
						<c:when test="${login == null}">
						 <input name="insertList[0].creator" type="text" size="50" value="${board.creator}" maxlength='5'>
						</c:when>
						<c:when test="${login != null}">
						 <input name="insertList[0].creator" type="text" size="50" value="${login.userName}" maxlength='5' readonly="readonly"> 
						</c:when>
						</c:choose> 
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td align="right">
				<a href="/board/boardList.do">List</a>
			</td>
		</tr>
	</table>
</form>	
</body>
</html>