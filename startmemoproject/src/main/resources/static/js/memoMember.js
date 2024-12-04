$(function(){
	
	$("#loginForm").on("submit", function() {
		
		let id = $("#userId").val();
		let pass = $("#userPass").val();
		
			if(id.length <= 0){
				alert("아이디를 입력해주세요");
				$("#userId").focus();
				return false;
					}
					
			if(pass.length <= 0){
				alert("비밀번호를 입력해주세요");
				$("#userPass").focus();
				return false;
					}
			
					
			// 모달 로그인 폼이 submit 될 때 폼 유효성 검사를 위한 이벤트 처리
			$("#modalLoginForm").submit(function(){
				let id = $("#modalUserId").val();
				let pass = $("#modalUserPass").val();
				
				if(id.length <= 0){
					alert("아이디를 입력해주세요");
					$("#modalUserId").focus();
					return false;
				}
				
				if(pass.length <= 0){
				alert("비밀번호를 입력해주세요");
				$("#modalUserPass").focus();
				return false;
				}
			})		
		});
		
		$("#detailUpdate").on("click", function(){
			
			
			let pass = $("#pass").val();
								
			if(pass.length <= 0){
				alert("메모 수정을 원하시면 비밀번호를 입력해주세요");
				$("#pass").focus();
				return false;
			}
			
			$("#rPass").val(pass);
			$("#checkForm").attr("action", "updateForm")
			$("#checkForm").attr("method", "post")
			$("#checkForm").submit()
			
			});
			
			
			
			
			$("#updateForm").on("submit", function(){
				
				if($("#title").val().length <= 0){
					alert("제목을 입력해주세요");
					$("#writer").focus();
					return false;
				}
										
				if($("#content").val().length <= 0){
					alert("내용을 입력해주세요");
					$("#content").focus();
					return false;
				}
				
			});
			
			$("#detailDelete").on("click", function(){
				
				let pass = $("#pass").val();
				
				if(pass.length <= 0){
					alert("메모 삭제를 원하시면 비밀번호를 입력해주세요");
					$("#pass").focus();
					return false;
				}
				
				$("#rPass").val(pass);
				$("#checkForm").attr("action", "delete")
				$("#checkForm").attr("method", "post")
				$("#checkForm").submit()
				
				
			});
			
				
			

});
