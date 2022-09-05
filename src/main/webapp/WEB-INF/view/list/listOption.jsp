<%@ page import="java.time.format.DateTimeFormatter"%>
<%@ page import="java.time.LocalDate"%>
<%@ page import="javax.naming.spi.DirStateFactory.Result"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="/wMemo/css/content/style.css" type="text/css"
	rel="stylesheet" />
<script>
	$(function() {
		var uId = $('#optionId').val();
		$('#listMonth').click(function() {
			alert('click');
			var _month = $('#monthSelect').val();
			var mArray = _month.split("-");
			var idxMonth = mArray[0] + mArray[1];
			$.ajax({
				url : "/wMemo/list/listMonth",
				type : "post",
				dataType : "text",
				data : {"id":uId, "idxMonth":idxMonth},
				success : function(result) {
					$('#aside_box').html(result);
				}
			});
			alert('ok');
		});
		$('#listClear').click(function() {
			$.ajax({
				url : "/wMemo/list/listOption",
				success : function(result) {
					$('#aside_option').html(result);
				}
			});
			$.ajax({
				url : "/wMemo/list/listAll",
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
	<input type="hidden" id="optionId" name="optionId" value="${sessionScope.id}">
	<input type="month" id="monthSelect" name="monthSelect" style="width:50%; height:4vh; margin-left: 1vh; font-size:1.5rem;">
	<button id="listMonth" style="width:20%; height:4vh;">검색</button>
	<button id="listClear" style="width:20%; height:4vh;">초기화</button>
