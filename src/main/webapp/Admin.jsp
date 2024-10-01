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
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
	crossorigin="anonymous"></script>
<title>Admin Page</title>
</head>
<body class="d-flex flex-column justify-content-center align-items-center p-5">

    <div>
        <div id="result" class="w-75">
            <h1>Users</h1>
            <table id="table_users" class="table w-100">
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Email</th>
                </tr>
            </table>
        </div>
    </div>

</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script>

function fetchUser() {
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "users", true);
    
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4 && xhr.status === 200) {
            var response = JSON.parse(xhr.responseText);
            
            var table = document.getElementById("table_users");

            response.forEach(function(user) {
                var row = "<tr>" + 
                          "<td>" + user.user_id + "</td>" +
                          "<td>" + user.username + " " + user.lastname + "</td>" +
                          "<td>" + user.email + "</td>" +
                          "</tr>";
                
                table.innerHTML += row;
            });
        }
    };
    xhr.send();
}

$(document).ready(function() {
    fetchUser();
});
</script>
</html>
