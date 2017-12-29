window.onload=function()
{
	//给back添加一个单击响应函数
	Qselect("#back").onclick=function(){
		history.back();
	};
	//点击发送验证码，服务器检查该手机号是否已经被注册，根据服务器返回的消息给用户提示
	var serverCheckNumber;  //记录服务器返回的验证码
	//获取输入手机号的input
	var phone=document.getElementById("registerPhone").getElementsByTagName("input")[0];
	//获取并检验手机号是否正确
	var sendCheckButton=document.getElementById("sendCheck");
	//给发送验证码绑定单击事件
	sendCheckNumber(sendCheckButton,phone,function(receive){
		if(receive.length==6){
			serverCheckNumber=receive;			
		}else if(receive.equals("0")){
			alert("发送验证码失败，请稍后再试！");
		}else{
			alert("服务器繁忙，请稍后再试！");
		}
	});
	//输入验证码，并点击同意注册后服务器给出提示信息，如验证码错误等
	var confirmReister=document.getElementById("confirmReister");
	//给确认按钮注册事件
	confirmReister.addEventListener("click",function(event){
		var target=event.target;
		var checkNumber=document.getElementById("checkNumber").getElementsByTagName("input")[0];
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
					//注册成功，将用户的手机号保存在本地，并跳转到首页
					var storage=localStorage;   
					storage["phone"]=phone.value;
					window.location.href="index.html";
				}else if(receiveNumber===2){
					alert("该手机号已被注册！");
				}else if(receiveNumber===3){
					alert("验证码有误！");
				}else{
					alert("服务器繁忙，请稍后再试！");
				}
			},"type=register&"+"data="+message);
		}else{
			alert("验证码格式有误！");
		}
	},false);
};



 