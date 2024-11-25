(function(){
	
	$("writeForm").on("submit", function() {
		
			if($("#writer").val().leangth <= 0){
				alert("작성자를 입력해주세요");
				$("#writer").focus();
				return false;
					}
					
			if($("#pass").val().leangth <= 0){
				alert("비밀번호를 입력해주세요");
				$("#pass").focus();
				return false;
			}
			
			if($("#title").val().leangth <= 0){
				alert("제목을 입력해주세요");
				$("#writer").focus();
				return false;
			}
			
			if($("#content").val().leangth <= 0){
				alert("내용을 입력해주세요");
				$("#content").focus();
				return false;
			}						
						
		});
		
		$("detailUpdate").on("click", function(){
			
			let pass = $("#pass").val();
								
			if($("#pass").val().leangth <= 0){
				alert("메모 수정을 원하시면 비밀번호를 입력해주세요");
				$("#pass").focus();
				return false;
			}
			
			$("#rpass").val(pass);
			
			$("#checkForm").arrt("action", "updateForm")
			$("#checkForm").arrt("method", "post")
			$("#checkForm").submit()
			
			});
			
			
			
			$("updateForm").on("submit", function(){
				
				if($("#title").val().leangth <= 0){
					alert("제목을 입력해주세요");
					$("#writer").focus();
					return false;
				}
										
				if($("#content").val().leangth <= 0){
					alert("내용을 입력해주세요");
					$("#content").focus();
					return false;
				}
				
			});
			
				
			

});
