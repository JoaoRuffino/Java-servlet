<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<title>Teste</title>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
	crossorigin="anonymous"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"
	integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo="
	crossorigin="anonymous"></script>
</head>
<body>
	<div class="container mt-3" id="frmLogin">
		<h2>Autenticação</h2>
		<form action="users" class="p-5">
			<div class="mb-3 mt-3">
				<label for="email" class="form-label">Email:</label> <input
					type="email" class="form-control" id="email"
					placeholder="Enter email" name="email">
			</div>
			<div class="mb-3">
				<label for="pwd" class="form-label">Password:</label> <input
					type="password" class="form-control" id="pwd"
					placeholder="Enter password" name="pswd">
			</div>

			<button type="button" id="btn-login" class="btn btn-primary">Submit</button>
			<a id="linkLembrarSenha" class="btn btn-primary" href="#">Lembrar
				Senha</a> <a id="linkCadUser" class="btn btn-primary" href="#">Cadastrar
				Usuário</a>
		</form>


		
	</div>
	
	<div class="container mt-3" id="loggedIn">
		<h2>Fazer Requisição GET com AJAX</h2>
		<button class="btn btn-primary mb-5" onclick="fetchUser()">Buscar Usuários</button>

		<h3>Resultado:</h3>
		<div id="result">
		</div>
	</div>
	
	
	<div class="container mt-3" id="frmLembrarSenhaArea">
		<h2>Lembrar Senha</h2>
		<form action="/action_page.php" class="p-5">
			<div class="mb-3 mt-3">
				<label for="email" class="form-label">Email:</label> <input
					type="email" class="form-control" id="email"
					placeholder="Enter email" name="email">
			</div>


			<button type="button" id="btn-login" class="btn btn-primary">Submit</button>
		</form>
	</div>

	<div class="container mt-3" id="frmCadUser">
		<h2>Cadastro do Usuário</h2>
		<form action="/action_page.php" class="p-5">
			<label>Cep:</label> <input class="form-control" name="cep"
				type="text" id="cep" value="" size="10" maxlength="9" /> <br /> <label>Rua:
			</label> <input class="form-control" name="rua" ype="text" id="rua" size="60" /><br />
			<label>Bairro:</label> <input class="form-control" name="bairro"
				type="text" id="bairro" size="40" /> <br /> <label>Cidade:
			</label><input class="form-control" name="cidade" type="text" id="cidade"
				size="40" /><br /> <label>Estado:</label> <input
				class="form-control" name="uf" type="text" id="uf" size="2" /><br />
			<label>IBGE:</label> <input class="form-control" name="ibge"
				type="text" id="ibge" size="8" /> <br />

			<button type="button" id="btn-login" class="btn btn-primary">Submit</button>
		</form>
	</div>
</body>
<script>

function fetchUser() {
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "users", true); 
    
    
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4 && xhr.status === 200) {
            var response = JSON.parse(xhr.responseText);  
            
            var resultDiv = document.getElementById("result");
            resultDiv.innerHTML = "";  

            response.forEach(function(user) {
                resultDiv.innerHTML += 
                    "User ID: " + user.user_id + "<br>" +
                    "Username: " + user.username + "<br>" +
                    "Lastname: " + user.lastname + "<br><br>";
            });
        }
    };
    xhr.send();
}

function validateUser(){
	
}

$(document).ready(function() {
	
	
	

    function limpa_formulário_cep() {
        // Limpa valores do formulário de cep.
        $("#rua").val("");
        $("#bairro").val("");
        $("#cidade").val("");
        $("#uf").val("");
        $("#ibge").val("");
    }
    
    
    //$("#frmLogin").hide();
    $("#frmLembrarSenhaArea").hide();
    $("#frmCadUser").hide();
    $("#loggedIn").hide();


    $("#linkLembrarSenha").click(() => {
    	$("#frmLogin").hide();
        $("#frmLembrarSenhaArea").show();
        $("#frmCadUser").hide();
        $("#loggedIn").hide();

    });
    
    
    $("#linkCadUser").click(() => {
    	$("#frmLogin").hide();
        $("#frmLembrarSenhaArea").hide();
        $("#frmCadUser").show();
        $("#loggedIn").hide();

    });
    
    
    
    //Quando o campo cep perde o foco.
    $("#cep").blur(function() {

        //Nova variável "cep" somente com dígitos.
        var cep = $(this).val().replace(/\D/g, '');

        //Verifica se campo cep possui valor informado.
        if (cep != "") {

            //Expressão regular para validar o CEP.
            var validacep = /^[0-9]{8}$/;

            //Valida o formato do CEP.
            if(validacep.test(cep)) {

                //Preenche os campos com "..." enquanto consulta webservice.
                $("#rua").val("...");
                $("#bairro").val("...");
                $("#cidade").val("...");
                $("#uf").val("...");
                $("#ibge").val("...");

                //Consulta o webservice viacep.com.br/
                $.getJSON("https://viacep.com.br/ws/"+ cep +"/json/?callback=?", function(dados) {

                    if (!("erro" in dados)) {
                        //Atualiza os campos com os valores da consulta.
                        $("#rua").val(dados.logradouro);
                        $("#bairro").val(dados.bairro);
                        $("#cidade").val(dados.localidade);
                        $("#uf").val(dados.uf);
                        $("#ibge").val(dados.ibge);
                    } //end if.
                    else {
                        //CEP pesquisado não foi encontrado.
                        limpa_formulário_cep();
                        alert("CEP não encontrado.");
                    }
                });
            } //end if.
            else {
                //cep é inválido.
                limpa_formulário_cep();
                alert("Formato de CEP inválido.");
            }
        } //end if.
        else {
            //cep sem valor, limpa formulário.
            limpa_formulário_cep();
        }
    });
});

</script>
</html>