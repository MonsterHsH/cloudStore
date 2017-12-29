window.onload=function(){
	var bodyWH = getWH();
	//屏幕自适应
	setTargetWidth("#header", bodyWH.width + "px");
	setTargetWidth("#storeList", bodyWH.width + "px");
	//给返回按钮绑定单击响应函数
	Qselect("#back").onclick = function() {
		history.back();
	};
};
