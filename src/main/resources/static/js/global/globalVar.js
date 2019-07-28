/**
 * 全局变量集 不允许调用到其他js里的变量或方法，不存在前置js
 */
/**
 * 正在使用的小说id,用于判断正在浏览哪本小说，再次读取该本小说相关的数据不用重新从数据库获取
 */
var usingBookId = null;
/**
 * 正在使用的章节id,用于判断正在查看和修改哪个章节
 */
var usingChapterId = null;
/**
 * 正在使用的内容id,用于判断正在查看和修改哪个章节，再次读取该章节内容不用冲数据库重新获取
 */
var usingContentId = null;
/**
 * 正在使用的content(章节内容)，用于跟更新后的章节内容进行对比，相同则无需沟通服务器上传
 */
var usingContent = null;
/**
 * 章节编辑器，用于查看编辑更新章节内容，在index页面加载完毕后被创建
 */
var chapterEditor = null;
/**
 * 书籍管理器命令flg,0:出错 1：删除小说 2：修改小说信息 3：查看小说信息（不可编辑）（暂时没写） 4：查看章节列表
 */
var commandFlg=4;
var Ids = {
	// +全局标题div
	title_div : "title_div",
	// +--全局标题
	title : "title",
	// +全局容器div
	container_div : "container_div",
	// +--菜单栏ul
	menu_bar_ul : "menu_bar_ul",
	// +--菜单栏下侧div容器,0级div
	below_container_div : "below_container_div",
	/**
	 * 书籍列表,一级div-----------------------------------------------------------------------
	 */
	// +书籍列表容器div,一级div
	book_list_content_div : "book_list_content_div",
	// +-+书籍列表左侧容器div,二级div
	book_list_left_content_div : "book_list_left_content_div",
	// --+--书籍列表导航按钮
	book_list_change_btn : "book_list_change_btn",
	// --+--新增/更新小说表单导航按钮
	update_book_form_change_btn : "update_book_form_change_btn",
	// +-+书籍列表右侧容器div,二级div
	book_list_right_content_div : "book_list_right_content_div",
	// --+-+新增/更新小说表单右侧div,三级div
	update_book_form_right_div : "update_book_form_right_div",
	// ----+--新增/更新小说标题
	update_book_title : "update_book_title",
	// ----+-+新增/更新小说表单
	update_book_form : "update_book_form",
	// ------+----小说id
	book_id_text : "book_id_text",
	// ------+----小说名
	book_name_text : "book_name_text",
	// ------+----小说类型
	book_type_text : "book_type_text",
	// ------+----小说标签
	book_tags_text : "book_tags_text",
	// ------+----小说简介
	book_intro_text : "book_intro_text",
	// ------+----表单提交按钮
	update_book_form_submit_btn : "update_book_form_submit_btn",
	// --+-+书籍列表右侧div,三级div
	book_list_rignt_div : "book_list_rignt_div",
	// ----+-+书籍列表表格
	book_list_table : "book_list_table",
	// ------+--书籍列表表格thead
	book_list_table_thead : "book_list_table_thead",
	// ------+--书籍列表表格tbody
	book_list_table_tbody : "book_list_table_tbody",
	/**
	 * 章节管理器,一级div-------------------------------------------------------------------------------
	 */
	// +章节管理器容器div,一级div
	chapter_manage_content_div : "chapter_manage_content_div",
	// +-+章节管理左侧容器div,二级div
	chapter_manage_left_content_div : "chapter_manage_left_content_div",
	// +-+--章节列表导航按钮,三级a
	chapter_list_change_btn : "chapter_list_change_btn",
	// +-+--新增/更新章节表单导航按钮,三级a
	update_chapter_editor_change_btn : "update_chapter_editor_change_btn",
	// +-+章节管理右侧容器div,二级div
	chapter_manage_right_content_div : "chapter_manage_right_content_div",
	// --+-+章节列表右侧div,三级div
	chapter_list_rignt_div : "chapter_list_rignt_div",
	// ----+-+章节列表表格
	chapter_list_table : "chapter_list_table",
	// ------+--章节列表表格thead
	chapter_list_table_thead : "chapter_list_table_thead",
	// ------+--章节列表表格tbody
	chapter_list_table_tbody : "chapter_list_table_tbody",
	/**
	 * 人物管理器，一级div
	 */
	//
	people_manage_content_div : "people_manage_content_div",
}

var Statics = {
	/**
	 * 未知错误
	 */
	FAILED : -1,
	/**
	 * 请求成功
	 */
	SUCCESS : 1,
	/**
	 * 用户名或密码不正确
	 */
	PASS_USER_ERR : 2,

	/**
	 * 用户名或密码为空
	 */
	PASS_USER_NULL : 3,
	/**
	 * 用户名不规范
	 */
	USERNAME_NOT_STANDARD : 4,

	/**
	 * 密码复杂度不够
	 */
	PASS_NOT_ENOUGH : 5,
	/**
	 * 用户名已被注册
	 */
	USERNAME_EXISTS : 6,

	/**
	 * 书不存在
	 */
	BOOK_NOT_EXISTS : 7,

}