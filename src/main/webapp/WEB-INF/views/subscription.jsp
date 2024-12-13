<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Subscription List</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-100 font-sans">
<div class="container mx-auto mt-10">
    <h1 class="text-2xl font-semibold text-gray-800 mb-6">Your Subscriptions</h1>
    <div class="space-y-4">
        <c:forEach var="subscription" items="${subscriptions}">
            <div class="bg-white p-4 shadow rounded-lg flex items-center justify-between">
                <div>
                    <p class="text-gray-700 font-semibold">Status: ${subscription.status}</p>
                    <p class="text-gray-500">Date: ${subscription.subscriptionDate}</p>
                    <p class="text-gray-500">Documents Provided: ${subscription.documentsProvided}</p>
                </div>

                <ul>
                    <li>
                        <strong>Subscription ID:</strong> ${subscription.id}<br>
                        <strong>Devis ID:</strong> ${subscription.devis.id}<br>

                        <c:if test="${subscription.devis.carInsurance != null}">
                            <strong>age:</strong>${subscription.devis.carInsurance.age}
                            <br><strong>driver history:</strong>${subscription.devis.carInsurance.driverHistory}
                            <br><strong>vehicle type:</strong>${subscription.devis.carInsurance.vehicleType}
                            <br><strong>vehicle using:</strong>${subscription.devis.carInsurance.vehicleUsing}
                        </c:if>
                        <c:if test="${subscription.devis.homeInsurance != null}">
                            <strong>Asset type:</strong>${subscription.devis.homeInsurance.assetType}
                            <br>
                            <strong>Asset value:</strong>${subscription.devis.homeInsurance.assetValue}
                            <br>
                            <strong>localization:</strong>${subscription.devis.homeInsurance.localization}
                            <br>
                            <strong>security system:</strong>${subscription.devis.homeInsurance.securitySystem}
                        </c:if>
                        <c:if test="${subscription.devis.healthInsurance != null}">
                            <strong>age: </strong>${subscription.devis.healthInsurance.age}
                            <br>
                            <strong>type Coverage:</strong> ${subscription.devis.healthInsurance.typeCoverage}
                            <br>
                            <strong>health Status:</strong> ${subscription.devis.healthInsurance.healthStatus}
                        </c:if>
                        <br>
                    </li>
                </ul>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>
