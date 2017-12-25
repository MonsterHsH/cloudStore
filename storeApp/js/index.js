window.onload=function(){
	alert("hello!");
	var bodyWH = getWH();
	//屏幕自适应
	setTargetWidth("#outer", bodyWH.width + "px");
	setTargetWidth("#indexHeader", bodyWH.width + "px");
	setTargetWidth("#footer", bodyWH.width + "px");
	setTargetWidth("#searchDiv", bodyWH.width - 100 + "px");
	setTargetWidth("#carouselFigure", bodyWH.width + "px");
	setTargetWidth("#functionEntry", bodyWH.width + "px");
	setTargetWidth("#goodsScan", bodyWH.width + "px")
	setTargetWidth("#slider li",bodyWH.width+"px");
	   
	//轮播图处理
	var slider = Qselect("#slider"); 
	var pointDiv = Qselect("#pointDiv");	                            //设置轮播图中的存放点的DIV点
	var icon = pointDiv.getElementsByTagName("span");
	AppTouch(slider,icon,pointDiv,bodyWH.width,true,3000);

	//给学生店铺设置单击事件
	Qselect("#studentStore").onclick=function(){
		 window.location.href = "store/storeList.html";
	};
	
	Qselect("#oldGoods").onclick=function(){
		 window.location.href = "goodsManger/goodsList.html";
		 
		 //向下一个页面提供请求二手交易的信息
	};
	
	Qselect("#lostAndFound").onclick=function(){
		 window.location.href="lostAndFound/lostAndFound.html";
	};
	
	Qselect("#shareTrading").onclick=function(){
		 window.location.href = "shareTrading/shareTrading.html";
		
	};
	
	
	Qselect("#newGoods").onclick=function(){
		 window.location.href = "goodsManger/goodsList.html";
	};

	Qselect("#hotGoods").onclick=function(){
		 window.location.href = "goodsManger/goodsList.html";
	};
	/*******************************************************************/
	/*！！！！！！！！！！！！！！！！！！！向服务器请求商品信息！！！！！！！！！！！！！！！！！！！！！！**/
	/*******************************************************************/
		
		
		
		
	//给商品添加单击事件
	var goods = Qselect("#goodsScan ul li");
	for(var i=0;i<goods.length;i++){
		goods[i].onclick=scanGoods;
	}
	
	//给事件添加的单击响应函数
	function scanGoods(event){
		var target=event.target;
		var id=target.id;
		//若点击的是li，则将li的id转发给下一个页面，否侧，将target的父元素的id转发给下一个页面
		if(!id){
			id=target.parentElement.id;
		}
		//将goodsID传给下一个页面
		window.location.href = "goodsManger/goodsDetails.html";
	};



};

