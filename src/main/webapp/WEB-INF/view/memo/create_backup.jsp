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
		var now = new Date();
		var splitYear = now.getFullYear();
		var splitMonth = ("0" + (now.getMonth() + 1)).slice(-2);
		var currentMonth = splitYear + "-" + splitMonth;
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
						<div id="microphone" class="aside_sideMenu_butt">
							<i class="fa-solid fa-microphone"></i>
						</div>
					</li>
					<li>
						<div id="language" class="aside_sideMenu_butt">
							<i class="fa-solid fa-language"></i>
						</div>
					</li>
					<li>
						<div id="paste" class="aside_sideMenu_butt">
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
					<li onclick="location.href='/wMemo/memo/create'">새 메모</li>
					<li>수정</li>
					<li>삭제</li>
				</ul>
			</div>
			<div id="main_memoBody">
			<form id="form" action="/wMemo/memo/newMemoProc" method="post">
				<input type="hidden" id="id" name="id" value="${sessionScope.id}"> 
				<input type="text" id="title" name="title" placeholder="Title" value="아뱌뱌">
				<input type="button" id="save" name="save" value="저장"><br>
				<div contentEditable="true" id="input"></div>
				<input type="hidden" id="memo" name="memo">
			</form>
		</div>
	</main>
</div>