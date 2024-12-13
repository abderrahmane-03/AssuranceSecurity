<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Insurance Form</title>
  <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
  <script>
    function toggleInsuranceFields() {
      var insuranceType = document.getElementById("insuranceType").value;

      document.getElementById("homeFields").classList.add("hidden");
      document.getElementById("carFields").classList.add("hidden");
      document.getElementById("healthFields").classList.add("hidden");
      document.getElementById("ageField").classList.add("hidden");

      if (insuranceType === "Home Insurance") {
        document.getElementById("homeFields").classList.remove("hidden");
        document.getElementById("age").removeAttribute("required");
      } else if (insuranceType === "Car Insurance") {
        document.getElementById("carFields").classList.remove("hidden");
        document.getElementById("ageField").classList.remove("hidden");
        document.getElementById("age").setAttribute("required", "true");
      } else if (insuranceType === "Health Insurance") {
        document.getElementById("healthFields").classList.remove("hidden");
        document.getElementById("ageField").classList.remove("hidden");
        document.getElementById("age").setAttribute("required", "true");
      }
    }



    function updateFormAction() {
      var selectedInsurance = document.getElementById("insuranceType").value;
      var form = document.getElementById("insuranceForm");
      if (selectedInsurance === "Home Insurance") {
        form.action = "${pageContext.request.contextPath}/insurances/RequestedHomeInsurance";
      } else if (selectedInsurance === "Car Insurance") {
        form.action = "${pageContext.request.contextPath}/insurances/RequestedCarInsurance";
      } else if (selectedInsurance === "Health Insurance") {
        form.action = "${pageContext.request.contextPath}/insurances/RequestedHealthInsurance";

      }
    }
  </script>
