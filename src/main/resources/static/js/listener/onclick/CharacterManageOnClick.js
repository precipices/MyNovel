/**
 * 人物管理器中的onclick事件
 */
//******************************************人物表单******************************************************************
/**
 * 人物管理器人物表单提交按钮命令flg,0:出错 1：新增人物 2：修改人物信息 3：查看人物信息（不可编辑）（暂时没写）
 */
var chFormFlg=0;
/**
 * 人物表单点击提交按钮后选择要进行的操作（可优化）
 * 
 * @returns
 */
function characterFormSubmitSelectFunc(){
	var bookId=$("#book_id_in_character_manage_hidden").val();
	if(bookId==0){
		alert("尚未选择小说！");
		return;
	}
	if ($("#ch_name_text").val().trim() == "") {
		alert("人物名不能为空！");
		return;
	}
	if ($("#ch_attr_sel").val().trim() == "") {
		alert("人物属性不能为空！");
		return;
	}
	switch(chFormFlg){
	case 1:// 新增人物
		// 沟通服务器，根据表单信息新增人物
		insertCharacter();
		// 刷新人物列表
		refreshCharacterListByBookId(bookId);
		// 跳转div
		$("#character_list_change_btn").click();
		//修改命令flg为3（查看信息）
		chCmdFlg=3;
		break;
	case 2:// 修改人物信息
		// 沟通服务器，根据表单信息修改人物信息
		updateCharacter();
		// 刷新人物列表
		refreshCharacterListByBookId(bookId);
		// 跳转div
		$("#character_list_change_btn").click();
		//修改命令flg为3（查看信息）
		chCmdFlg=3;
		break;
	case 3:// 查看人物信息
		// 按钮应为不可点击状态，不会执行本代码
		alert("不可提交！");
		break;
	default:
		alert("未知错误！");
	}
}
/**
 * 将命令flg改为传入参数值，并根据命令flg初始化人物表单
 * 
 * @param flg
 * @returns
 */
function characterFormInitByFlg(flg,chId){
	chFormFlg=flg;
	switch(chFormFlg){
	case 1:// 新增人物
		// 将【人物表单】置为新增模式(即所有值为空，隐藏id为0)
		// 隐藏id置零
		$("#ch_id_text").val(0);
		// 修改标题
		$("#update_character_title").text("新增人物");
		// 隐藏【首次出场章节】和【出场次数】
		$("#update_character_form_hidden_item").hide();
		// 其他文本框框切换可修改模式
		$(".chChange").attr("disabled",false);
		// 所有文本框置初值
		$("#ch_id_text").val(0);
		$("#ch_name_text").val("");
		$("#ch_attr_sel").val("配角");
		$("#ch_first_appear_text").val("");
		$("#ch_appear_times_text").val(0);
		$("#ch_belong_text").val("");
		$("#ch_intro_text").val("");
		// 显示提交按钮
		$("#character_form_submit_btn").show();
		break;
	case 2:// 修改人物信息
		// 将【人物表单】置为修改模式(即所有输入值为人物当前信息，可修改，隐藏id为人物id)
		// 沟通服务器获取人物信息,根据人物id更新人物表单信息
		refreshCharacterFormByChId(chId);
		// 显示文本框并设为可读写
		// 修改标题
		$("#update_character_title").text("修改人物信息");
		// 隐藏【首次出场章节】和【出场次数】
		$("#update_character_form_hidden_item").hide();
		// 其他输入框切换可修改模式
		$(".chChange").attr("disabled",false);
		// 显示提交按钮
		$("#character_form_submit_btn").show();
		break;
	case 3:// 查看人物信息
		// 将【人物表单】置为查看模式(即所有输入值为人物当前信息，不可修改，隐藏id为人物id)
		// 沟通服务器获取人物信息,根据人物id更新人物表单信息
		refreshCharacterFormByChId(chId);
		// 显示所有文本框并设为只读
		// 修改标题
		$("#update_character_title").text("查看人物信息");
		// 显示【首次出场章节】和【出场次数】
		$("#update_character_form_hidden_item").show();
		// 其他输入框切换只读模式
		$(".chChange").attr("disabled",true);
		// 隐藏提交按钮
		$("#character_form_submit_btn").hide();
		break;
	default:
		alert("未知错误！");
	}
}
/**
 * 将【新增/更新人物表单】置为新增模式(另有修改模式，查看模式);该方法已过期！！！
 * 
 * @returns
 */
function changeCreateCharacterForm(){
	// 如果隐藏id为0，说明表单未改动，无需再次重置
	var chId=$("#ch_id_text").val();
	if(chId==0)
		return;
	// 隐藏id置零
	$("#ch_id_text").val(0);
	// 修改标题
	$("#update_character_title").text("新增人物");
	// 隐藏【首次出场章节】和【出场次数】
	$("#update_character_form_hidden_item").hide();
	// 其他输入框切换可修改模式
	$("#chChange").attr("disable",true);
}


// ******************************************按钮功能切换******************************************************************
/**
 * 人物管理器左侧选项卡命令flg,0:出错 1：删除人物 2：修改人物信息 3：查看人物信息（不可编辑）（暂时没写） 4：查看人物参与事件 5：新增人物
 */
var chCmdFlg=3;
/**
 * 修改命令flg，修改命令按钮的名字，高亮导航选项
 * 
 * @param thisEle
 * @param flag
 * @param str
 * @returns
 */
function changeCharacterFlgAndBtnNameWithActive(thisEle,flag){
	// 修改命令flg
	chCmdFlg=flag;
	var btnName="";
	switch(chCmdFlg){
	case 0:
		btnName="空";
		break;
	case 1:
		btnName="删除人物";
		break;
	case 2:
		btnName="修改信息";
		break;
	case 3:
		btnName="查看信息";
		break;
	case 4:
		btnName="查看人物参与事件";
		break;
	case 5:// 新增人物
		// 将【人物表单】置为新增模式
		characterFormInitByFlg(1,null);
		// 切换到新增人物表单div,高亮导航选项
		changeRightWithActiveTab('update_character_form_right_div',thisEle);
		return;
	default:
	}
	// 修改按钮名称
	$(".chSelFunc").text(btnName);
	// 切换到人物列表div,高亮导航选项
	changeRightWithActiveTab('character_list_rignt_div',thisEle);
}


/**
 * 命令按钮根据命令flg执行功能
 * 
 * @returns
 */
function execFuncByChFlg(chId,thisEle){
	switch(chCmdFlg){
	case 0:break;
	case 1:// 删除人物
		// 给出警告
		if(!confirm("确认删除吗？删除后不可恢复！！！")){
			return;
		}
		// 确认删除
		deleteCharacterByChId(chId,thisEle);
		break;
	case 2:// 修改信息
		// 将【人物表单】置为修改模式
		characterFormInitByFlg(2,chId);
		// 切换到新增人物表单div
		changeRight('update_character_form_right_div');
		break;
	case 3:// 查看信息
		// 将【人物表单】置为查看模式
		characterFormInitByFlg(3,chId);
		// 切换到新增人物表单div
		changeRight('update_character_form_right_div');
		break;
	case 4:// 查看人物参与事件
		viewAndRefreshChList(chId);
		break;
	default:
	}
}