/**
 * 事件管理器中的refresh;
 * 更新表格或数据的方法,允许调用MyUtil和golbalVar;仅更新，不会跳转或触发点击事件;
 */
//******************************************事件列表******************************************************************
/**
 * 根据小说id更新事件列表
 * 
 * @param bookId
 * @returns
 */
function refreshEventListByBookId(bookId) {
	// 设置事件管理器隐藏书籍id
	$("#book_id_in_event_manage_hidden").val(bookId);
	// 先清空表格
	$("#event_list_table_tbody").html("");
	// 沟通服务器取数据并返回到表格中
	var url = "getEventListByBookId.do";
	$.ajax({
		type : "POST",
		url : url,
		data : {
			bookId : bookId
		},
		success : function(data) {
			// alert(JSON.stringify(data));
			if (data == null) {
				alert("该小说尚未创建事件！");
				return;
			}
			var eventList = data;
			// 这里没有判断data是否获取成功
			var tbody = "";
			for (var i = 0; i < eventList.length; i++) {
				tbody += "<tr>" + "<td>" + eventList[i].evName + "</td>"
						+ "<td>" + eventList[i].intro + "</td>" + "<td>"
						+ eventList[i].origin + "</td>" + "<td>"
						+ eventList[i].result + "</td>" + "<td>"
						+ eventList[i].created + "</td>" + "<td>"
						+ eventList[i].updated + "</td>" + "<td>"
						+ "<button class='btn btn-primary smFont evSelFunc' "
						+ "onclick='execFuncByEvFlg(" + eventList[i].id
						+ ",this)'" + ">查看信息</button>" + "</td>" + "</tr>";
			}
			$("#event_list_table_tbody").html(tbody);

		},
		error : function() {
			alert("服务器无响应！");
		}
	});
}

/**
 * 删除事件 根据id删除事件,成功则删除列表对应行
 * 
 * @param bookId
 * @returns
 */
function deleteEventByEvId(evId, thisEle) {
	var url = "deleteEventByEvId.do";
	$.ajax({
		type : "POST",
		url : url,
		data : {
			evId : evId
		},
		success : function(data) {
			if (data == 1) {
				alert("删除成功！");
				$(thisEle).parent().parent().remove();
			} else {
				alert("删除失败：" + data);
			}
		},
		error : function() {
			alert("服务器无响应！");
		}
	});
}
/**
 * 删除事件参与人物
 * @returns
 */
function deleteChFromEventByChId(chId, evId, thisEle) {
	var url = "deleteChFromEventByChId.do";
	$.ajax({
		type : "POST",
		url : url,
		data : {
			chId : chId,
			evId : evId
		},
		success : function(data) {
			if (data == 1) {
				alert("删除成功！");
				$(thisEle).parent().parent().remove();
			} else {
				alert("删除失败：" + data);
			}
		},
		error : function() {
			alert("服务器无响应！");
		}
	});
}
// ******************************************事件表单******************************************************************
/**
 * 根据事件id更新事件表单信息
 * 
 * @param evId
 * @returns
 */
function refreshEventFormByEvId(evId) {
	var url = "getEventByEvId.do";
	$.ajax({
		url : url,
		type : "POST",
		data : {
			evId : evId
		},
		success : function(data) {
			if (data == null)
				alert("!!查无此人：evId");
			var ev = data;
			$("#ev_id_text").val(ev.id);
			$("#ev_name_text").val(ev.evName);
			$("#ev_intro_text").val(ev.intro);
			$("#ev_origin_text").val(ev.origin);
			$("#ev_result_text").val(ev.result);
			var chList=ev.chList;
			var tbody = "";
			if(chList!=null)
			for (var i = 0; i < chList.length; i++) {
				tbody += "<tr>" + "<td>" + chList[i].chName + "</td>"
						+ "<td>" + chList[i].chAttr + "</td>" + "<td>"
						+"<input type='button' class='btn btn-primary smFont evChange' "
						+ "onclick='deleteChFromEventByChId("+chList[i].id+","+ev.id+", this);' value='删除'/>" + "</td>" + "</tr>";
			}
			$("#character_list_in_event_form_table_tbody").html(tbody);
		},
		error : function() {
			alert("服务器无响应：getEventByEvId.do");
		}
	});
}
/**
 * 根据事件表单中的信息，沟通服务器，更新事件信息
 * 
 * @returns
 */
function updateEvent() {
	var url = "updateEvent.do";
	$.ajax({
		url : url,
		type : "POST",
		async : false,
		data : {
			evId : $("#ev_id_text").val(),
			evName:$("#ev_name_text").val(),
			intro:$("#ev_intro_text").val(),
			origin:$("#ev_origin_text").val(),
			result:$("#ev_result_text").val()
		},
		success : function(data) {
			if (data == 1)
				alert("事件信息更新成功！");
			else
				alert("！事件信息更新失败：" + data);
		},
		error : function() {
			alert("服务器无响应：updateEvent.do");
		}
	});
}
/**
 * 根据事件表单中的信息，沟通服务器，新增事件信息
 * 
 * @returns
 */
function insertEvent() {
	var url = "insertEvent.do";
	$.ajax({
		url : url,
		type : "POST",
		async : false,
		data : {
			bookId : $("#book_id_in_event_manage_hidden").val(),
			evName:$("#ev_name_text").val(),
			intro:$("#ev_intro_text").val(),
			origin:$("#ev_origin_text").val(),
			result:$("#ev_result_text").val()
		},
		success : function(data) {
			if (data == 1)
				alert("新增事件信息成功！");
			else
				alert("！事件信息插入失败：" + data);
		},
		error : function() {
			alert("服务器无响应：insertEvent.do");
		}
	});
}