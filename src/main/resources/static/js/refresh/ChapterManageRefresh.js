/**
 * 章节管理器中的refresh;
 * 更新表格或数据的方法,允许调用MyUtil和golbalVar;仅更新，不会跳转或触发点击事件;
 */
//************************章节列表*******************************************
/**
 * 根据小说id更新章节列表
 * @param bookId
 * @returns
 */
function refreshChapterListByBookId(bookId){
	//先清空表格
	$("#chapter_list_table_tbody").html("");
	//沟通服务器取数据并返回到表格中
	var url="getChapterList.do";
	$.ajax({
		type:"POST",
		url:url,
		data:{
			bookId:bookId
		},
		success:function(data){
//			alert(JSON.stringify(data));
			var chapterList=data;
			//这里没有判断data是否获取成功
			var tbody="";
			for(var i=0;i<chapterList.length;i++){
				tbody+="<tr>"
				  +"<td>"+chapterList[i].title+"</td>"
			      +"<td>"+chapterList[i].words+"</td>"
			      +"<td>"+chapterList[i].created+"</td>"
			      +"<td>"+chapterList[i].updated+"</td>"
			      +"<td>"
			      +"<button class='btn btn-primary smFont' "
			      +"onclick='viewUpdateContent("+chapterList[i].contentId+",\""+chapterList[i].title+"\","+chapterList[i].id+")'"
			      +">查看/修改内容</button>"
			      +"&nbsp;<button class='btn btn-primary smFont' "
			      +"onclick='deleteChapter("+chapterList[i].id+",this)'"
			      +">删除</button>"
			      +"</td>"
			      +"</tr>";
			}
			$("#chapter_list_table_tbody").html(tbody);
			
		},
		error:function(){
			alert("服务器无响应！");
		}
	});
}
/**
 * 新增章节后更新章节列表***************************************************待修改
 * @param contentId
 * @returns
 */
function refreshListCreateChapterByContentId(contentId){
	//更新章节内容
	refreshChapterListByBookId(usingBookId);
}
/**
 * 更新章节后更新章节列表***************************************************待修改
 * @param contentId
 * @returns
 */
function refreshListUpdateChapterByContentId(contentId){
	//更新章节内容
	refreshChapterListByBookId(usingBookId);
}
//************************章节内容编辑器*******************************************
/**
 * 将更新章节内容表单初始化为空（该操作也会将全局变量表里的内容id初始化为null）
 * @returns
 */
function refreshUpdateChapterFormToNull(){
	//修改隐藏内容id为空
	usingContentId=null;
	//修改隐藏章节内容为空
	usingContent=null;
	//修改章节标题为空
	$("#update_chapter_title").val("");
	//修改编辑器内容为空
	chapterEditor.txt.html("<p>章节内容</p>");
}

/**
 * 根据内容id更新章节内容到编辑器中
 * @param contentId
 * @returns
 */
function refreshChapterEditorByContentId(contentId){
	//沟通服务器，根据内容id获取章节内容并传入编辑器中
	var url="getContent.do";
	$.ajax({
		type:"POST",
		url:url,
		data:{
			contentId:contentId
		},
		success:function(data){
			//修改编辑器内容
			chapterEditor.txt.html(data);
			usingContent=data;
		},
		error:function(){
			alert("服务器无响应！");
		}
	});
}