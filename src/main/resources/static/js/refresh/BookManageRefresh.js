/**
 * 书籍管理器中的refresh;
 * 更新表格或数据的方法,允许调用MyUtil和golbalVar;仅更新，不会跳转或触发点击事件;
 */

/**
 * 更新小说列表
 */
function refreshBookList(){
	var url="getBookList.do";
	$.ajax({
		url:url,
		type:"POST",
		data:{},
		success:function(data){
//			alert(JSON.stringify(data));
			var bookList=data;
			//这里没有判断data是否获取成功
			var tbody="";
			for(var i=0;i<bookList.length;i++){
				tbody+="<tr>"
				  +"<td>"+bookList[i].bookName+"</td>"
			      +"<td>"+bookList[i].type+"</td>"
			      +"<td>"+bookList[i].tags+"</td>"
			      +"<td>"+bookList[i].intro+"</td>"
			      +"<td>"+bookList[i].created+"</td>"
			      +"<td>"+bookList[i].updated+"</td>"
			      +"<td><button class='btn btn-primary smFont selectFunc' onclick='execFuncByFlg("+bookList[i].id+",this);'>查看章节</button>"
				  +"</td>"
			      +"</tr>";
			}
			$("#book_list_table_tbody").html(tbody);
			commandFlg=4;//按钮名称变成了查看章节，所以这里得把flg改成4
		},
		error:function(){
			alert("服务器无响应！");
		}
	});
}
/**
 * 将update_book_form表单初始化为空
 * @param bookId
 * @returns
 */
function refreshCreateBookFormToNull(){
	$("#book_id_text").val(0);
	$("#book_name_text").val("");
	$("#book_type_text").val("");
	$("#book_tags_text").val("");
	$("#book_intro_text").val("");
}
/**
 * 根据小说id从服务器获取小说信息，并更新到update_book_form表单中
 * @param bookId
 * @returns
 */
function refreshUpdateBookFormByBookId(bookId){
	var url="getBookById.do";
	//沟通服务器，获取书籍值
	$.ajax({
		url:url,
		type:"POST",
		data:{bookId:bookId},
		success:function(data){
			var book=data;
			$("#book_id_text").val(book.id);
			$("#book_name_text").val(book.bookName);
			$("#book_type_text").val(book.type);
			$("#book_tags_text").val(book.tags);
			$("#book_intro_text").val(book.intro);
		},
		error:function(){
			alert("服务器无响应！");
		}
	});
	//判断表单值是否为空及是否合法
//	var bookId=$("#book_id_text").val();
//	var bookName=$("#book_name_text").val();
//	var bookType=$("#book_type_text").val();
//	var bookTags=$("#book_tags_text").val();
//	var bookIntro=$("#book_intro_text").val();
}