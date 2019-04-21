/**
 * 页面加载后再执行的js
 */

$(function(){
    var E = window.wangEditor;
    var editor = new E('#content_editor');
    // 或者 var editor = new E( document.getElementById('editor') )
    editor.create();
	
	
});