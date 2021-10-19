<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>TiendaTIC</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU"
	crossorigin="anonymous">
	<link rel="stylesheet" href="css/estilos_1.css">
<link rel="stylesheet" href="css/estilos_2.css">
<link rel="stylesheet" href="css/estilos_3.css">
</head>
<body>
	<div class="form-group text-center">
	<h1>BIENVENIDO</h1>
	</div>
	<div class="container mt-4 col-lg-4">
		<div class="card col-sm-10">
			<div class="card-body">
				<form class="form-sign" method="get" action="./DemoServlet">  
				     
				<!-- ------------------------------------------------------------------------------- -->
				<div class="card-header">
				<h3>Inicio sesion TiendaTIC</h3>
		


					</div>
					
					<div class="form-group">
						 <input type="text" name="txtusuario"
							class="form-control"  placeholder="Nombre de usuario">
					</div>
					<br>
					
					<div class="form-group">
						 <input type="password"
							name="txtpassword" class="form-control"  placeholder="Password">
					</div>

					<div>				
						<input type="reset" name= "accion" value="Borrar" class="btn btn-success btn-block btn-dark">
						<input type="submit" name= "accion" value="Ingresar" class="btn btn-success btn-block btn-dark">
					</div>
				</form>
			</div>
		</div>
	</div>

</body>
</html>