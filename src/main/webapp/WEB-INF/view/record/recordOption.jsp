<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="/wMemo/css/content/style.css" type="text/css" rel="stylesheet" />
<script>
	$(function() {
		var uId=$('#id').val();
		$('#recordForm_butt').click(function() {
			$.ajax({
				url : "/wMemo/record/record",
				success : function(result) {
					$('#aside_box').html(result);
				}
			});
		});
		$('#recordList_butt').click(function() {
			$.ajax({
				url : "/wMemo/record/recordList",
				type : "post",
				dataType : "text",
				data : {"id":uId},
				success : function(result) {
					$('#aside_box').html(result);
				}
			});
		});

	});
</script>
<input type="hidden" id="id" name="id" value="${sessionScope.id}">
<button id="recordForm_butt">녹음</button>
<button id="recordList_butt">조회</button>