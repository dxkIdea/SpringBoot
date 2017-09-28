var userJs = {
		addUser : function(){
			alert(userName + passWord);
			$.ajax(function(){
				data:$("#form").serialize(),
				type:"POST",
				dataType:"JSON",
				url:"/user/registUser",
				success : function(data){
					alert("注册成功");
				},
				erro : function(data){
					alert("注册失败");
				}
				
			})
		}
}