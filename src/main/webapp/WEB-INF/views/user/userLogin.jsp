<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
</head>
<script type="text/javascript">

	$j(document).ready(function(){
		$j("#login").on("click",function(){
			
			var id = $j('input[name="userId"]').val();
			var pw = $j('input[name="userPw"]').val();
			
			var $frm = $j('.userLogin :input');
			var param = $frm.serialize();
			
			if((id.length != 0 && pw.length != 0)){
			
			$j.ajax({
				url : "/user/userLoginAction.do",
				dataType: "json",
				type: "POST",
				data : param,
				success: function(data, textStatus, jqXHR)
			    {
					if(data.success != null){
						alert("로그인 성공")
					location.href = "/board/boardList.do";
					}else {
						alert("아이디 또는 패스워드가 틀렸습니다.")
					}
			    },
			    error: function (jqXHR, textStatus, errorThrown)
			    {
			    	alert("실패");
			    }
			})
			
			}else if(id.length == 0){
				alert("아이디를 입력해주세요")
				$j("#userId").focus();
			}else if(pw.length == 0){
				alert("비밀번호를 입력해주세요")
				$j("#userPw").focus();
			}else{
			}
		})	
	})
</script>
<body>
	<form class="userLogin">
		<table align="center">
		
			<tr>
				<td>
					<table id="joinTable" border="1">
						<tr>
							<td width="120" align="center">
								id
							</td>
							<td width="300">
								<input name="userId" id="userId" type="text" size="20" value="${user.userId}">
							</td>	
						</tr>
						<tr>
							<td width="120" align="center">
								pw
							</td>
							<td width="300">
								<input name="userPw" id="userPw" type="password" size="20" value="${user.userPw}">
							</td>	
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td align="right">
					<input id="login" type="button" value="login">
				</td>
			</tr>
		</table>
	</form>

</body>
</html>