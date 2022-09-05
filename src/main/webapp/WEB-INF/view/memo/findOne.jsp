<%@ page import="java.time.format.DateTimeFormatter"%>
<%@ page import="java.time.LocalDate"%>
<%@ page import="javax.naming.spi.DirStateFactory.Result"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 기본 context에 wMemo가 설정되어있으므로 리소스 사용 시엔 앞에 기본 context를 붙여줘야 함 -->
<link href="/wMemo/css/content/style.css" type="text/css"
	rel="stylesheet">
<script>
	$(function() {
		var uId = $('#id').val();
		$('.aside_sideMenu_butt').click(function() {
			var currId = $(this).attr('id');
			if(currId=="bookmark"){
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
			} else if (currId=="record") {
				$.ajax({
					url : "/wMemo/record/recordOption",
					success : function(result) {
						$('#aside_option').html(result);
					}
				});
				$.ajax({
					url : "/wMemo/record/record",
					success : function(result) {
						$('#aside_box').html(result);
					}
				});
			}
			if ($('#aside_sideMenu_cursor').val() == "") {
				$('#aside_sideMenu_cursor').val(currId);
				$('aside').toggleClass('aside_open');

			} else if ($('#aside_sideMenu_cursor').val() != currId) {
				$('#aside_sideMenu_cursor').val(currId);

			} else {
				$('#aside_sideMenu_cursor').val("");
				$('aside').toggleClass('aside_open');
			}
		});
		$('#save').click(function () {
	    	var id=$('#id').val();
	    	var title=$('#title').val();
	    	var input=$('#input').val();
	    	if(title==""){
	    		alert('타이틀을 입력해주세요');
	    		return;
	    	}
	        $('#memo').val($('#input').html());
	        $('#form').submit();
	    });
		$('#delete').click(function() {
			var state = confirm("정말 삭제하시겠습니까");
			if(state){
				var idx = $('#idx').val();
				var id = $('#id').val();
				var preSubfolder = $('#preSubfolder').val();
				location.href = "/wMemo/memo/deleteProc?idx=" + idx + "&id=" + id + "&preSubfolder=" + preSubfolder;	
			};
		});
	});
</script>
<div id="content">
	<aside>
		<input type="hidden" id="aside_sideMenu_cursor" value="">
		<div>
			<div id="aside_sideMenu">
				<ul>
					<li>
						<div id="bookmark" class="aside_sideMenu_butt">
							<i class="fa-solid fa-book-bookmark"></i>
						</div>
					</li>
					<li>
						<div id="record" class="aside_sideMenu_butt">
							<i class="fa-solid fa-microphone"></i>
						</div>
					</li>
					<li>
						<div id="paste" class="aside_sideMenu_butt_disabled">
							<i class="fa-solid fa-paste"></i>
						</div>
					</li>
				</ul>
			</div>
			<div id="aside_slide">
				<div id="aside_option">옵션</div>
				<div id="aside_box">폴더메뉴</div>
			</div>
		</div>
	</aside>
	<main>
		<div id="main_loginCover_back"></div>
		<div id="main_memo">
			<div id="main_memoHeader">
				<ul>
					<li onclick="location.href='/wMemo/'">Home</li>
					<li onclick="location.href='/wMemo/memo/create'" style="background-color:rgb(249, 249, 104);">New</li>
					<li id="save">Write</li>
					<li id="delete">Delete</li>
				</ul>
			</div>
			<div id="main_memoBody">
			<form id="form" action="/wMemo/memo/updateProc" method="post">
				<input readonly type="hidden" id="idx" name="idx" value="${findOne.idx}">
				<input readonly type="hidden" id="id" name="id" value="${findOne.id}">
				<input type="text" id="title" name="title" value="${findOne.title}">
				<input type="text" id="subfolder" name="subfolder" value="${findOne.subfolder}">
				<input readonly type="hidden" id="preSubfolder" name="preSubfolder" value="${findOne.subfolder}">
				<div contentEditable="true" id="input">${findOne.loaded}</div>
				<input type="hidden" id="memo" name="memo">
			</form>
		</div>
		</div>
	</main>
</div>