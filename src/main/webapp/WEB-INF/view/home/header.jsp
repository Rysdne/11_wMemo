<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 기본 context에 wMemo가 설정되어있으므로 리소스 사용 시엔 앞에 기본 context를 붙여줘야 함 -->
<link href="/wMemo/css/header/style.css" type="text/css" rel="stylesheet"/>
<header>
	<div id="header_profile">
		<!-- 기본 context에 wMemo가 설정되어있으므로 리소스 사용 시엔 앞에 기본 context를 붙여줘야 함 -->
		<img src="/wMemo/img/profile.png" id="header_profileImg" alt="프로필">
		<h1 id="header_profile_sayHello">
		${sessionScope.id}
		</h1>
		<h5 id="header_profile_logout">
			<a href="/wMemo/login/logout">로그아웃</a>
		</h5>
	</div>
	<div id="header_menu">
		<i class="fa-solid fa-bars"></i>
	</div>
</header>
