/*获取屏幕宽和高*/
function getWH()
{
	var bodyHeight=screen.availHeight;
	var bodyWeight=screen.availWidth;
	var bodyWH={
		"width":bodyWeight,
		"height":bodyHeight
	}
	return bodyWH;
};
/*
 * 设置元素的高度
 */
function setTargetHeight(selectorName,height)
{
	var target=Qselect(selectorName);
	//若target的长度为1，则只给该元素赋高度值
	if(target.length==1){
		target.style.height=height;
	}
	else{
		for(var i=0;i<target.length;i++){
			target[i].style.height=height;
		}
	}
};
 /*
  * 设置元素的宽度
 */
function setTargetWidth(selectorName,width)
{
	var target=Qselect(selectorName);
	//若target的长度为1，则只给该元素赋高度值
	if(target.length==1){
		target.style.width=width;
	}
	else{
		for(var i=0;i<target.length;i++){
			target[i].style.width=width;
		}
	}
};
/*
 * 元素选择器
 */
function Qselect(name)
{
	var target=document.querySelectorAll(name);
	if(target!==null){
		if(target.length>1){
			return target;
		}
		else{ 
			var target2=target[0];
			target2.length=1;
			return target2;
		} 
	}
	return null;
};
/*创建新元素*/
function createElement(elementName){
	var target=document.createElement(elementName);
	return target;
};
/*触屏滑动事件处理*/
/*
 * slider   轮播图外层ul元素
 * icon     轮播图中标识符元素（list）
 * width    轮播图外层div宽度
 * autoChange  是否开启自动播放
 */
//轮播图索引
function AppTouch(slider,icon,pointDiv,Width,auto,time)
{
	var index=0;              //轮播图标识的索引
	var autoChange;           //自动播放的定时器     
	var isScrolling=0;        //判断是横屏还是竖屏，1代表纵向滑动，0代表横向滑动
	//触屏开始位置和时间
	var startPos={
		x:0,
		y:0,
		time:new Date()
	};
	//触屏结束位置和时间
	var endPos={
		x:0,
		y:0
	};
	var pictureCount = slider.getElementsByTagName("li").length;        //获取图片的数量
	slider.style.width = Width * pictureCount+ "px";            //设置ul的宽度
	/*添加轮播点*/
	for(var i = 0; i < pictureCount; i++) {
		var span = createElement("span");         
		pointDiv.appendChild(span);	
		span.className="defaultPoint";              //设置span内联样式背景为空，CSS样式会起作用
		span.index=i;                              //给span添加index
		if(i == 0){
			span.className="solidPoint";           //默认第一个span为当前所在的图片
		} 
	}
	var pointDivWidth = pointDiv.offsetWidth;    //获取div的宽度，用来设置div居中
	pointDiv.style.marginLeft = (Width - pointDivWidth) / 2 + "px";   //设置轮播图点的位置居中
	//调用touch时间函数
	/*用于绑定触屏事件*/
	function handlers(event){
		if(event.type=='touchstart'){
			start(event);
		}else if(event.type=='touchmove'){
			move(event);
		}else if(event.type=='touchend'){
			end(event);
		}
	};
	function start(event){
		//关闭自动播放
		clearInterval(autoChange);
		//获取第一个触屏点
		var touch=event.targetTouches[0];
		startPos.x=touch.pageX;
		startPos.y=touch.pageY;
		startPos.time=+new Date();
		slider.addEventListener("touchmove",move,false);
		slider.addEventListener("touchend",end,false);
	};
	//触摸滑动事件
	function move(event){
		//当屏幕有多个touch时或者页面被缩放过，则不执行move操作
		if(event.targetTouches.length>1||event.scale&&event.scale!==1){
			return;
		}
		//获取第一个touch事件
		var touch=event.targetTouches[0];
		endPos.x=touch.pageX-startPos.x;
		endPos.y=touch.pageY-startPos.y;
		//判断是横屏还是竖屏，1代表纵向滑动，0代表横向滑动
		isScrolling=Math.abs(endPos.x)<Math.abs(endPos.y)?1:0;
		if(isScrolling==0){
			//阻止触摸事件的滚屏行为
			event.preventDefault();
			//根据滑动距离改变slider的x坐标值
			slider.style.left=-index*Width+endPos.x+"px";
		}
	};
	//触摸结束事件
	function end(event){
		//计算滑动的持续时间
		var duration=+new Date()-startPos.time;
		//若是横向滑动
		if(isScrolling==0){
			//取消设置代表当前页索引的标记
			icon[index].className="defaultPoint";
			//滑动事件大于20ms才处理
			if(Number(duration)>20){
				//判断左移还是右移
				//向左滑动,图片转到下一个
				if(endPos.x<-100){
					if(index!==icon.length-1){
						index+=1;
						slider.className="f-anim";
					}else{
						slider.className="";
						index=0;
					}
				}
				//向右滑动，图片转到上一个
				else if(endPos.x>100){
					if(index!==0){
						index-=1;
						slider.className="f-anim";
					}else{
						slider.className="";
						index=icon.length-1;
					}
				}
			}
			//设置代表当前页面索引的标记样式为被标记样式
			icon[index].className="solidPoint";
			slider.style.left=-index*Width+"px";
			
		}
		//取消绑定事件处理函数
		slider.removeEventListener("touchmove",move,false);
		slider.removeEventListener("touchend",end,false);
		//开启自动播放
		autoChange=setInterval(autoFun,time);
	};
	
	if(('ontouchstart' in window) || window.DocumentTouch && document instanceof DocumentTouch) {
	//addEventListener的第二个参数可以传一个对象，系统会自动调用该对象的handleEvent属性作为事件监听函数
	slider.parentElement.addEventListener('touchstart', handlers, false);	
	}
	//开启自动播放图片功能
	if(auto){
		autoChange=setInterval(autoFun,time);	
	}
	function autoFun(){
		//取消设置代表当前页索引的标记
		icon[index].className="defaultPoint";
		if(index !== icon.length - 1) {
			index += 1;
			slider.className = "f-anim";
		} else {
			slider.className = "";
			index = 0;
		}
		//设置代表当前页面索引的标记样式为被标记样式
		icon[index].className="solidPoint";
		slider.style.left=-index*Width+"px";
	}
	
};