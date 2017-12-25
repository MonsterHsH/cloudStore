window.onload = function() {
	var bodyWH = getWH();
	setTargetWidth("#header", bodyWH.width + "px");
	setTargetWidth(".goodsList", bodyWH.width + "px");
	setTargetWidth("#eva textarea", bodyWH.width - 20 + "px");
	//给返回按钮绑定单击响应函数
	Qselect("#back").onclick = function() {
		history.back();
	};
	//获取星星的个数
	var count=0;
	getStarsCount();

	/*获取评价的星星数量*/
	function getStarsCount(){
		//获取评价的星星的元素
		var stars=Qselect("#evaCount input");
		for(var i=0;i<stars.length;i++){
			stars[i].num=i;
			//为星星绑定单击事件
			stars[i].onclick=function(event){
				var target=event.target;
				for(var j=0;j<stars.length;j++){
					if(stars[j].num<=target.num){
						stars[j].checked = true;
						stars[j].previousSibling.src = "../img/person/goodeva.png";	
					}else{
						stars[j].checked = false;
						stars[j].previousSibling.src = "../img/person/badeva.png";	
					}
				}
				count=target.num+1;
			};
		}
	};

//if(target.checked) {
//	for(var j = 0; j < i; j++) {
//		stars[j].checked = true;
//		stars[j].previousSibling.src = "../img/person/goodeva.png";
//	}
//}



};