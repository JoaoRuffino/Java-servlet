<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>

	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<div class="container">
			<a class="navbar-brand" href="#">Loja</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarNav"
				aria-controls="navbarNav" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarNav">
				<ul class="navbar-nav me-auto">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="./index.jsp">Home</a></li>
					<li class="nav-item"><a class="nav-link" href="#produtos">Produtos</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="#contato">Contato</a>
					</li>
					<li class="nav-item"><a class="nav-link" id="manageContato" href="#manageContato">Manage Usuários</a>
					</li>
					<li class="nav-item"><a class="nav-link" id="manageProdutos" href="#manageProdutos">Manage Produtos</a>
					</li>
				</ul>
				<button class="btn btn-outline-light" id="login" type="button">Login</button>
				<button class="btn btn-outline-danger" id="logout" type="button">Logout</button>
				
			</div>
		</div>
	</nav>
	<!-- Login Section -->

	<div class="d-flex justify-content-center align-items-center vh-100">
	<div id="loginSection" class="mb-4" style="max-width: 600px; width: 100%;">
		<h3>Login</h3>
		<form id="loginForm">
			<div class="mb-3">
				<label for="loginEmail" class="form-label">Email</label>
				<input type="email" class="form-control" id="loginEmail" placeholder="Enter your email">
			</div>
			<div class="mb-3">
				<label for="loginPassword" class="form-label">Password</label>
				<input type="password" class="form-control" id="loginPassword" placeholder="Enter your password">
			</div>
			<button type="button" class="btn btn-primary" id="loginBtn">Login</button>
		</form>
	</div>
</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script>
	
	$(document).ready(function(){
		var token = localStorage.getItem('token');
		if(!token){
            $("#manageContato").addClass("d-none");
            $("#manageProdutos").addClass("d-none");
            $("#logout").addClass("d-none");
		}
		if(token){
			$("#login").addClass("d-none");
			alert("Já está logado!");
            $(location).attr('href', './index.jsp');
		}
		
	})
	$("#login").click(function(){
		$(location).attr('href', './login.jsp');

	})
	
	$('#loginBtn').on('click', function () {
        const email = $('#loginEmail').val();
        const password = $('#loginPassword').val();

        $.ajax({
            url: `http://localhost:8080/FirstProjeto/user/login`,
            method: 'POST',
            data: { email, password },
            success: function (response, textStatus, xhr) {
                if (xhr.status === 200) {
                    alert('Login successful');
                    localStorage.setItem('token', response.token);
                    //console.log(localStorage.getItem('token'));
                    $(location).attr('href', './access.jsp');
                }
            },
            error: function () {
                alert('Invalid credentials');
            }
        });
    });
	</script>
</body>
</html>