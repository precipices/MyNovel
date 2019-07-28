/**
 * 事件管理器中的onclick事件
 */
//******************************************事件表单******************************************************************
/**
 * 事件管理器事件表单提交按钮命令flg,0:出错 1：新增事件 2：修改事件信息 3：查看事件信息（不可编辑）（暂时没写）
 */
var evFormFlg=0;
/**
 * 事件表单点击提交按钮后选择要进行的操作（可优化）
 * 
 * @returns
 */
function eventFormSubmitSelectFunc(){
	var bookId=$("#book_id_in_event_manage_hidden").val();
	if(bookId==0){
		alert("尚未选择小说！");
		return;
	}
	if ($("#ev_name_text").val().trim() == "") {
		alert("事件名不能为空！");
		return;
	}
	switch(evFormFlg){
	case 1:// 新增事件
		// 沟通服务器，根据表单信息新增事件(同步方式)
		insertEvent();
		// 刷新事件列表
		refreshEventListByBookId(bookId);
		// 跳转div
		$("#event_list_change_btn").click();
		//修改命令flg为3（查看信息）
		evCmdFlg=3;
		break;
	case 2:// 修改事件信息
		// 沟通服务器，根据表单信息修改事件信息(同步方式)
		updateEvent();
		// 刷新事件列表
		refreshEventListByBookId(bookId);
		// 跳转div
		$("#event_list_change_btn").click();
		//修改命令flg为3（查看信息）
		evCmdFlg=3;
		break;
	case 3:// 查看事件信息
		// 按钮应为不可点击状态，不会执行本代码
		alert("不可提交！");
		break;
	default:
		alert("未知错误！");
	}
}
/**
 * 将命令flg改为传入参数值，并根据命令flg初始化事件表单
 * 
 * @param flg
 * @returns
 */
function eventFormInitByFlg(flg,evId){
	evFormFlg=flg;
	switch(evFormFlg){
	case 1:// 新增事件
		// 将【事件表单】置为新增模式(即所有值为空，隐藏id为0)
		// 隐藏id置零
		$("#ev_id_text").val(0);
		// 修改标题
		$("#update_event_title").text("新增事件");
		// 其他文本框框切换可修改模式
//		$("#update_event_form_right_div").find(".evChange").attr("disabled",false);
		$(".evChange").attr("disabled",false);
		// 所有文本框置初值
		$("#ev_name_text").val("");
		$("#ev_intro_text").val("");
		$("#ev_origin_text").val("");
		$("#ev_result_text").val("");
		//清空人物列表
		$("#character_list_in_event_form_table_tbody").html("");
		// 显示提交、添加、删除按钮
		$("#ev_ch_add_btn").show();
		$("#event_form_submit_btn").show();
		break;
	case 2:// 修改事件信息
		// 将【事件表单】置为修改模式(即所有输入值为事件当前信息，可修改，隐藏id为事件id)
		// 沟通服务器获取事件信息,根据事件id更新事件表单信息
		refreshEventFormByEvId(evId);
		// 显示文本框并设为可读写
		// 修改标题
		$("#update_event_title").text("修改事件信息");
		// 输入框切换可修改模式
		$(".evChange").attr("disabled",false);
		// 显示提交、添加、删除按钮
		$("#ev_ch_add_btn").show();
		$("#event_form_submit_btn").show();
		break;
	case 3:// 查看事件信息
		// 将【事件表单】置为查看模式(即所有输入值为事件当前信息，不可修改，隐藏id为事件id)
		// 沟通服务器获取事件信息,根据事件id更新事件表单信息
		refreshEventFormByEvId(evId);
		// 显示所有文本框并设为只读
		// 修改标题
		$("#update_event_title").text("查看事件信息");
		// 输入框切换只读模式
//		$("#update_event_form_right_div").find(".evChange").attr("disabled",false);

//		document.getElementsByClassName("evChange").disabled=true;
		$(".evChange").attr("disabled",true);
		// 隐藏提交按钮
		$("#ev_ch_add_btn").hide();
		$("#event_form_submit_btn").hide();
		break;
	default:
		alert("未知错误！");
	}
}


// ******************************************按钮功能切换******************************************************************
/**
 * 事件管理器左侧选项卡命令flg,0:出错 1：删除事件 2：修改事件信息 3：查看事件信息 4：查看事件参与人物 5：新增事件
 */
var evCmdFlg=3;
/**
 * 修改命令flg，修改命令按钮的名字，高亮导航选项
 * 
 * @param thisEle
 * @param flag
 * @param str
 * @returns
 */
function changeEventFlgAndBtnNameWithActive(thisEle,flag){
	// 修改命令flg
	evCmdFlg=flag;
	var btnName="";
	switch(evCmdFlg){
	case 0:
		btnName="空";
		break;
	case 1:
		btnName="删除事件";
		break;
	case 2:
		btnName="修改信息";
		break;
	case 3:
		btnName="查看信息";
		break;
	case 5:// 新增事件
		// 将【事件表单】置为新增模式
		eventFormInitByFlg(1,null);
		// 切换到新增事件表单div,高亮导航选项
		changeRightWithActiveTab('update_event_form_right_div',thisEle);
		return;
	default:
	}
	// 修改按钮名称
	$(".evSelFunc").text(btnName);
	// 切换到事件列表div,高亮导航选项
	changeRightWithActiveTab('event_list_rignt_div',thisEle);
}


/**
 * 命令按钮根据命令flg执行功能
 * 
 * @returns
 */
function execFuncByEvFlg(evId,thisEle){
	switch(evCmdFlg){
	case 0:break;
	case 1:// 删除事件
		// 给出警告
		if(!confirm("确认删除吗？删除后不可恢复！！！")){
			return;
		}
		// 确认删除
		deleteEventByEvId(evId,thisEle);
		break;
	case 2:// 修改信息
		// 将【事件表单】置为修改模式
		eventFormInitByFlg(2,evId);
		// 切换到新增事件表单div
		changeRight('update_event_form_right_div');
		break;
	case 3:// 查看信息
		// 将【事件表单】置为查看模式
		eventFormInitByFlg(3,evId);
		// 切换到新增事件表单div
		changeRight('update_event_form_right_div');
		break;
	default:
	}
}