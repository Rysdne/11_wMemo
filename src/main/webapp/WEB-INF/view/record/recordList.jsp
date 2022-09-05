<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="/wMemo/css/content/style.css" type="text/css" rel="stylesheet" />
<script>
	$(function() {
		$('tr').click(function() {
			var rIdx = $(this).find("td").eq(0).text();
			$.ajax({
				url : "/wMemo/record/recordListOne",
				type : "post",
				dataType : "text",
				data : {"ridx":rIdx},
				success : function(result) {
					$('#aside_box').html(result);
				}
			});
		});
	});
</script>
<table style="background-color:white;">
	<tr style="background-color:rebeccapurple; color:white;">
		<th class="hidden">ridx</th>
		<th class="hidden">id</th>
		<th style="width: 10vh;">타이틀</th>
		<th style="width: 10vh;">내용</th>
		<th style="width: 10vh;">날짜</th>
	</tr>
	<c:forEach var="record" items="${recordList}">
	<tr id="tBody">
		<td class="hidden"><c:out value="${record.ridx}"/></td>
		<td class="hidden"><c:out value="${record.id}"/></td>
		<td><div class="ellipsis" style="width: 10vh;"><c:out value="${record.rtitle}"/></div></td>
		<td><div class="ellipsis" style="width: 20vh;"><c:out value="${record.rtext}"/></div></td>
		<td><div class="ellipsis" style="width: 10vh;"><c:out value="${record.rdate}"/></div></td>
	</tr>
	</c:forEach>
</table>