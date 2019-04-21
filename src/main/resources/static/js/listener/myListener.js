/**
 * 监听器集
 */

//验证上传类型
function checkUploadType(ele) {
	// 返回 KB，保留小数点后两位
	// alert((ele.files[0].size/(1024*1024)).toFixed(2));
	var file = ele.value;
	if (!/.(txt|word)$/.test(file)) {
		alert("上传类型必须是txt,word中的一种");
		$(ele).val("");
		return false;
	}
	// 返回Byte(B),保留小数点后两位
	if (((ele.files[0].size).toFixed(2)) >= (1000 * 1024 * 1024)) {
		alert("请上传小于1000M的文件");
		$(ele).val("");
		return false;
	}
}
// 点击提交按钮，上传文件
function bookUpload() {
	var file = $("#book_upload_file");
	if (file.val() == "" || file.val() == null) {
		alert("文件为空！");
		return;
	}
	var url = "/bookUpload.do";
	// 上传文件
	$.ajax({
		type : "POST",
		url : url,
		contentType : false,
		processData : false,
		data : new FormData($("#book_upload_form")[0]),
		success : function(data) {
			alert(data);
			// // 上传成功则载入文件
			// if (data != "上传失败！") {
			// filePath = data;
			// // 关闭模态框
			// $("#myModal").modal("hide");
			// } else {
			// alert(data);
			//			}
		}
	});
}

$(function() {
	// 初始化
	// 显示第一个tab-item的内容，隐藏其他的
	$('#tab-contents div.tab-item:eq(0)').show().siblings('div').hide();
	$('#sex-div').hide();
	// 切换选项卡监听器
//	$('#tab-titles li').click(
//			function() {
//				$(this).siblings().find('a').removeClass("active");
//				$(this).find("a").addClass("active");
//				$(
//						'#tab-contents div.tab-item:eq('
//								+ $(this).prevAll().length + ')').show()
//						.siblings('div').hide();
//			});
	// 人名生成方式监听器
	$("#create-way1").on("change", function() {
		// 得到当前生成方式
		var val = $(this).val();
		if (val == 0) {
			// $('#create-way-div1').show();
			// $('#create-num-div1').show();
			// $('#xing-num-div').show();
			// $('#ming-num-div').hide();
			$('#sex-div').hide();
		} else if (val == 1) {
			// $('#ming-num-div').show();
			$('#sex-div').show();
		}
	});

	// 点击人名生成按钮
	$('#create-btn1').on("click",
					function() {
						var url = "/namecreate.do";

						var createType = $('#create-way1').val();
						var createNum = $('#create-num1').val();
						var xingNum = $('#xing-num').val();
						var mingNum = $('#ming-num').val();
						var sex = $('#sex').val();

						$
								.ajax({
									type : "POST",
									url : url,
									data : {
										"createType" : createType,
										"createNum" : createNum,
										"xingNum" : xingNum,
										"mingNum" : mingNum,
										"sex" : sex
									},
									success : function(data) {
										var table = JSON.parse(data);
										var resultTable = "<table>";
										for (var i = 0; i < table.length;) {
											resultTable += "<tr>";
											for (var j = 0; j < 10
													&& i < table.length; j++, i++) {
												resultTable += "<td>"
														+ table[i] + "</td>";
											}
											resultTable += "</tr>";
										}
										resultTable += "</table>";
										;
										$("#result-div1").html(resultTable);
										$("#result-div1")
												.find("table")
												.addClass(
														"table table-striped table-bordered mytable");

									}
								});
					});
	// 点击地名生成按钮
	$('#create-btn3').on("click",
					function() {
						var url = "/placenamecreate.do";

						var createType = $('#create-way3').val();
						var createNum = $('#create-num3').val();

						$
								.ajax({
									type : "POST",
									url : url,
									data : {
										"createType" : createType,
										"createNum" : createNum
									},
									success : function(data) {
										var table = JSON.parse(data);
										var resultTable = "<table>";
										for (var i = 0; i < table.length;) {
											resultTable += "<tr>";
											for (var j = 0; j < 10
													&& i < table.length; j++, i++) {
												resultTable += "<td>"
														+ table[i] + "</td>";
											}
											resultTable += "</tr>";
										}
										resultTable += "</table>";
										;
										$("#result-div3").html(resultTable);
										$("#result-div3")
												.find("table")
												.addClass(
														"table table-striped table-bordered mytable");

									}
								});
					});
});