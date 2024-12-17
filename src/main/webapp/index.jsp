<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Loja de Produtos</title>
<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">

</head>

<body>


	<!-- Navbar -->
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
				<button class="btn btn-outline-light" id="create" type="button">Criar usuário</button>
				<button class="btn btn-outline-danger" id="logout" type="button">Logout</button>
				
			</div>
		</div>
	</nav>
	<header class="bg-primary text-white text-center py-4">
		<h1>Bem-vindo à Nossa Loja</h1>
		<p>Encontre os melhores produtos aqui!</p>
	</header>
	<!-- Produtos -->
	<main class="container my-5">
		<h2 class="text-center mb-4" id="produtos">Nossos Produtos</h2>
		<div class="row row-cols-1 row-cols-md-3 g-4"></div>

		
	</main>

	<footer class="bg-dark text-white text-center py-4" id="contato">
		<p>&copy; 2024 Nossa Loja. Todos os direitos reservados.</p>
		<p>Email: contato@nossaloja.com | Telefone: (11) 1234-5678</p>
	</footer>

	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

	<script>
$(document).ready(function() {
	var token = localStorage.getItem('token');
	if(!token){
        
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

    	    const productCard = 
	  	    	  `<div class="col"> 
	  	    	    <div class="card h-100">
	                <img style="height: 300px; object-fit: cover;" src=\${product.image_path}>
	  	    	      <div class="card-body">
	  	    	        <h5 class="card-title">\${product.name}</h5>
	  	    	        <p class="card-text"> \${product.description}</p>
	  	    	        <a href="#" class="btn btn-primary">Comprar</a>

	  	    	      </div> 
	  	    	    </div> 
	  	    	  </div>`;
        console.log(productCard);
        productContainer.append(productCard);
      });
    },
    error: function(xhr, status, error) {
      alert("Ocorreu um erro ao consumir produtos: " + error);
    }
  });
});

$("#login").click(function(){
	$(location).attr('href', './login.jsp');

})
$("#create").click(function(){
	$(location).attr('href', './create.jsp');

})
$("#logout").click(function(){
		localStorage.setItem('token', '');
		alert("Você saiu! Até mais.");
        $(location).attr('href', './index.jsp');

	})
</script>

	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>
