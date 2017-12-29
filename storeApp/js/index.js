window.onload=function(){
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
	
	//检测是否已经登陆，若没登陆则跳转到login页面
	
	//获取热销商品
	
	//上拉加载热销商品
	
	//滚屏处理

	//轮播图处理
	var slider = Qselect("#slider"); 
	var pointDiv = Qselect("#pointDiv");	                            //设置轮播图中的存放点的DIV点
	var icon = pointDiv.getElementsByTagName("span");
	AppTouch(slider,icon,pointDiv,bodyWH.width,true,3000);

	//给学生店铺设置单击事件
	Qselect("#studentStore").onclick=function(){
		 window.location.href = "store/storeList.html";
	};
	//给二手交易菜单绑定事件
	Qselect("#oldGoods").onclick=function(){
		 window.location.href = "goodsManger/goodsList.html";
		 
		 //向下一个页面提供请求二手交易的信息
	};
	//给失物招领绑定事件
	Qselect("#lostAndFound").onclick=function(){
//		 window.location.href="lostAndFound/lostAndFound.html";
		alert("该功能暂未开通！");
	};
	//给共享交易绑定事件
	Qselect("#shareTrading").onclick=function(){
//		 /window.location.href = "shareTrading/shareTrading.html";
		alert("该功能暂未开通！");
		
	};
	
	
	Qselect("#newGoods").onclick=function(){
		 window.location.href = "goodsManger/goodsList.html";
	};

	Qselect("#hotGoods").onclick=function(){
		 window.location.href = "goodsManger/goodsList.html";
	};
	//给商品添加单击事件
	var goods = Qselect("#goodsScan ul li");
	for(var i=0;i<goods.length;i++){
		goods[i].onclick=scanGoods;
	}
	//给商品添加的单击响应函数
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

