<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <title>Make Order</title>
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
                    <div><h2>Order info</h2></div>
                    <form:form action="saveOrder" modelAttribute="order">
                        <form:hidden path = "id"/>
                        <form:hidden path = "username" value = "${authenticatedUser.username}" />
                        <div class="m-3">
                            <h5>Description</h5>
                        </div>
                        <div>
                            <form:input cssStyle="width: 200px;height: 60px" path="description"/>
                        </div>
                        <div class="m-3">
                            <h5>Total Cost</h5>
                        </div>
                        <div>
                            <form:input cssStyle="width: 200px" path="totalcost"/>
                        </div>
                        <div class="m-3">
                            <h5>To Go</h5>
                            <form:checkbox path="togo" value="To Go"/>
                        </div>
                        <div>
                            <input class="btn btn-primary btn-lg m-3" type="submit" value="OK">
                        </div>
                        <div>
                            <input class="btn btn-secondary m-3" type="button" value="Back" onclick="window.location.href='/'">
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>