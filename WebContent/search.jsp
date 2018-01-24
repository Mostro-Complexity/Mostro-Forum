<%@page import="com.adminServlet.AdminPlateServlet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>

<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>板块管理</title>

<!-- Bootstrap Core CSS -->
<link href="lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!-- Additional fonts for this theme -->
<link href="lib/font-awesome/css/font-awesome.min.css" rel="stylesheet"
	type="text/css">
<link
	href='https://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic'
	rel='stylesheet' type='text/css'>
<link
	href='https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800'
	rel='stylesheet' type='text/css'>

<!-- Custom styles for this theme -->
<link href="css/clean-blog.min.css" rel="stylesheet">

<!-- Temporary navbar container fix until Bootstrap 4 is patched -->
<style>
.navbar-toggler {
	z-index: 1;
}

@media ( max-width : 576px) {
	nav>.container {
		width: 100%;
	}
}
</style>

</head>

<body>
	<%@ include file="navigation.jsp"%>

	<!-- Page Header -->
	<header class="intro-header"
		style="background-image: url('img/contact-bg.jpg')">
	<div class="container">
		<div class="row">
			<div class="col-lg-8 offset-lg-2 col-md-10 offset-md-1">
				<div class="page-heading">
					<h1>板块管理</h1>
					<hr class="small">
					<span class="subheading">查找，修改所有板块</span>
				</div>
			</div>
		</div>
	</div>
	</header>

	<!-- Main Content -->
	<div class="container">
		<div class="row">
			<div class="col-lg-8 offset-lg-2 col-md-10 offset-md-1">
				<%@ page import="com.adminServlet.*"%>
				<%@ page import="com.controller.*"%>
				<%@ page import="java.sql.*"%>
				<%
					posts = LoginServlet.loadPosts(LoginServlet.connection, 200);
					ArrayList<Plate> plates = AdminPlateServlet.loadPlates(LoginServlet.connection);
					if (plates != null) {
						for (int i = 0; i < plates.size(); i++) {
				%>
				<form id=<%=String.format("\"%s\"", plates.get(i).getPlateID())%>
					action="AdminRemovePlateServlet" method="post">
					<div class="post-preview">
						<a type="submit"				>
							<h2 class="post-title"><%=plates.get(i).getName()%></h2>
							<h3 class="post-subtitle"></h3> <input 
							name="plate_id" style="display: none"
							value=<%=String.format("\"%s\"", plates.get(i).getPlateID())%>>
						</a>
						<p class="post-meta" >
							<a href="#" onclick="document.getElementById(<%=String.format("\'%s\'", plates.get(i).getPlateID())%>).submit();">删除</a>					</p>
					</div>
				</form>
				<%
					}
					}
				%>
				<hr>
				<!-- Pager -->
				<div class="clearfix">

					<a class="btn btn-secondary float-right" href="#">下一页 &rarr;</a> <a
						class="btn btn-secondary float-right" href="#">&larr;上一页</a>
				</div>
			</div>
		</div>
	</div>

	<hr>

	<!-- Footer -->
	<footer>
	<div class="container">
		<div class="row">
			<div class="col-lg-8 offset-lg-2 col-md-10 offset-md-1"></div>
		</div>
	</div>
	</footer>

	<!-- jQuery Version 3.1.1 -->
	<script src="lib/jquery/jquery.js"></script>

	<!-- Tether -->
	<script src="lib/tether/tether.min.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="lib/bootstrap/js/bootstrap.min.js"></script>

	<!-- Theme JavaScript -->
	<script src="js/clean-blog.min.js"></script>

</body>

</html>
