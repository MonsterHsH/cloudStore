window.onload=function()
{ 
	var bodyWH=getWH(); 
	setTargetHeight("#outer",bodyWH.height-40+"px");
	//获取手机号的输入框
	var phone=document.getElementById("phoneNumber");
	//获取验证码的输入框
	var checkNumber=document.getElementById("checkNumber")
	//获取发送验证码按钮元素
	var sendCheck=document.getElementById("sendCheckRequrest");
	//获取登陆按钮元素
	var loginButton=document.getElementById("loginButton");
	//获取第三方登陆按钮元素
	var loginByQQ=document.getElementById("loginByQQ");
	var loginByWeChat=document.getElementById("loginByWeChat");
	var loginByBlog=document.getElementById("loginByBlog");
	//记录验证码的值
	var serverCheckNumber;
	//给发送验证码绑定事件
	sendCheckNumber(sendCheck,phone,function(receive){
		if(receive.length==6){
			serverCheckNumber=receive;	
		}else if(receive.equals("0")){ 
			alert("发送验证码失败，请稍后再试！");
		}else{
			alert("服务器繁忙，请稍后再试！");
		}
	});
	//给登陆按钮绑定事件
	loginButton.addEventListener("click",function(event){
		var target=event.target;
		if(Trim(phone.value,"g").length==0){
			alert("手机号不能为空！");
		}else if(Trim(checkNumber.value,"g").length==0){
			alert("验证码不能为空！");
		}else if(Trim(checkNumber.value,"g").length==6){
			var data={
				phoneNumber:phone.value,
				checkNumber:checkNumber.value,
				serverCheckNumber:serverCheckNumber
			}
			var message=JSON.stringify(data);	
			sendRequest(function(receive){
				//根据服务器返回的消息，判断注册是否成功
				var receiveNumber=Number.parseInt(receive);
				if(receiveNumber===1){
					//登陆成功，将用户的手机号保存 在本地，并跳转到首页
					var storage=localStorage;   
					storage["phone"]=phone.value;
					window.location.href="index.html";
				}else if(receiveNumber===2){
					alert("该手机号未注册！");
					if(confirm("是否要注册")){
						window.location.href="register.html";
					}
				}else if(receiveNumber===3){
					alert("验证码有误！");
				}else{
					alert("服务器繁忙，请稍后再试！");
				}
			},"type=login&"+"data="+message);
		}else{
			alert("验证码格式有误！");
		}
	},false);
	
	
	
	
	
	
	
	
}
//单击帮助后给出提示信息

//发送验证码后，该位置变为倒计时，倒计时结束提示重新发送
			 
//登陆时，根据返回值的不同给出不同的提示
			 
//点击第三方图标时给出提示

			
			