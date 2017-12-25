window.onload = function() {
	var bodyWH = getWH();
	setTargetWidth(".storeList", bodyWH.width + "px");
	setTargetWidth(".goodsContent", bodyWH.width - 40 + "px");
	setTargetWidth(".goodsTitle", bodyWH.width - 170 + "px");
	
	//获取页面总价的标签
	var sumMoney=Qselect("#countMoney span");
	var sumPrice=0;
	//为选择绑定单击事件
	var selectedAll=Qselect("input[name=menuSelected]");//全选
	var storeSelects=Qselect("input[name=storeSelect]");//所有商店
	var goodsSelects=Qselect("input[name=goodsSelect]");//所有商品
	var allInput=Qselect("input");
	//为全选按钮绑定单击响应函数
	selectedAll.onclick=function(event){
		var target=event.target;
		var img=target.previousSibling;
		if(target.checked){
			img.src="../img/trolley/selected.png";
			for(var i=0;i<allInput.length;i++){
				allInput[i].checked="checked";
				allInput[i].previousSibling.src=img.src;
			}
		}
		else{
			img.src = "../img/trolley/unselect.png";
			for(var i=0;i<allInput.length;i++){
				allInput[i].checked="";
				allInput[i].previousSibling.src=img.src;
			}
		}
		isAllgoodsSelect();
		countMoney();
	};
	//为商店按钮绑定单击响应函数
	for(var j=0;j<storeSelects.length;j++){
		storeSelects[j].onclick=function(event){
			var target=event.target;
			var img=target.previousSibling;
			var store =target.parentNode.parentNode.parentNode;
			var goods=store.getElementsByClassName("goods");
			for(var i=0;i<goods.length;i++){
				var goodsInput=goods[i].getElementsByTagName("input")[0];
				if(target.checked){
					img.src="../img/trolley/selected.png";
					goodsInput.checked=target.checked;
					goodsInput.previousSibling.src=img.src;
				}
				else{
					img.src = "../img/trolley/unselect.png";
					goodsInput.checked=target.checked;
					goodsInput.previousSibling.src=img.src;
				}
			}
			isAllgoodsSelect();
			countMoney();
		};
	}
	//为商品选择框绑定事件
	for(var i=0;i<goodsSelects.length;i++){
		goodsSelects[i].onclick=function(event){
			target=event.target;
			//获取商品对应的商店
			var store =target.parentNode.parentNode.parentNode.parentNode;
			//获取商品对应的商店的选择框
			var storeInputs=store.getElementsByTagName("input");
			var storeInput=storeInputs[0];
			if(target.checked){
				target.previousSibling.src="../img/trolley/selected.png";
			}else{
				target.previousSibling.src="../img/trolley/unselect.png";
			}
			//检测本商店里的物品是否被全选
			var j=1;
			while(j<storeInputs.length){
				if(!storeInputs[j].checked){
					break;
				}
				else{
					j++;
				}
			}
			if(j<storeInputs.length){
				storeInput.checked="";
				storeInput.previousSibling.src="../img/trolley/unselect.png";
			}else{
				storeInput.checked=true;
				storeInput.previousSibling.src="../img/trolley/selected.png";
			}
			
			//检测所有商品是否被选
			isAllgoodsSelect();
			countMoney();
		};
	}
	//判断当前商品是否已经被全选
	function isAllgoodsSelect(){
		var k=0;
		while(k < goodsSelects.length) {
			if(goodsSelects[k].checked) {
				k++;	
			} else {
				break;
			}
		}
		if(k < goodsSelects.length) {
			selectedAll.checked = "";
			selectedAll.previousSibling.src = "../img/trolley/unselect.png";
		} else {
			selectedAll.checked = true;
			selectedAll.previousSibling.src = "../img/trolley/selected.png";
		}
	};
	
	//计算金额
	function countMoney(){
		sumMoney.innerHTML=0;
		sumPrice=0;
		for(var k=0;k < goodsSelects.length;k++){
			if(goodsSelects[k].checked){
				//获取商品的价格
				var goods =goodsSelects[k].parentNode.parentNode;
				var priceNode=goods.getElementsByClassName("goodsPrice")[0].getElementsByTagName("span")[0];
				var price=Number.parseFloat(priceNode.innerHTML);
				//获取商品的数量
				var goodsCountNode=goods.getElementsByClassName("counts")[0].getElementsByTagName("span")[0]
				var goodsCount=Number.parseFloat(goodsCountNode.innerHTML);
				//计算金额
				var sumPrice=sumPrice+(price*goodsCount);
				sumMoney.innerHTML=sumPrice+"";
			} 
		}
	};
	
	
	
	
	
};