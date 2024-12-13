<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-100 font-sans">
<div class="flex items-center justify-center h-screen">
    <div class="text-center">
        <h1 class="text-4xl font-bold text-gray-800 mb-6">Welcome to Insurance Portal</h1>
        <p class="text-lg text-gray-700 mb-6">Manage your insurances with ease</p>
        <a href="${pageContext.request.contextPath}/insurances/InsuranceForm"
           class="bg-indigo-600 text-white py-3 px-6 rounded-lg hover:bg-indigo-700">Request an Insurance</a>
    </div>
</div>
</body>
</html>
