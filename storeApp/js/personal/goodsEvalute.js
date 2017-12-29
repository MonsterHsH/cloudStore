window.onload = function() {
	var bodyWH = getWH();
	var goodEvaCount;    //好评分数
	setTargetWidth("#header", bodyWH.width + "px");
	setTargetWidth(".goodsList", bodyWH.width + "px");
	setTargetWidth("#eva textarea", bodyWH.width - 20 + "px");
	//给返回按钮绑定单击响应函数
	Qselect("#back").onclick = function() {
		history.back();
	};
	//给评价绑定单击事件
	document.getElementById("badEva").addEventListener("click",function(event){
		event.preventDefault();           
		event.stopPropagation();
		var target=event.target.parentNode;
		var image=target.getElementsByTagName("img")[0];
		var check=target.getElementsByTagName("input")[0];
		image.src="../img/person/eva3.png";
		check.checked=true;
		document.getElementById("generalEva").getElementsByTagName("img")[0].src="../img/person/eva4.png";
		document.getElementById("goodEva").getElementsByTagName("img")[0].src="../img/person/eva4.png";
		document.getElementById("generalEva").getElementsByTagName("input")[0].checked=false;
		document.getElementById("goodEva").getElementsByTagName("input")[0].checked=false;
		goodEvaCount=-1;
	},true);
	document.getElementById("generalEva").addEventListener("click",function(event){
		event.preventDefault();           
		event.stopPropagation();
		var target=event.target.parentNode;
		var image=target.getElementsByTagName("img")[0];
		var check=target.getElementsByTagName("input")[0];
		image.src="../img/person/eva2.png";
		check.checked=true;
		document.getElementById("badEva").getElementsByTagName("img")[0].src="../img/person/eva4.png";
		document.getElementById("goodEva").getElementsByTagName("img")[0].src="../img/person/eva4.png";
		document.getElementById("badEva").getElementsByTagName("input")[0].checked=false;
		document.getElementById("goodEva").getElementsByTagName("input")[0].checked=false;
		goodEvaCount=0;
	},true);
	document.getElementById("goodEva").addEventListener("click",function(event){
		event.preventDefault();           
		event.stopPropagation();
		var target=event.target.parentNode;
		var image=target.getElementsByTagName("img")[0];
		var check=target.getElementsByTagName("input")[0];
		image.src="../img/person/eva1.png";
		check.checked=true;
		document.getElementById("badEva").getElementsByTagName("img")[0].src="../img/person/eva4.png";
		document.getElementById("generalEva").getElementsByTagName("img")[0].src="../img/person/eva4.png";
		document.getElementById("badEva").getElementsByTagName("input")[0].checked=false;
		document.getElementById("generalEva").getElementsByTagName("input")[0].checked=false;
		goodEvaCount=1;
	},true);
	
	

	



};