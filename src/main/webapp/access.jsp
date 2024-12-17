<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Gerenciamento</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
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
					<li class="nav-item"><a class="nav-link" id="manageContato"
						href="#manageContato">Manage Usuários</a></li>
					<li class="nav-item"><a class="nav-link" id="manageProdutos"
						href="#manageProdutos">Manage Produtos</a></li>
				</ul>
				<button class="btn btn-outline-light" id="login" type="button">Login</button>
				<button class="btn btn-outline-danger" id="logout" type="button">Logout</button>

			</div>
		</div>
	</nav>

	<!-- Modal para update user -->
	<div class="modal fade" id="updateUserModal" tabindex="-1"
		aria-labelledby="updateUserModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="updateUserModalLabel">Atualizar
						Usuário</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<form id="updateUserForm">
						<div class="mb-3">
							<label for="updateEmail" class="form-label">Email</label> <input
								type="email" class="form-control" id="updateEmail" required>
						</div>
						<div class="mb-3">
							<label for="updateUsername" class="form-label">Nome de
								Usuário</label> <input type="text" class="form-control"
								id="updateUsername" required>
						</div>
						<div class="mb-3">
							<label for="updateCep" class="form-label">CEP</label> <input
								type="text" class="form-control" id="updateCep" required>
						</div>
						<input type="hidden" id="updateUserId">
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">Cancelar</button>
					<button type="button" class="btn btn-primary" id="saveUpdateBtn">Salvar</button>
				</div>
			</div>
		</div>
	</div>
	

	<div id="usersSection" class="container my-5">
		<h3 class="text-center fw-bold mb-4 text-primary">User Management</h3>
		<div class="card shadow-lg border-0">
			<div class="card-header bg-primary text-white text-center">
				<h5 class="m-0">User List</h5>
			</div>
			<div class="table-responsive">
				<table id="usersTable"
					class="table table-striped table-hover table-borderless align-middle mb-0">
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
					</tbody>
				</table>
			</div>
			<div class="card-footer text-muted text-center">
				<small>&copy; 2024 Loja Company</small>
			</div>
		</div>
	</div>
	<div id="productsSection" class="container my-5">
		<h3 id="manageProdutos" class="text-center fw-bold mb-4 text-primary">Products
			Management</h3>
		<div class="card shadow-lg border-0">
			<div class="card-header bg-primary text-white text-center">
				<h5 class="m-0">Product List</h5>

			</div>
			<div class="row row-cols-1 row-cols-md-3 g-4"></div>

			<div class="card-footer text-muted text-center">
				<small>&copy; 2024 Loja Company</small>
			</div>
		</div>
	</div>
	<script>
	var token = localStorage.getItem('token');

	$(document).ready(function(){
		if(!token){
            $(location).attr('href', './login.jsp');
            $("#manageContato").addClass("d-none");
            $("#manageProdutos").addClass("d-none");
            $("#logout").addClass("d-none");
		}
		if(token){
			$("#login").addClass("d-none");
		}
		$.ajax({
		    url: "http://localhost:8080/FirstProjeto/products/get",
		    type: "GET",
		    dataType: "json",
		    success: function(data) {
		   
		      const productContainer = $(".row");
		      productContainer.empty();

		      data.forEach(function(product) {
		    	 console.log(product.name);
		    	    console.log(typeof product.name);
					console.log(product.image_path);
		    	    const productCard = 
		  	    	  `<div class="col"> 
		  	    	    <div class="card h-100">
		                <img style="height: 300px; object-fit: cover;" src=\${product.image_path}>
		  	    	      <div class="card-body">
		  	    	        <h5 class="card-title">\${product.name}</h5>
		  	    	        <p class="card-text"> \${product.description}</p>
		  	    	        <button data-id=\${product.idProduct} class="btn btn-danger deleteProduct">Deletar</button> 
		  	    	      </div> 
		  	    	    </div> 
		  	    	  </div>`;
		        productContainer.append(productCard);
		      });
		      $('.deleteProduct').on('click', function () {
                  const userId = $(this).data('id');
                  deleteProduct(userId);
              });
              $('.updateProduct').on('click', function () {
                  const userId = $(this).data('id');
                  const username = $(this).data('username');
                  const email = $(this).data('email');
                  const cep = $(this).data('cep');
                  updateUser(userId, email, username, cep);
              });
		    },
		    error: function(xhr, status, error) {
		      alert("Ocorreu um erro ao consumir produtos: " + error);
		    }
		  });
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
                                <button class="btn btn-danger btn-sm deleteBtn" data-id=\${user.user_id}>Deletar</button>
                                <button class="btn btn-warning btn-sm updateBtn" data-id=\${user.user_id}
                                    data-email=\${user.email} 
                                    data-username=\${user.username} 
                                    data-cep=\${user.cep}>Editar</button>
                            </td>
                            
                        </tr>
                    `);
                });

                $('.deleteBtn').on('click', function () {
                    const userId = $(this).data('id');
                    deleteUser(userId);
                });
                $('.updateBtn').on('click', function () {
                    const userId = $(this).data('id');
                    const username = $(this).data('username');
                    const email = $(this).data('email');
                    const cep = $(this).data('cep');
                    updateUser(userId, email, username, cep);
                });
            },
            error: function () {
                alert('Error fetching users');
            }
        });
		
	})
	function deleteProduct(id){
		if (confirm("Tem certeza que deseja excluir este produto?")) {
			$.ajax({
				url: 'http://localhost:8080/FirstProjeto/products?idProduct=' + id,
				type: "DELETE",
				headers: { Authorization: `Bearer \${token}` },
				success: function() {
					alert("Produto removido com sucesso!");
					location.reload();
				},
				error: function(xhr) {
					if (xhr.status === 400) {
						alert("Erro: ID do produto não fornecido.");
					} else if (xhr.status === 500) {
						alert("Erro no servidor ao remover o usuário.");
					} else if (xhr.status === 401) {
						alert("Não autorizado. Verifique seu token.");
					} else {
						alert("Erro desconhecido.");
					}
				}
			});
		}
	}
	function deleteUser(id){
		if (confirm("Tem certeza que deseja excluir este usuário?")) {
			console.log('http://localhost:8080/FirstProjeto/users?user_id=' + id);
			$.ajax({
				url: 'http://localhost:8080/FirstProjeto/users?user_id=' + id,
				type: "DELETE",
				headers: { Authorization: `Bearer \${token}` },
				success: function() {
					alert("Usuário removido com sucesso!");
					location.reload();
				},
				error: function(xhr) {
					if (xhr.status === 400) {
						alert("Erro: ID do usuário não fornecido.");
					} else if (xhr.status === 500) {
						alert("Erro no servidor ao remover o usuário.");
					} else if (xhr.status === 401) {
						alert("Não autorizado. Verifique seu token.");
					} else {
						alert("Erro desconhecido.");
					}
				}
			});
		}
	}
	function updateUser(userid, email, username, cep){

	    $('#updateUserId').val(userid);
	    $('#updateEmail').val(email);
	    $('#updateUsername').val(username);
	    $('#updateCep').val(cep);

	    $('#updateUserModal').modal('show');
	}
	$('#saveUpdateBtn').on('click', function () {
	    const userId = $('#updateUserId').val();
	    const email = $('#updateEmail').val();
	    const username = $('#updateUsername').val();
	    const cep = $('#updateCep').val();

	    const url = `http://localhost:8080/FirstProjeto/users?user_id=\${userId}&email=\${encodeURIComponent(email)}&username=\${encodeURIComponent(username)}&cep=\${encodeURIComponent(cep)}`;

	    $.ajax({
	        url: url,
	        type: "PUT",
	        headers: { Authorization: `Bearer \${token}` },
	        success: function () {
	            alert("Usuário atualizado com sucesso!");
	            $('#updateUserModal').modal('hide');
	            location.reload();
	        },
	        error: function (xhr) {
	            if (xhr.status === 400) {
	                alert("Erro: Informações obrigatórias não fornecidas.");
	            } else if (xhr.status === 500) {
	                alert("Erro no servidor ao atualizar o usuário.");
	            } else if (xhr.status === 401) {
	                alert("Não autorizado. Verifique seu token.");
	            } else {
	                alert("Erro desconhecido.");
	            }
	        }
	    });
	});

	
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