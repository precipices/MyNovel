/**
 * 点击事件
 */

/**
 * 显示章节列表
 * 根据id查询章节列表，并返回到#chapter_list_tbody中
 * @param id
 * @returns
 */
function viewChapterList(bookId){
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
			      +"<td>"+chapterList[i].updated+"</td>"
			      +"<td>"+chapterList[i].created+"</td>"
			      +"<td><button class='btn btn-primary' onclick='viewUpdateContent("+chapterList[i].contentId+",\""+chapterList[i].title+"\")'>查看/修改内容</button></td>"
			      +"</tr>";
			}
			$("#chapter_list_tbody").html(tbody);
			//显示章节列表div
			change($("#chapter_list_div"),null,"章节列表");
			
		},
		error:function(){
			alert("服务器无响应！");
		}
	});
}
/**
 * 查看/修改内容
 * @param contentId 内容id
 * @param title     章节标题
 * @returns
 */
function viewUpdateContent(contentId,title){
	$("#content_id_hidden").val("");
	var url="getContent.do";
	$.ajax({
		type:"POST",
		url:url,
		data:{
			contentId:contentId
		},
		success:function(data){
			$("#content_id_hidden").val(contentId);
			//显示章节编辑div
			change($("#content_edit_div"),null,"章节编辑");
			$("#chapter_title_p").val(title);
		},
		error:function(){
			alert("服务器无响应！");
		}
	});
}
