<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="en">
	<head>
		<meta charset="UTF-8">
			<meta name="viewport" content="width=device-width, initial-scale=1.0">
				<title>Login</title>
				<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
					<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
					<link rel="stylesheet" href="css/loginPageStyle.css">
	</head>
	<body>
						<div class="container-xxl">
							<div class="row justify-content-center">
								<div class="col-2 align-self-center">
									<img class="logo" src="img/logo.png">
									</div>
									<div class="col-4">
										<form name="LoginForm" action="authorization" method="POST">
											<input type="hidden" name="command" value="login">
												<div class="row mb-3">
													<label for="inputLogin" class="col-sm-2 col-form-label">Login</label>
													<div class="col-sm-6">
														<input type="text" name="login" id="inputLogin">
														</div>
													</div>
													<div class="row mb-4">
														<label for="inputPassword" class="col-sm-2 col-form-label">Password</label>
														<div class="col-sm-6">
															<input type="password" name="password" id="inputPassword">
															</div>
														</div>
														<button type="submit" class="btn btn-primary">Login</button>
													</form>
												</div>
											</div>
										</div>
									</body>
								</html>