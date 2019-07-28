/**
 * 人物管理器中的refresh;
 * 更新表格或数据的方法,允许调用MyUtil和golbalVar;仅更新，不会跳转或触发点击事件;
 */
//******************************************人物列表******************************************************************
/**
 * 根据小说id更新人物列表
 * 
 * @param bookId
 * @returns
 */
function refreshCharacterListByBookId(bookId) {
	// 设置人物管理器隐藏书籍id
	$("#book_id_in_character_manage_hidden").val(bookId);
	// 先清空表格
	$("#character_list_table_tbody").html("");
	// 沟通服务器取数据并返回到表格中
	var url = "getCharacterListByBookId.do";
	$.ajax({
		type : "POST",
		url : url,
		data : {
			bookId : bookId
		},
		success : function(data) {
			// alert(JSON.stringify(data));
			if (data == null) {
				alert("该小说尚未创建人物！");
				return;
			}
			var characterList = data;
			// 这里没有判断data是否获取成功
			var tbody = "";
			for (var i = 0; i < characterList.length; i++) {
				tbody += "<tr>" + "<td>" + characterList[i].chName + "</td>"
						+ "<td>" + characterList[i].chAttr + "</td>" + "<td>"
						+ (characterList[i].sex ? '男' : '女') + "</td>" + "<td>"
						+ characterList[i].belong + "</td>" + "<td>"
						+ characterList[i].intro + "</td>" + "<td>"
						+ characterList[i].firstAppear + "</td>" + "<td>"
						+ characterList[i].appearTimes + "</td>" + "<td>"
						+ characterList[i].created + "</td>" + "<td>"
						+ characterList[i].updated + "</td>" + "<td>"
						+ "<button class='btn btn-primary smFont chSelFunc' "
						+ "onclick='execFuncByChFlg(" + characterList[i].id
						+ ",this)'" + ">查看信息</button>" + "</td>" + "</tr>";
			}
			$("#character_list_table_tbody").html(tbody);

		},
		error : function() {
			alert("服务器无响应！");
		}
	});
}

/**
 * 删除人物 根据id删除人物,成功则删除列表对应行
 * 
 * @param bookId
 * @returns
 */
function deleteCharacterByChId(chId, thisEle) {
	var url = "deleteCharacterByChId.do";
	$.ajax({
		type : "POST",
		url : url,
		data : {
			chId : chId
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
// ******************************************人物表单******************************************************************
/**
 * 根据人物id更新人物表单信息
 * 
 * @param chId
 * @returns
 */
function refreshCharacterFormByChId(chId) {
	var url = "getCharacterByChId.do";
	$.ajax({
		url : url,
		type : "POST",
		data : {
			chId : chId
		},
		success : function(data) {
			if (data == null)
				alert("!!查无此人：chId");
			var ch = data;
			$("#ch_id_text").val(ch.id);
			$("#ch_name_text").val(ch.chName);
			$("#ch_attr_sel").val(ch.chAttr);
			$("#ch_first_appear_text").val(ch.firstAppear);
			$("#ch_appear_times_text").val(ch.appearTimes);
			$("#ch_belong_text").val(ch.belong);
			$("#ch_sex_text").val(ch.sex);
			$("#ch_intro_text").val(ch.intro);
		},
		error : function() {
			alert("服务器无响应：getCharacterByChId.do");
		}
	});
}
/**
 * 根据人物表单中的信息，沟通服务器，更新人物信息
 * 
 * @returns
 */
function updateCharacter() {
	var url = "updateCharacter.do";
	$.ajax({
		url : url,
		type : "POST",
		async : false,
		data : {
			chId : $("#ch_id_text").val(),
			chName : $("#ch_name_text").val(),
			chAttr : $("#ch_attr_sel").val(),
			belong : $("#ch_belong_text").val(),
			sex : $("#ch_sex_text").val(),
			intro : $("#ch_intro_text").val()
		},
		success : function(data) {
			if (data == 1)
				alert("人物信息更新成功！");
			else
				alert("！人物信息更新失败：" + data);
		},
		error : function() {
			alert("服务器无响应：updateCharacter.do");
		}
	});
}
/**
 * 根据人物表单中的信息，沟通服务器，新增人物信息
 * 
 * @returns
 */
function insertCharacter() {
	var url = "insertCharacter.do";
	$.ajax({
		url : url,
		type : "POST",
		async : false,
		data : {
			bookId : $("#book_id_in_character_manage_hidden").val(),
			chName : $("#ch_name_text").val(),
			chAttr : $("#ch_attr_sel").val(),
			belong : $("#ch_belong_text").val(),
			sex : $("#ch_sex_text").val(),
			intro : $("#ch_intro_text").val()
		},
		success : function(data) {
			if (data == 1)
				alert("新增人物信息成功！");
			else
				alert("！人物信息插入失败：" + data);
		},
		error : function() {
			alert("服务器无响应：insertCharacter.do");
		}
	});
}