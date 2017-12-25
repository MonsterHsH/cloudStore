window.onload=function(){
	var bodyWH = getWH();
	setTargetWidth("#header", bodyWH.width + "px");
	//给订单页面设置宽度
	setTargetWidth("#orderBody", bodyWH.width + "px");
	setTargetWidth("#orderBody>ul", bodyWH.width*5 + "px");
	setTargetWidth(".goodsTitle", bodyWH.width - 120 + "px");
	
	//给返回按钮绑定单击响应函数
	Qselect("#back").onclick = function() {
		history.back();
	};
	//为菜单栏绑定单击响应函数
	var allLi=Qselect("#allLi");
	var waitPayLi=Qselect("#waitPayLi");
	var waitSendLi=Qselect("#waitSendLi");
	var waitRecLi=Qselect("#waitRecLi");
	var waitEvaLi=Qselect("#waitEvaLi");
	var ul=Qselect("#orderBody>ul");
	var menuUnderLine=Qselect("#bottomMenu span");
	allLi.onclick=function(event){
		ul.style.left=0;
		menuUnderLine.style.left=0;
	};
	waitPayLi.onclick=function(event){
		ul.style.left=-bodyWH.width+"px";
		menuUnderLine.style.left=bodyWH.width*0.2+"px";
	};
	waitSendLi.onclick=function(event){
		ul.style.left=-bodyWH.width*2+"px";
		menuUnderLine.style.left=bodyWH.width*0.2*2+"px";
	};
	waitRecLi.onclick=function(event){
		ul.style.left=-bodyWH.width*3+"px";
		menuUnderLine.style.left=bodyWH.width*0.2*3+"px";
	};
	waitEvaLi.onclick=function(event){
		ul.style.left=-bodyWH.width*4+"px";
		menuUnderLine.style.left=bodyWH.width*0.2*4+"px";
	};
	
};