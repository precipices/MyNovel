/**
 * 切换div方法集
 */

/**
 * 切换div并修改页面title并高亮目标及其父选项卡(不改变标题栏的active状态)
 * @param goalId 目标div的id,传入的应为一级div的id
 * @param titleStr 需改变的标题的内容
 * @param thisEle 高亮目标，传入的应该是一个二级菜单的this对象
 * @returns
 */
function changeWithTitleWithActiveTab(goalId,titleStr,thisEle){
	//显示目标div并隐藏并列div
	if(goalId!=null)
		$("#"+goalId).show().siblings('div').hide();
	else{
		alert("ERROR:目标一级div不存在!!");
	}
	//改变标题栏内容
	if(titleStr!=null)
		$("#title").text(titleStr);
	//高亮目标二级菜单及其父一级菜单
	if(thisEle!=null){
		//移除菜单栏所有选项卡的active模式
		$("#menu_bar_ul").find('a').removeClass("active");
		//为本二级菜单添加active
		$(thisEle).addClass("active");
		//为父一级菜单添加active
		$(thisEle).parent().siblings('a').addClass("active");
	}
}
/**
 * 切换div并修改页面title(不改变标题栏的active状态)
 * @param goalId 目标div的id,传入的应为一级div的id
 * @param titleStr 需改变的标题的内容
 * @returns
 */
function changeWithTitle(goalId,titleStr){
	changeWithTitleWithActiveTab(goalId,titleStr,null);
}
/**
 * 切换div并修改页面title(不改变标题栏的active状态)
 * @param goalId 目标div的id,传入的应为一级div的id
 * @returns
 */
function changeDivOnly(goalId){
	changeWithTitleWithActiveTab(goalId,null,null);
}
/**
 * 左侧导航栏模式中切换右侧二级div容器中的三级div;并高亮左侧二级div导航栏中的对应标签
 * @param goalId 目标div的id,传入的应为三级div的id,且其的父一级div使用的是左侧导航栏模式布局
 * @param thisEle 高亮目标，传入的应该是一个左侧二级div导航栏中的a标签的this对象
 * @returns
 */
function changeRightWithActiveTab(goalId,thisEle){
	//显示目标div并隐藏并列div
	if(goalId!=null)
		$("#"+goalId).show().siblings('div').hide();
	else{
		alert("ERROR:目标三级div不存在!!");
	}
	if(thisEle!=null){
		//移除所有并列选项卡的active模式
		$(thisEle).siblings('a').removeClass("active");
		//为本选项卡添加active
		$(thisEle).addClass("active");
	}
}
/**
 * 左侧导航栏模式中切换右侧二级div容器中的三级div;
 * @param goalId 目标div的id,传入的应为三级div的id,且其的父一级div使用的是左侧导航栏模式布局
 * @returns
 */
function changeRight(goalId){
	//显示目标div并隐藏并列div
	if(goalId!=null)
		$("#"+goalId).show().siblings('div').hide();
	else{
		alert("ERROR:目标三级div不存在!!");
	}
}