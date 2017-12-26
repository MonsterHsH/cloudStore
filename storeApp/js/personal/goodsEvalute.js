window.onload = function() {
	var bodyWH = getWH();
	var goodEvaCount;    //好评分数
	var stars=document.getElementById("evaCount").getElementsByTagName("input");//获取表示星星的元素
	setTargetWidth("#header", bodyWH.width + "px");
	setTargetWidth(".goodsList", bodyWH.width + "px");
	setTargetWidth("#eva textarea", bodyWH.width - 20 + "px");
	//给返回按钮绑定单击响应函数
	Qselect("#back").onclick = function() {
		history.back();
	};
	//给每个星星绑定单击事件
	for(var i=0;i<stars.length;i++){
		var star=stars[i];
		star.num=i;
		//当单击星星时，将该星星以及前边的星星全部变为彩色，并将checked属性改为true
		//将后面的星星变成灰色，checked属性变为false
		star.onclick=function(event){
			var target=event.target;
			for(var j=0;j<target.num+1;j++){
				stars[j].checked=true;
				stars[j].previousSibling.src = "../img/person/goodeva.png";
			}
			for(;j<stars.length;j++){
					stars[j].checked=false;
					stars[j].previousSibling.src = "../img/person/badeva.png";
			}
		};
	}
	//获取好评数
	function getEvaCount(){
		for(var i=0;i<stars.length;i++){
			if(stars[i].checked==false){
				return i;
			}
		}
		return i;
	};
	goodEvaCount=getEvaCount();        //获取好评的星星的个数
	



};