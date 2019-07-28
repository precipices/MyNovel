/**
 * 章节管理器中的onclick事件
 */

//************************章节列表*******************************************
/**
 * 显示章节列表 根据小说id查询章节列表，并返回到#chapter_list_table_tbody中
 * ！！：调用了异步的ajax后切换div,可能会有div已切换但数据还没到的问题
 * 
 * @param bookId
 * @returns
 */
function viewAndRefreshChapterList(bookId) {
	if (bookId == usingBookId) {
		// 如果章节列表并未更新显示章节列表div
		changeWithTitle("chapter_manage_content_div", "章节列表");
		return;
	}
	usingBookId = bookId;
	refreshChapterListByBookId(bookId);
	// 显示章节列表div
	$("#chapter_manage_btn").click();
	// changeWithTitle("chapter_manage_content_div", "章节列表");
}

function deleteChapter(chapterId, thisEle) {
	if (!confirm("确认删除章节？删除后内容不可恢复！！！"))
		return;
	var url = "deleteChapter.do";
	$.ajax({
		url : url,
		type : "POST",
		data : {
			chapterId : chapterId
		},
		success : function(data) {
			alert("删除成功");
			$(thisEle).parent().parent().remove();
		},
		error : function() {
			alert("服务器无响应！");
		}
	});
}
// ************************章节内容编辑器*******************************************
/**
 * 打开新增章节div
 * 
 * @param thisEle
 * @returns
 */
function openCreateChapterFrom(thisEle) {
	// 如果全局内容id不为空，初始化更新章节表单
	if (usingContentId != null)
		refreshUpdateChapterFormToNull();
	// 切换div
	changeRightWithActiveTab("update_chapter_right_div", thisEle);
}

/**
 * 查看/修改章节内容
 * 
 * @param contentId
 *            内容id
 * @param title
 *            章节标题
 * @returns
 */
function viewUpdateContent(contentId, title, chapterId) {
	if (contentId == usingContentId) {
		// 显示【新增/更新/查看章节右侧div】,三级div
		changeRight("update_chapter_right_div");
		return;
	}
	refreshUpdateChapterFormToNull();
	// 修改隐藏内容id
	usingContentId = contentId;
	usingChapterId = chapterId;
	// 修改章节标题
	$("#update_chapter_title").val(title);
	// 显示【新增/更新/查看章节右侧div】,三级div
	changeRight("update_chapter_right_div");
	// 沟通服务器，根据内容id获取章节内容并传入编辑器中
	refreshChapterEditorByContentId(contentId);
}

/**
 * 根据全局文件中的usingContentId判断下一步操作是新增还是更新章节，并调用对应方法
 * 
 * @returns
 */
function createUpdateChapterCheck() {
	if (usingContentId == null || usingContentId == 0) {
		createChapter();
	} else {
		updateChapter();
	}
}

function contentFormCheck() {
	var title = $("#update_chapter_title").val();
	var bookId = usingBookId;
	var content = chapterEditor.txt.text();
	if (title == null || $.trim(title) == "") {
		alert("章节标题不能为空！");
		return false;
	} else if (bookId == null || bookId < 1) {
		alert("未选择小说！");
		return false;
		// 这里并没有成功去除空格，因为空格变成了&nbsp;
	} else if (content == null || $.trim(content) == ""
			|| $.trim(content) == "章节内容") {
		alert("章节内容不能为空！");
		return false;
	}
	return true;
}
/**
 * 新增章节
 * 
 * @returns
 */
function createChapter() {
	// int bookId, String title, int words, String content
	var url = "createChapter.do";
	if (!contentFormCheck()) {
		return;
	}
	$.ajax({
		url : url,
		type : "POST",
		data : {
			bookId : usingBookId,
			title : $("#update_chapter_title").val(),
			words : parseInt(getWordNum(chapterEditor.txt.text())),
			content : chapterEditor.txt.html()
		},
		success : function(data) {
			alert("新增章节成功：" + data);
			// 更新章节列表（***********************************************************待优化）
			refreshListCreateChapterByContentId(0);
			// 切换div
			$("#chapter_list_change_btn").click();
		},
		error : function() {
			alert("服务器无响应！");
		}
	});
}
/**
 * 更新章节内容
 * 
 * @returns
 */
function updateChapter() {
	// 如果内容相同，无需沟通服务器更新
	// if (usingContent == chapterEditor.txt.html()) {
	// alert("章节内容未修改，无需沟通服务器更新!");
	// return;
	// }
	// alert("test:章节内容不同，需沟通服务器更新");
	// 弹出警告
	if (!confirm("确认修改该章节内容吗？修改后不可恢复！！！")) {
		return;
	}
	// 沟通服务器更新章节内容
	var url = "updateContent.do";
	$.ajax({
		url : url,
		type : "POST",
		data : {
			chapterId : usingChapterId,
			contentId : usingContentId,
			title : $("#update_chapter_title").val(),
			content : chapterEditor.txt.html(),
			words : parseInt(getWordNum(chapterEditor.txt.text()))
		},
		success : function(data) {
			if (data == 1) {
				alert("更新成功！");
				// 更新章节编辑器中内容
				refreshChapterEditorByContentId(usingContentId);
				// 更新章节列表（***********************************************************待优化）
				refreshListUpdateChapterByContentId(usingContentId);
				// 切换div
				$("#chapter_list_change_btn").click();
			} else {
				alert("更新失败：" + data);
			}
		},
		error : function() {
			alert("服务器无响应！");
		}
	});
}