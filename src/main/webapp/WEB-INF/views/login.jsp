<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-100 font-sans">
<div class="flex items-center justify-center h-screen">
    <form method="post" action="${pageContext.request.contextPath}/login"
          class="bg-white p-8 rounded-lg shadow-md max-w-md w-full">
        <h2 class="text-2xl font-bold text-gray-800 mb-6 text-center">Login</h2>
        <div class="mb-4">
            <label class="block text-gray-700 font-semibold">Email:</label>
            <input type="email" name="email" required class="w-full px-3 py-2 border border-gray-300 rounded-lg"/>
        </div>
        <div class="mb-6">
            <label class="block text-gray-700 font-semibold">Password:</label>
            <input type="password" name="password" required class="w-full px-3 py-2 border border-gray-300 rounded-lg"/>
        </div>
        <c:if test="${not empty param.error}">
            <div class="text-red-500 text-center mb-4">Invalid username or password.</div>
        </c:if>

        <a href="/register">Register</a>
        <button type="submit" class="w-full bg-indigo-600 text-white py-2 rounded-lg hover:bg-indigo-700">
            Login
        </button>
    </form>
</div>
</body>
</html>
