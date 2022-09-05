<%@ page import="javax.naming.spi.DirStateFactory.Result"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href="/wMemo/css/content/style.css" type="text/css" rel="stylesheet" />
<script src="//cdnjs.cloudflare.com/ajax/libs/annyang/2.6.1/annyang.min.js"></script>
<script type="text/javascript">
	$('#recordDelete').click(function() {
		var rIdx = $('#recordIdx').val();
		var state = confirm("정말 삭제하시겠습니까");
		if(state){
			$.ajax({
				url : "/wMemo/record/recordDelete?ridx="+rIdx,
				success : function(result) {
					$('#aside_box').html(result);
				}
			});
		}
	});
</script>
<div id="recordBox">
	<c:forEach var="record" items="${recordListOne}">
		<div>
			<span id="recordState">Disabled..</span> | 
			<button id="speech" disabled>Rec</button>
			<button id="stop" disabled>Pause</button> |
				<input readonly type="hidden" id="recordIdx" name="recordIdx" value="${record.ridx}">
				<input readonly type="hidden" id="recordId" name="recordId" value="${record.id}">
				<input readonly type="text" id="recordTitle" name="recordTitle" style="width: 15vh;" placeholder="Title" value="${record.rtitle}"> 
				<button id="recordDelete">Delete</button>
		</div>
		<div id="textWrapper" action="/wMemo/record/recordSave" method="post">
			<div id="stackText" class="textbox">${record.rtext}</div>
		</div>
	</c:forEach>
</div>