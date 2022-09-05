<%@ page import="javax.naming.spi.DirStateFactory.Result"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="/wMemo/css/content/style.css" type="text/css"
	rel="stylesheet" />
<script>
	$(function() {
		$('tr').click(function() {
			var idx = $(this).find("td").eq(0).text();
			var id = $(this).find("td").eq(1).text();
			var subfolder = $(this).find("td").eq(2).text();
			var title = $(this).find("td").eq(3).text();
			location.href = "/wMemo/memo/findOne?idx=" + idx + "&id=" + id + "&subfolder=" + subfolder + "&title=" + title;
		});
	});
</script>
<table style="background-color:white;">
	<tr style="background-color:rebeccapurple; color:white;">
		<th class="hidden">idx</th>
		<th class="hidden">계정</th>
		<th style="width: 10vh;">폴더</th>
		<th style="width: 20vh;">타이틀</th>
		<th style="width: 10vh;">수정일</th>
		<th class="hidden">수정시간</th>
	</tr>
	<c:forEach var="memo" items="${memoList}">
	<tr id="tBody" style="border:1px solid lightgray;">
		<td class="hidden"><c:out value="${memo.idx}"/></td>
		<td class="hidden"><c:out value="${memo.id}"/></td>
		<td><div class="ellipsis" style="width: 10vh;"><c:out value="${memo.subfolder}"/></div></td>
		<td><div class="ellipsis" style="width: 20vh;"><c:out value="${memo.title}"/></div></td>
		<td><div class="ellipsis" style="width: 10vh;"><c:out value="${memo.cdate}"/></div></td>
		<td class="hidden"><c:out value="${memo.ctime}"/></td>
	</tr>
	</c:forEach>
</table>