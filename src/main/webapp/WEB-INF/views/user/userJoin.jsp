<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>join</title>
<script type="text/javascript">
	$j(document).ready(function(){
		
		var userChk;

	 	//아이디 영어,숫자 정규식
	 	$j(function(){
		       $j("#id").keyup(function (event) {
		            regexp = /[ㄱ-힣~!@#$%^&*()_+|<>?:{}= ]/g;
		            v = $j(this).val();
		            if (regexp.test(v)) {
		                
		                $j(this).val(v.replace(regexp, ''));
		            }
		        });
		});
	 	
		// 이름 한글 정규식
	 	$j(function(){
		       $j("#name").keyup(function (event) {
		            regexp = /[a-z0-9]|[ \[\]{}()<>?|`~!@#$%^&*-_+=,.;:\"'\\]/g;
		            v = $j(this).val();
		            if (regexp.test(v)) {
		                
		                $j(this).val(v.replace(regexp, ''));
		            }
		        });
		});
	 	
	 	
		
	 	// 우편번호 하이픈 추가
			$j(document).on("keyup", "#userAddr1", function() {
		     $j(this).val($j(this).val().replace(/[^0-9]/g, "").replace(/([0-9]{3})+([0-9]{3})$/,"$1-$2").replace("--", "-"));
		    }); 
	 	
	
		
		
		
		
	
		  //아이디 중복 체크
		$j("#checkId").on("click",function(){
			
			var $frm = $j('.userJoin :input[name="userId"]');
			var param = $frm.serialize();
		
			if(param != "userId="){
				$j.ajax({
					url : "/user/userCheck.do",
					dataType: "json",
					type: "POST",
					data: param,
					success: function(data, textStatus, jqXHR){
						if(data.success == "notNull"){
							alert("존재하는 아이디 입니다.")
							$j("#id").focus();
						}else {
							alert("사용가능한 아이디 입니다.")
							 userChk = "ok";
							$j("#pw").focus();
							console.log(userChk)
						}
					},
					error: function (jqXHR, textStatus, errorThrown)
					    {
					    	alert("실패");
					    }
				});
			}else{
				alert("id를 입력해주세요")
				$j("#id").focus();
			}
			
		})
		//아이디 중복 체크
		$j("#id").on("change keyup paste",function(){
			userChk = null;
			console.log(userChk)
		})
		
		//비밀번호 확인
		$j("#pwCheck , #pw").on("change keyup paste",function(){
			var pwCheck = $j("#pwCheck").val();
			var pw = $j("#pw").val();
			if(pwCheck.length == 0){
				$j('#pwCheckMsg').css("display","none")
				
			}else{
			if(pw != pwCheck){
				$j('#pwCheckMsg').css("display","block")
				$j('#pwCheckMsg').css("color","red")
				$j('#pwCheckMsg').text("비밀번호가 일치하지 않습니다");
			}else{
				$j('#pwCheckMsg').css("color","green")
				$j('#pwCheckMsg').text("비밀번호가 일치합니다");
				$j('#pwCheckMsg').css("display","block")
			}
			}
		})
		
		//등록
		$j("#join").on("click",function(){
			var id = $j("#id").val();
			var pw = $j("#pw").val();
			var pwCheck = $j("#pwCheck").val();
			var name = $j("#name").val();
			var phoneRegExp = /^\d{4}$/;	
			var userPhone2 = $j("#phone2").val();
			var userPhone3 = $j("#phone3").val();
			var postRegExp = /^\d{3}-\d{3}$/;
			var userAddr1 = $j("#userAddr1").val();
			
			/*아이디 중복 체크 확인 */
			if(userChk == "ok"){
			
			/*비밀번호 6글자이상 12글지이하  */
			if(pw.length < 6 || pw.length > 12){
				alert("비밀번호는 6~12 자리 이내로 입력해주세요");
				$j("#pw").focus();
				return;
			}else if (pwCheck.length == 0) {
				alert("pwCheck를 입력해주세요 ");
				$j("#pwCheck").focus();
				return;
			}else if(pw != pwCheck){
				alert("비밀번호가 일치하지 않습니다.");
				$j("#pwCheck").focus();
				return;
			}
			
			/* 이름 null 확인 */
			if(name.length == 0){
				alert("이름을 입력해주세요")
				$j("#name").focus();
				return;
			}
			
			/* 번호 정규식 확인 */
			if(!phoneRegExp.test(userPhone2)){
				alert("핸드폰 중간번호 4자리 숫자로 입력해주세요");
				$j("#phone2").focus();
				return;
			}
			
			/* 번호 정규식 확인 */
			if(!phoneRegExp.test(userPhone3)){
				alert("핸드폰 끝번호 4자리 숫자로 입력해주세요");
				$j("#phone3").focus();
				return;
			}
			
			/* 우편번호 정규식 확인 */
			if(!postRegExp.test(userAddr1)){
				alert("postNo의 형식은 xxx-xxx 입니다.")
				$j("#userAddr1").focus();
				return;
			};
			
		
			
			var $frm = $j('.userJoin :input');
			var param = $frm.serialize();
			
			console.log("param");
			console.log(param)
		
		
			$j.ajax({
			    url : "/user/userJoinAction.do",
			    dataType: "json",
			    type: "POST",
			    data : param,
			    success: function(data, textStatus, jqXHR)
			    {
					alert("회원가입 완료");
					
					alert("메세지:"+data.success);
					
					/* location.href = "/board/boardList.do?pageNo="; */
					location.href = "/board/boardList.do";
			    },
			    error: function (jqXHR, textStatus, errorThrown)
			    {
			    	alert("전송오류");
			    	alert(jqXHR.responseText);
			    }
			});

			}else {
				if(id.length == 0){
					alert("아이디를 입력해 주세요")
					$j("#id").focus();
				}else{
					alert("아이디 중복 체크 확인")
					$j("#id").focus();
				}
			}
		})
	
});
</script>
</head>
<body>
	<form class="userJoin">
	<table align="center">
	
		<tr>
			<td>
				<a href="/board/boardList.do">List</a>
			</td>
		</tr>
		
		<tr>
			<td>
				<table id="joinTable" border="1">
				
					<tr>
						<td width="120" align="center">
							id
						</td>
						<td width="300">
							<input name="userId" type="text" size="15"  id="id" value="${user.userId}" maxlength='15'  >
							<input id="checkId" type="button"  value="중복확인">
						</td>
					</tr>
					<tr>
						<td width="120" align="center">
							pw
						</td>
						<td width="300">
							<input name="userPw" type="password" size="20" id="pw" value="${user.userPw}" >
						</td>
					</tr>
					<tr>
						<td width="120" align="center">
							pw check
						</td>
						<td width="300">
							<input name="userPwCheck" type="password" size="20" class="abc" id="pwCheck" maxlength='16' >
							<p style=" display: none; font-size: 5px" id="pwCheckMsg" ></p>
						</td>
					</tr>
					<tr>
						<td width="120" align="center">
							name
						</td>
						<td width="300">
							<input name="userName" type="text" size="20" id="name" value="${user.userName}" maxlength='5' >
						</td>
					</tr>
					<tr>
						<td width="120" align="center">
							phone
						</td>
						<td width="300">
							<select name="userPhone1">
								<c:forEach items="${codeList}" var="codeList">
									<option value="${codeList.codeId}">${codeList.codeName}</option>
								</c:forEach>
							</select>
							-
							<input name="userPhone2" type="text" size="4"  id="phone2"  value="${user.userPhone2}" maxlength='4'>
							-
							<input name="userPhone3" type="text" size="4"  id="phone3" value="${user.userPhone3}" maxlength='4'>
						</td>
					</tr>
					<tr>
						<td width="120" align="center">
							postNo
						</td>
						<td width="300">
							<input name="userAddr1" type="text" size="20"  id="userAddr1" value="${user.userAddr1}" maxlength='6'>
						</td>
					</tr>
					<tr>
						<td width="120" align="center">
							address
						</td>
						<td width="300">
							<input name="userAddr2" type="text" size="20"  id="userAddr2" value="${user.userAddr2}" maxlength='50'>
						</td>
					</tr>
					<tr>
						<td width="120" align="center">
							company
						</td>
						<td width="300">
							<input name="userCompany" type="text" size="20" id="userCompany" value="${user.userCompany}" maxlength='20'>
						</td>
					</tr>
					
				</table>
			</td>
		</tr>
		<tr>
			<td style="float: right;">
				<input id="join" type="button" value="Join">
			</td>
		</tr>
		
	</table>
	</form>
</body>
</html>