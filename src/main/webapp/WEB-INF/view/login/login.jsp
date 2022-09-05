<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link href="/wMemo/css/content/style.css" type="text/css" rel="stylesheet" />
<script>
	$(function() {
		$('#login').click(function() {
			var id = $('#id').val();
			var password = $('#password').val();
			if (id == "" || id > 50 || password == "" || password > 20) {
				alert('계정 정보를 확인해주세요');
				return;
			}
			$('#loginForm').submit();
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
						<div id="bookmark" class="aside_sideMenu_butt_disabled">
							<i class="fa-solid fa-book-bookmark"></i>
						</div>
					</li>
					<li>
						<div id="speech" class="aside_sideMenu_butt_disabled">
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
				<div id="aside_folder_option">옵션</div>
				<div class="aside_folder">폴더메뉴</div>
			</div>
		</div>
	</aside>
	<main>
		<div id="main_loginCover_back"></div>
		<div id="main_memo">
			<div id="main_memoHeader">
				<ul>
					<li onclick="location.href='/wMemo/'">Home</li>
				</ul>
			</div>
			<div id="main_memoBody">
				<div id="form_frame">
					<input readonly type="text" id="title_disabled" name="title_disabled" placeholder="로그인">
					<div id="postit_box">
						<div id="postit_login">
							<form id="loginForm" action="/wMemo/login/loginProc" method="post">
								<table>
									<tr>
										<th>
											<span style="font-weight: bold">ID</span>
										</th>
										<td>
											 : <input type="text" id="id" name="id" class="login_input" tabindex="1">
										</td>
										<td rowspan="2">
											<input type="button" id="login" name="login" class="summit_butt" value="로그인" tabindex="3">
										</td>
									</tr>
									<tr>
										<th>
											<span style="font-weight: bold">PW</span>
										</th>
										<td>
											 : <input type="password" id="password" name="password" class="login_input" tabindex="2">
										</td>
									</tr>
								</table>
							</form>
							<br>
							<a href="/wMemo/login/regId" tabindex="4">회원 가입</a> | <a href="/wMemo/login/findId" tabindex="5">아이디 찾기</a> | <a href="/wMemo/login/findPw" tabindex="6">비밀번호 찾기</a><br>
						</div>
					</div>
				</div>
			</div>
		</div>
	</main>
</div>