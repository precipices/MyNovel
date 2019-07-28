/**
 * 书籍管理器中的onclick事件
 */
//******************************************删除书籍******************************************************************
/**
 * 删除小说
 * 根据id删除小说,成功则删除列表对应行
 * @param bookId
 * @returns
 */
function deleteBook(bookId,e){
	if(!confirm("确认删除吗？删除后不可恢复！！！")){
		return;
	}
	var url="deleteBook.do";
	$.ajax({
		type:"POST",
		url:url,
		data:{
			bookId:bookId
		},
		success:function(data){
			if(data==1){
				alert("删除成功！");
				$(e).parent().parent().remove();
			}else{
				alert("删除失败："+data);
			}
		},
		error:function(){
			alert("服务器无响应！");
		}
	});
}
//******************************************新增/更新书籍的表单******************************************************************
/**
 * 根据隐藏的bookid判断下一步操作是新增还是更新小说，并调用对应方法
 * 
 * @returns
 */
function createUpdateCheck() {
	var bookId = $("#book_id_text").val();
	if (bookId == 0) {
		createBook();
	} else {
		updateBook();
	}
}
/**
 * 表单格式检查
 * 
 * @returns
 */
function updateBookFormCheck() {
	var bookName = $("#book_name_text").val();
	var bookType = $("#book_type_text").val();
	// var bookTags=$("#book_tags_text").val();
	var bookIntro = $("#book_intro_text").val();
	// ------+----小说名
	if (bookName == null || bookName.trim() == "") {
		alert("小说名不能为空！");
		return false;
	}
	// ------+----小说类型
	if (bookType == null || bookType.trim() == "") {
		alert("小说类型不能为空！");
		return false;
	}
	// ------+----小说标签
	// if(bookTags==null||bookTags.trim()==""){
	// alert("小说标签不能为空！");
	// return;
	// }
	// ------+----小说简介
	if (bookIntro == null || bookIntro.trim() == "") {
		alert("小说简介不能为空！");
		return false;
	}
	return true;
}
/**
 * 修改新增/更新小说表单信息并跳转到更新小说div
 * 
 * @returns
 */
function openUpdateBookFrom(bookId) {
	// 更新【新增/更新小说表单】信息
	refreshUpdateBookFormByBookId(bookId);
	// 修改title信息
	$("#update_book_title").text("修改小说信息");
	// 切换div
	changeRight("update_book_form_right_div");
}

/**
 * 跳转到新增小说div
 * 
 * @param thisEle
 *            高亮目标，传入的应该是一个左侧二级div导航栏中的a标签的this对象
 * @returns
 */
function openCreateBookFrom(thisEle) {
	// 初始化【新增/更新小说表单】信息
	refreshCreateBookFormToNull();
	// 修改title信息
	$("#update_book_title").text("新增小说");
	// 切换div
	changeRightWithActiveTab("update_book_form_right_div", thisEle);
}
/**
 * 新增小说，提交表单数据并返回新增小说的id;更新小说列表
 * 
 * @returns
 */
function createBook() {
	var url = "createBook.do";
	// 当小说id不为零时，初始化为零
	var bookId = $("#book_id_text").val();
	if (bookId != 0)
		$("#book_id_text").val(0);
	// 表单格式检查
	if (!updateBookFormCheck()) {
		return;
	}
	if(!confirm("确认创建该小说吗？")){
		return;
	}
	// 沟通服务器，新增小说并刷新列表
	$.ajax({
		type : "POST",
		url : url,
		// dataType:"json",
		data : $("#update_book_form").serialize(),
		success : function(data) {
			if (data > 0) {
				// 修改表单隐藏bookId值
				$("#book_id_text").val(data);
				// 更新小说列表tbody
				refreshBookList();
				// 跳转到小说列表div
				$("#book_list_change_btn").click();
			} else {
				alert("失败：" + data);
			}
		},
		error : function() {
			alert("服务器无响应！");
		}
	});
}
/**
 * 更新小说信息
 * 
 * @returns
 */
function updateBook() {
	if(!confirm("确认修改该小说信息吗？修改后不可恢复！！！")){
		return;
	}
	var url = "updateBook.do";
	// 表单格式检查
	if (!updateBookFormCheck()) {
		return;
	}
	$.ajax({
		url : url,
		type : "POST",
		data : $("#update_book_form").serialize(),
		success : function(data) {
			if (data > 0) {
				// 更新小说列表信息
				refreshBookList();
				// 切换到小说列表div
				$("#book_list_change_btn").click();
			} else {
				alert("更新失败：" + data);
			}
		},
		error : function() {
			alert("服务器无响应！");
		}
	});
}
//******************************************按钮功能切换******************************************************************
///**
// * 书籍管理器命令flg,0:出错 1：删除小说 2：修改小说信息 3：查看小说信息（不可编辑）（暂时没写） 4：查看章节列表
// */
//var commandFlg=4;
/**
 * 修改命令flg，修改命令按钮的名字，高亮导航选项
 * @param thisEle
 * @param flag
 * @param str
 * @returns
 */
function changeFlgAndBtnNameWithActive(thisEle,flag){
	//修改命令flg
	commandFlg=flag;
	var btnName="";
	switch(commandFlg){
	case 0:
		btnName="空";
		break;
	case 1:
		btnName="删除小说";
		break;
	case 2:
		btnName="修改信息";
		break;
	case 3:
		btnName="查看信息";
		break;
	case 4:
		btnName="章节管理";
		break;
	case 5:
		btnName="人物管理";
		break;
	case 6:
		btnName="事件管理";
		break;
	default:
	}
	//修改按钮名称
	$(".selectFunc").text(btnName);
	// 切换到书籍列表div,高亮导航选项
	changeRightWithActiveTab('book_list_rignt_div',thisEle);
}
/**
 * 命令按钮根据命令flg执行功能
 * @returns
 */
function execFuncByFlg(bookId,thisEle){
	switch(commandFlg){
	case 0:break;
	case 1://删除书籍
		deleteBook(bookId,thisEle);
		break;
	case 2://修改小说信息
		openUpdateBookFrom(bookId);
		break;
	case 3://
		break;
	case 4://章节管理
		viewAndRefreshChapterList(bookId);
		break;
	case 5://人物管理
		//沟通服务器获取数据
		refreshCharacterListByBookId(bookId);
		//切换div
		$("#character_manage_btn").click();
		break;
	case 6:
		//沟通服务器获取数据
		refreshEventListByBookId(bookId);
		//切换div
		$("#event_manage_btn").click();
		break;
	case 7:break;
	case 8:break;
	default:
	}
}







function btnDeleteBookChange(thisEle){
	//修改按钮名称
	$(".selectFunc").text("删除本书");
	//修改按钮功能
	
	// 切换到书籍列表div
	changeRightWithActiveTab('book_list_rignt_div',thisEle);
}