</head>
<body class="bg-gray-100 min-h-screen flex items-center justify-center">
<form id="insuranceForm" method="post" onsubmit="updateFormAction()" class="w-full max-w-lg bg-white p-8 shadow-lg rounded-lg">
  <h2 class="text-2xl font-semibold text-gray-800 text-center mb-6">Insurance Request Form</h2>

  <div class="mb-6">
    <label class="block text-gray-600 text-sm font-semibold mb-2" for="insuranceType">Insurance Type:</label>
    <select id="insuranceType" name="insuranceType" onchange="toggleInsuranceFields()"
            class="block w-full px-4 py-3 border border-gray-300 rounded-md text-gray-700 focus:outline-none focus:ring-2 focus:ring-indigo-500 transition ease-in-out duration-150">
      <option value="" disabled selected>Select Insurance Type</option>
      <option value="Home Insurance">Home Insurance</option>
      <option value="Car Insurance">Car Insurance</option>
      <option value="Health Insurance">Health Insurance</option>
    </select>

  </div>

  <div id="ageField" class="hidden mb-6">
    <label class="block text-gray-600 text-sm font-semibold mb-2" for="age">Age:</label>
    <input type="number" id="age" name="age" required min="18" max="100"
           class="block w-full px-4 py-3 border border-gray-300 rounded-md text-gray-700 focus:outline-none focus:ring-2 focus:ring-indigo-500 transition ease-in-out duration-150"/>
  </div>


  <div id="homeFields" class="hidden">
    <h3 class="text-lg font-medium text-gray-700 mb-4">Home Insurance Details</h3>
    <div class="mb-4">
      <label class="block text-gray-600 text-sm font-semibold mb-2" for="assetValue">Asset Value:</label>
      <input type="number" id="assetValue" name="assetValue" min="10000"
             class="block w-full px-4 py-3 border border-gray-300 rounded-md text-gray-700 focus:outline-none focus:ring-2 focus:ring-indigo-500 transition ease-in-out duration-150"/>
    </div>
    <div class="mb-4">
      <label class="block text-gray-600 text-sm font-semibold mb-2" for="assetType">Asset Type:</label>
      <select id="assetType" name="assetType" class="block w-full px-4 py-3 border border-gray-300 rounded-md text-gray-700 focus:outline-none focus:ring-2 focus:ring-indigo-500 transition ease-in-out duration-150">
        <option value="apartment">apartment</option>
        <option value="house">house</option>
      </select>
    </div>
    <div class="mb-4">
      <label class="block text-gray-600 text-sm font-semibold mb-2" for="localization">Localization:</label>
      <select id="localization" name="localization"  class="block w-full px-4 py-3 border border-gray-300 rounded-md text-gray-700 focus:outline-none focus:ring-2 focus:ring-indigo-500 transition ease-in-out duration-150" >
        <option value="ZoneArisque">Zone à risque</option>
        <option value="address">Other</option>
      </select>
    </div>
    <div class="mb-4">
      <label class="block text-gray-600 text-sm font-semibold mb-2" for="securitySystem">Security System:</label>

      <select id="securitySystem" name="securitySystem"  class="block w-full px-4 py-3 border border-gray-300 rounded-md text-gray-700 focus:outline-none focus:ring-2 focus:ring-indigo-500 transition ease-in-out duration-150" >
        <option value="Camera">Camera</option>
        <option value="SecuritySystem">Security System</option>
        <option value="">None</option>
      </select></div>
  </div>

  <div id="carFields" class="hidden">
    <h3 class="text-lg font-medium text-gray-700 mb-4">Car Insurance Details</h3>
    <div class="mb-4">
      <label class="block text-gray-600 text-sm font-semibold mb-2" for="vehicleType">Vehicle Type:</label>
      <select  class="block w-full px-4 py-3 border border-gray-300 rounded-md text-gray-700 focus:outline-none focus:ring-2 focus:ring-indigo-500 transition ease-in-out duration-150" id="vehicleType" name="vehicleType">
        <option value="lux">lux</option>
        <option value="normal">normal</option>
      </select>
    </div>
    <div class="mb-4">
      <label class="block text-gray-600 text-sm font-semibold mb-2" for="vehicleUsing">Vehicle Usage:</label>
      <select id="vehicleUsing" name="vehicleUsing"  class="block w-full px-4 py-3 border border-gray-300 rounded-md text-gray-700 focus:outline-none focus:ring-2 focus:ring-indigo-500 transition ease-in-out duration-150" >
        <option value="private">private</option>
        <option value="professional">professional</option>
      </select>
    </div>
    <div class="mb-4">
      <label class="block text-gray-600 text-sm font-semibold mb-2" for="driverHistory">Driver History last 3 years:</label>
       <select id="driverHistory" name="driverHistory"  class="block w-full px-4 py-3 border border-gray-300 rounded-md text-gray-700 focus:outline-none focus:ring-2 focus:ring-indigo-500 transition ease-in-out duration-150" >
        <option value="professional">accidents</option>
        <option value="infraction">infraction</option>
         <option value="without">without</option>
      </select>
    </div>
  </div>

  <div id="healthFields" class="hidden">
    <h3 class="text-lg font-medium text-gray-700 mb-4">Health Insurance Details</h3>
    <div class="mb-4">
      <label class="block text-gray-600 text-sm font-semibold mb-2" for="typeCoverage">Type of Coverage:</label>
      <select id="typeCoverage" name="typeCoverage"
              class="block w-full px-4 py-3 border border-gray-300 rounded-md text-gray-700 focus:outline-none focus:ring-2 focus:ring-indigo-500 transition ease-in-out duration-150">
        <option value="basic">Basic</option>
        <option value="Premium">Premium</option>
      </select>
    </div>
    <div class="mb-4">
      <label class="block text-gray-600 text-sm font-semibold mb-2" for="healthStatus">Health Status:</label>
      <select id="healthStatus" name="healthStatus"
              class="block w-full px-4 py-3 border border-gray-300 rounded-md text-gray-700 focus:outline-none focus:ring-2 focus:ring-indigo-500 transition ease-in-out duration-150">
        <option value="Medical">Medical History</option>
        <option value="Antecedents">Past Medical Issues</option>
      </select>
    </div>
  </div>

  <div class="text-center">
    <button type="submit"
            class="w-full bg-indigo-600 text-white py-3 rounded-md font-semibold hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-indigo-500 transition ease-in-out duration-150">
      Submit Request
    </button>
  </div>
</form>
</body>
</html>
