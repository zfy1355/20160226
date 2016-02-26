<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="${ctx}/login.html" method="post">
		<fieldset>
			<label class="block clearfix"> <span
				class="block input-icon input-icon-right"> <input type="text"
					name="userName" class="form-control" placeholder="用户名" required />
					<i class="icon-user"></i>
			</span>
			</label> <label class="block clearfix"> <span
				class="block input-icon input-icon-right"> <input
					type="password" name="password" class="form-control"
					placeholder="密码" required /> <i class="icon-lock"></i>
			</span>
		                          <%session.setAttribute("userName", "112312"); %>                        
			</label>
			<c:if test="${not   empty  message}">
				<label class="block clearfix"> <span
					class="alert alert-error"> <a class="close"
						data-dismiss="alert">×</a> <span class="red">${message}</span>
				</span>
				</label>
			</c:if>
			<div class="space"></div>
			<div class="clearfix">
				<%--		<label class="inline">
														<input type="checkbox" class="ace" name="rememberMe" value="true" />
														<span class="lbl"> 记住我</span>
													</label>
--%>
				<button type="submit"
					class="width-35 pull-right btn btn-sm btn-primary">
					<i class="icon-key"></i> 登 录
				</button>
			</div>

			<div class="space-4"></div>
		</fieldset>
	</form>

</body>
</html>