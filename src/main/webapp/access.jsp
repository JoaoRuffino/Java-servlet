<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Gerenciamento</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
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
	
	<div id="usersSection" class="container my-5">
    <h3 class="text-center fw-bold mb-4 text-primary">User Management</h3>
    <div class="card shadow-lg border-0">
        <div class="card-header bg-primary text-white text-center">
            <h5 class="m-0">User List</h5>
        </div>
        <div class="table-responsive">
            <table id="usersTable" class="table table-striped table-hover table-borderless align-middle mb-0">
                <thead class="bg-light text-dark">
                    <tr>
                        <th scope="col" class="text-center">ID</th>
                        <th scope="col">Email</th>
                        <th scope="col">Username</th>
                        <th scope="col" class="text-center">CEP</th>
                        <th scope="col" class="text-center">Actions</th>
                    </tr>
                </thead>
                <tbody id="usersTableBody">
                    <!-- Linhas geradas dinamicamente -->
                </tbody>
            </table>
        </div>
        <div class="card-footer text-muted text-center">
            <small>&copy; 2024 Your Company</small>
        </div>
    </div>
</div>
	<script>
	$(document).ready(function(){
		var token = localStorage.getItem('token');
		if(!token){
            $(location).attr('href', './login.jsp');
            $("#manageContato").addClass("d-none");
            $("#manageProdutos").addClass("d-none");
            $("#logout").addClass("d-none");
		}
		if(token){
			$("#login").addClass("d-none");
		}
		
		//Fetch users
		$.ajax({
            url: 'http://localhost:8080/FirstProjeto/users',
            method: 'GET',
            headers: { Authorization: "Bearer " + token },
            success: function (response) {
                const tableBody = $('#usersTable tbody');
                tableBody.empty();
                response.forEach(user => {
              

                    tableBody.append(`
                        <tr>
                            <td>\${user.user_id}</td>
                            <td>\${user.email}</td>
                            <td>\${user.username}</td>
                            <td>\${user.cep}</td>
                            <td>
                                <button class="btn btn-danger btn-sm deleteBtn" data-id="${user.user_id}">Delete</button>
                            </td>
                        </tr>
                    `);
                });

                $('.deleteBtn').on('click', function () {
                    const userId = $(this).data('id');
                    deleteUser(userId);
                });
            },
            error: function () {
                alert('Error fetching users');
            }
        });
		
	})
	$("#login").click(function(){
		$(location).attr('href', './login.jsp');
	})
	$("#logout").click(function(){
		localStorage.setItem('token', '');
		alert("Você saiu! Até mais.");
        $(location).attr('href', './index.jsp');

	})
	</script>
	
</body>
</html>