/**
 * 页面加载后再执行的js
 */

$(function() {

	var E = window.wangEditor;
	chapterEditor = new E('#update_chapter_editor');
	// 或者 var editor = new E( document.getElementById('editor') )
	chapterEditor.create();
	// $('.mytable td').hover(function(){
	// $(this).title
	// },function(){
	//    	
	// });
	/**
	 * 为表格每个td添加title
	 */
	$('.mytable td').each(function() {
		// alert($(this).text());
		$(this).attr("title", $(this).text());
	});
//	DOMSubtreeModified
	/**
	 * 为表格动态添加的每个td添加title
	 */
	$('.mytable').on('DOMNodeInserted','tr', function() {
		var that=$(this);
//		var temp=0;
		$.each(that.find("td"), function(index, value) {
			var attr = $(value).text();
			$(value).attr("title", attr);
//			temp++;
		});
//		alert(temp);
	});
	

	$("#test").click();
	//进入书籍管理器
//	$("#book_list_btn").click();
	
	//进入第一本书的人物管理器
//	$("#book_list_btn").click();
//	$("#character_manage_change_btn").click();
//	$("#book_list_table_tbody button:first").click();
	//进入事件管理器新增事件
//	$("#event_manage_btn").click();
//	$("#add_event_change_btn").click();
	//进入第一本书的事件管理器
//	$("#book_list_btn").click();
//	$("#event_manage_change_btn").click();
//	$("#book_list_table_tbody button:first").click();
	//测试用
//	$("#character_manage_btn").click();
//	$("#add_character_change_btn").click();
	
	
});