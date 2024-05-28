<!doctype html>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
	<title><g:message code='springSecurity.login.title'/> - Mónaco S.A. de C.V.</title>
	<meta name="viewport" content="width=device-width, initial-scale=1"/>
	<link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap" rel="stylesheet">
	<asset:link rel="icon" href="favicon.ico" type="image/x-ico"/>

	<asset:stylesheet src="application.css"/>
</head>

<body class="d-flex flex-column vh-100">
	<header class="header w-100 text-white d-flex my-5 row" style="padding: 0; max-width: none">
		<div class="col-7 header-login row justify-content-center align-items-center py-4">
			<h1 class="m-0" style="font-size: 28px">BIENVENIDO A MÓNACO S.A. DE C.V</h1>
		</div>
		<div class="col-5 logo row justify-content-center align-items-center">
			<asset:image src="logo.png" alt="Mónaco S.A. de C.V."  class="img-fluid" style="height: 100px;"/>
		</div>
	</header>

	<div class="w-100 d-flex align-items-center justify-content-center">
		<div class="card card-signin">
			<div class="card-body">
				<h5 class="card-title text-center">INICIAR SESIÓN</h5>
				<g:if test='${flash.message}'>
					<div class="alert alert-danger" role="alert">${flash.message}</div>
				</g:if>
				<form class="form-signin" action="${postUrl ?: '/login/authenticate'}" method="POST" id="loginForm" autocomplete="off">
					<div class="form-group">
						<label for="username">Usuario</label>
						<input type="text" class="form-control" name="${usernameParameter ?: 'username'}" id="username" autocapitalize="none" placeholder="Ingrese su número de empleado"/>
					</div>
					<g:if test='${flash.usernameError}'>
						<div class="text-danger">${flash.usernameError}</div>
					</g:if>

					<div class="form-group">
						<label for="password">Contraseña</label>
						<input type="password" class="form-control" name="${passwordParameter ?: 'password'}" id="password" placeholder="Ingrese su contraseña"/>
						<i id="passwordToggler" title="toggle password display" onclick="passwordDisplayToggle()">&#128065;</i>
					</div>
					<g:if test='${flash.passwordError}'>
						<div class="text-danger">${flash.passwordError}</div>
					</g:if>

					<button id="submit" class="btn btn-lg btn-warning btn-block text-uppercase text-white" type="submit">Ingresar</button>
				</form>
			</div>
		</div>
	</div>

	<div id="spinner" class="spinner" style="display:none;">
		<g:message code="spinner.alt" default="Loading&hellip;"/>
	</div>

	<script type="text/javascript">
		document.addEventListener("DOMContentLoaded", function(event) {
			document.forms['loginForm'].elements['username'].focus();
		});
		function passwordDisplayToggle() {
			var toggleEl = document.getElementById("passwordToggler");
			var eyeIcon = '\u{1F441}';
			var xIcon = '\u{2715}';
			var passEl = document.getElementById("password");
			if (passEl.type === "password") {
				toggleEl.innerHTML = xIcon;
				passEl.type = "text";
			} else {
				toggleEl.innerHTML = eyeIcon;
				passEl.type = "password";
			}
		}
	</script>

<asset:javascript src="application.js"/>

</body>
</html>