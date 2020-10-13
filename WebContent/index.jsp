<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--
	Spectral by HTML5 UP
	html5up.net | @ajlkn
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
<head>
<script>
	function logOut() {
		location.href = "logout.jsp";
		alert("Logout success!");
	}
</script>

<title>SE_editor</title>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="./css/assets/css/main.css" />
<link rel="stylesheet" href="./css/assets/css/noscript.css" />
</head>
<body class="landing is-preload">
	<%
		request.setCharacterEncoding("utf-8");
	%>
	<!-- Page Wrapper -->
	<div id="page-wrapper">

		<!-- Header -->
		<header id="header" class="alt">
			<h1>
				<a href="index.jsp">UP</a>
			</h1>
			<nav id="nav">
				<ul>
					<li class="special"><a href="#menu" class="menuToggle"><span>Menu</span></a>
						<div id="menu">
							<ul>
								<li><a href="index.jsp">Home</a></li>
								<li><a href="ide/IDE.jsp" target="_blank">SE IDE</a></li>
								<li><a href="algorithm/algorithm.jsp" target="_blank">Algorithm</a></li>
								<li><a href="bulletinBoard/bulletinBoardList.jsp">BulletinBoard</a></li>
								<li><a href="inquiry/inquiry.jsp">inquiry</a></li>
								<%
									if ("master".equals((String) session.getAttribute("authority"))) {
								%>
								<li><a href="inquiry/inquiryMasterMode.jsp">문의 답변</a></li>
								<li><a href="algorithm/algorithmBoard.jsp">알고리즘 추가</a></li>
								<%
									}
								if (session.getAttribute("id") == null) {
								%>
								<li><a href="./loginForm.jsp">Login</a></li>
								<%
									} else {
								%>
								<li><a href="userInfo/pwCheckForModify.jsp">Modifycation</a></li>
								<li><a href="#" onclick="logOut()">logout</a></li>
								<%
									}
								%>
							</ul>
						</div></li>
				</ul>
			</nav>
		</header>

		<!-- Banner -->
		<section id="banner">
			<div class="inner">
				<h2>WORKSPACE</h2>
				<p>
					This is your workplace<br /> This site is available in several
					languages<br /> Let's coding now!
				</p>
				<ul class="actions special">
					<li><a href="ide/IDE.jsp" class="button primary">Go
							WorkSpace</a></li>
				</ul>
			</div>
			<a href="#one" class="more scrolly">This site?</a>
		</section>

		<!-- One -->
		<section id="one" class="wrapper style1 special">
			<div class="inner">
				<header class="major">
					<h2>
						This site for <br />coding on the web
					</h2>
					<p>
						It will be very effective if you want to code without programming<br />
						This site supports java, c, javascript, and python.<br /> We hope
						this site is useful to you
					</p>
				</header>
				<ul class="icons major">
					<li><span class="icon fa-gem major style1"><span
							class="label">Lorem</span></span></li>
					<li><span class="icon fa-heart major style2"><span
							class="label">Ipsum</span></span></li>
					<li><span class="icon solid fa-code major style3"><span
							class="label">Dolor</span></span></li>
				</ul>
			</div>
		</section>

		<!-- Two -->
		<section id="two" class="wrapper alt style2">
			<section class="spotlight">
				<div class="image">
					<img src="images/pic01.jpg" alt="" />
				</div>
				<div class="content">
					<h2>SE LAB</h2>
					<p>
						이 사이트는 SE LAB 홈페이지의 <br /> 부가적인 기능이다.<br /> 홈페이지에서는 SE LAB의 다양한 <br />정보
						및 자유로운 환경을 제공한다.<br /> 여러 게시판들을 이용하여<br /> SE내의 다양한 정보를 제공한다
					</p>
				</div>
			</section>
			<section class="spotlight">
				<div class="image">
					<img src="images/pic02.jpg" alt="" />
				</div>
				<div class="content">
					<h2>Web Coding Site</h2>
					<p>
						이 사이트는 각자 개인의 <br /> workspace를 제공한다.<br /> WORKSPACE는 MONACO <br />EDITOR을
						이용하여 만들어졌으며, <br />C, JAVA, PYTHON, JS <br />등의 언어들을 사용하여 작성한
						코드를 저장 및 실행한다.
					</p>
				</div>
			</section>
			<section class="spotlight">
				<div class="image">
					<img src="images/pic03.jpg" alt="" />
				</div>
				<div class="content">
					<h2>BulletinBoard</h2>
					<p>
						이 사이트를 이용하는 인원이<br /> 소통할수 있는 게시판을 제공한다. 코딩을 사용하다 발생하는 문제, 혹은 <br />
						필요한 내용들을 소통가능하게 하고자 한다.
					</p>
				</div>
			</section>
		</section>

		<!-- CTA -->
		<section id="cta" class="wrapper style4">
			<div class="inner">
				<header>
					<h2>GO SE LAB</h2>
					<p>SE 홈페이지의 다양한 기능을 사용하기를 원한다면?</p>
				</header>
				<ul class="actions stacked">
					<li><a href="#" class="button fit primary">GO</a></li>
					<li><a href="#" class="button fit">SE_LOGIN</a></li>
				</ul>
			</div>
		</section>

		<!-- Footer -->
		<footer id="footer">
			<ul class="icons">
				<li><a href="#" class="icon brands fa-dribbble"><span
						class="label">SE</span></a></li>
				<li><a href="#" class="icon brands fa-twitter"><span
						class="label">Twitter</span></a></li>
				<li><a href="#" class="icon brands fa-facebook-f"><span
						class="label">Facebook</span></a></li>
				<li><a href="#" class="icon brands fa-instagram"><span
						class="label">Instagram</span></a></li>
				<li><a href="#" class="icon solid fa-envelope"><span
						class="label">Hanshin</span></a></li>
			</ul>

			<ul class="copyright">
				<li>&copy; SE LAB</li>
				<li>GO HOME: <a href="http://html5up.net">SE</a></li>
			</ul>
		</footer>

	</div>

	<!-- Scripts -->
	<script src="./css/assets/js/jquery.min.js"></script>
	<script src="./css/assets/js/jquery.scrollex.min.js"></script>
	<script src="./css/assets/js/jquery.scrolly.min.js"></script>
	<script src="./css/assets/js/browser.min.js"></script>
	<script src="./css/assets/js/breakpoints.min.js"></script>
	<script src="./css/assets/js/util.js"></script>
	<script src="./css/assets/js/main.js"></script>

</body>
</html>