/**
 * index.html页面的工具集，会使用到页面中的id,提供给后续阶层调用
 */

/**
 * 标题栏切换div
 * @param goal 目标div对象
 * @param e
 * @param str
 * @returns
 */
function change(goal,e,str){
	//显示目标div并隐藏并列div
	goal.show().siblings('div').hide();
	//移除所有选项卡的active模式
	$("#menu_bar_ul").find('a').removeClass("active");
	//为本选项卡添加active
	$(e).addClass("active");
	//为父选项卡添加active
	$(e).parent().siblings('a').addClass("active");
	//将标题设置为本选项卡内文本
	if(e!=null)
		$("#title").text($(e).text());
	else if(str!=null)
 		$("#title").text(str);
}
/**
 * 左侧选项卡切换右侧div
 * @param goal
 * @returns
 */
function changeRight(goal,e,str){
	//显示目标div并隐藏并列div
	goal.show().siblings('div').hide();
	//移除所有选项卡的active模式
	$(e).parent().find('a').removeClass("active");
	//为本选项卡添加active
	$(e).addClass("active");
	//将标题设置为本选项卡内文本
	if(e!=null)
		$("#title").text($(e).text());
	else if(str!=null)
 		$("#title").text(str);

}