window.onload=function()
{
	//给back添加一个单击响应函数
	Qselect("#back").onclick=function(){
		history.back();
	};	
	
	//点击发送验证码，服务器检查该手机号是否已经被注册，根据服务器返回的消息给用户提示
	//并且重新发送变为倒计时60S，取消该Span的单击事件
	//60S后变为重新发送，并注册单击事件
	
	//输入验证码，并点击同意注册后服务器给出提示信息，如验证码错误等
	
};