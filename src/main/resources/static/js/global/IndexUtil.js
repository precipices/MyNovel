/**
 * index.html页面的工具集，可能会使用到页面中的id,提供给后续阶层调用
 */
// 返回val的字节长度
function getByteLen(val) {
	var len = 0;
	for (var i = 0; i < val.length; i++) {
		if (val[i] == ' ' || val[i] == '\n') {
			// 什么都不做
		} else if (val[i].match(/[^\x00-\xff]/ig) != null) // 全角
			len += 2;
		else
			len += 1;
	}
	return len;
}
/**
 * 返回val的字数,汉字算一个，标点字母算半个，空格算0个
 * @param val
 * @returns
 */
function getWordNum(val) {
	var len = 0;
	for (var i = 0; i < val.length; i++) {
		if (val[i] == ' ' || val[i] == '\n') {
			// 什么都不做
		} else if (val[i].match(/[^\x00-\xff]/ig) != null) // 全角
			len += 1;
		else
			len += 0.5;
	}
	return len;
}