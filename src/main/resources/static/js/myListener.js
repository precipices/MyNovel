/**
 * 监听器集
 */

$(function() {
	// 初始化
	// 显示第一个tab-item的内容，隐藏其他的
	$('#tab-contents div.tab-item:eq(0)').show().siblings('div').hide();

	// 切换选项卡监听器
	$('#tab-titles li').click(
			function() {
				$(this).siblings().find('a').removeClass("active");
				$(this).find("a").addClass("active");
				$(
						'#tab-contents div.tab-item:eq('
								+ $(this).prevAll().length + ')').show()
						.siblings('div').hide();
			});
	// 人名生成方式监听器
	$("#create-way").on("change", function() {
		// 得到当前生成方式
		var val = $(this).val();
		if (val == 0) {
			// $('#create-way-div').show();
			// $('#create-num-div').show();
			// $('#xing-num-div').show();
//			$('#ming-num-div').hide();
			$('#sex-div').hide();
		} else if (val == 1) {
//			$('#ming-num-div').show();
			$('#sex-div').show();
		}
	});

	// 点击人名生成按钮
	$('#name-create-btn')
			.on(
					"click",
					function() {
						var url = "/namecreate.do";

						var createType = $('#create-way').val();
						var createNum = $('#create-num').val();
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
										;
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
										$("#result-div").html(resultTable);
										$("#result-div")
												.find("table")
												.addClass(
														"table table-striped table-bordered mytable");

									}
								});
					});
});