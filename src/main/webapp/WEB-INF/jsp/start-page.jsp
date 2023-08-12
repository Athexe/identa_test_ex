<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <title>Menu</title>
    <style>
        body {
            background-color: #f8f9fa;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .btn-container {
            text-align: center;
        }
        .centered-buttons {
            display: flex;
            flex-direction: column;
            align-items: center;
        }
        .btn-primary {
            background-color: #007bff;
            border-color: #007bff;
            width:200px;
        }
        .btn-secondary {
            background-color: #6c757d;
            border-color: #6c757d;
            width:200px;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col">
            <div class="btn-container">
                <div class="centered-buttons">
                    <button class="btn btn-primary btn-lg m-2" onclick="window.location.href='/make-order'">Make Order</button>
                    <security:authorize access="hasRole('EMPLOYEE')">
                        <button class="btn btn-secondary btn-lg m-2" onclick="window.location.href='/orders'">Orders</button>
                    </security:authorize>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>