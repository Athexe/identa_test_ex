<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
    <script>
        var stompClient = null;
        function connect() {
            var socket = new SockJS('/ws');
            socket.onopen = function () {
                console.log('WebSocket connection opened');
            };
            stompClient = Stomp.over(socket);
            stompClient.connect({}, function (frame) {
                stompClient.subscribe('/topic/orders', function (message) {
                    if (message.body === 'update') {
                        console.log('Received update message');
                        location.reload();
                    }
                });
            });
        }

        connect();
    </script>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <title>Orders</title>
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
        .width300{
            width:300px;
        }
        .width100{
            width:100px;
        }
        .width150{
            width:100px;
        }
        td{
            border: 1px solid black;
        }
        th{
            border: 1px solid black;
        }
        table{
            border-collapse: separate !important;
        }

    </style>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col">
            <div class="btn-container">
                <div class="centered-buttons">
                    <div class="mt-3">
                        <h2>Waiting Orders</h2>
                    </div>
                    <div class="m-4">
                        <table>
                        <tr>
                            <th class="width100">Username</th>
                            <th class="width300">Description</th>
                            <th class="width150">Total Cost</th>
                            <th class="width150">Time</th>
                            <th class="width100">To Go</th>
                            <th class="width100">Status</th>
                            <th class="width300">Operations</th>
                        </tr>
                        <c:forEach var="order" items="${allOrdersWithStatusWaiting}">
                            <c:url var="confirmButton" value="confirmOrder">
                                <c:param name="orderId" value="${order.id}"/>
                            </c:url>
                            <c:url var="updateButton" value="updateInfo">
                                <c:param name="orderId" value="${order.id}"/>
                            </c:url>
                            <c:url var="deleteButton" value="deleteOrder">
                                <c:param name="orderId" value="${order.id}"/>
                            </c:url>

                            <tr>
                                <td>${order.username}</td>
                                <td>${order.description}</td>
                                <td>${order.totalcost}</td>
                                <td>${order.timestamp}</td>
                                <td>${order.togo}</td>
                                <td>${order.status}</td>
                                <td>
                                    <input type="button" class="btn btn-outline-success" value="Confirm" onclick="window.location.href='${confirmButton}'">
                                    <input type="button" class="btn btn-outline-primary" value="Update" onclick="window.location.href='${updateButton}'">
                                    <input type="button" class="btn btn-outline-danger" value="Remove" onclick="window.location.href='${deleteButton}'">
                                </td>
                            </tr>
                        </c:forEach>
                        </table>
                    </div>
                    <div class="mt-3">
                        <h2>Confirmed Orders</h2>
                    </div>
                    <div class="m-4">
                        <table>
                            <tr>
                                <th class="width100">Username</th>
                                <th class="width300">Description</th>
                                <th class="width150">Total Cost</th>
                                <th class="width150">Time</th>
                                <th class="width100">To Go</th>
                                <th class="width100">Status</th>
                                <th class="width300">Oparations</th>
                            </tr>
                            <c:forEach var="order" items="${allOrdersWithStatusConfirmed}">
                                <c:url var="updateButton" value="/updateInfo">
                                    <c:param name="orderId" value="${order.id}"/>
                                </c:url>
                                <c:url var="deleteButton" value="/deleteOrder">
                                    <c:param name="orderId" value="${order.id}"/>
                                </c:url>

                                <tr>
                                    <td>${order.username}</td>
                                    <td>${order.description}</td>
                                    <td>${order.totalcost}</td>
                                    <td>${order.timestamp}</td>
                                    <td>${order.togo}</td>
                                    <td>${order.status}</td>
                                    <td>
                                        <input type="button" class="btn btn-outline-primary" value="Update" onclick="window.location.href='${updateButton}'">
                                        <input type="button" class="btn btn-outline-danger" value="Remove" onclick="window.location.href='${deleteButton}'">
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                    <div>
                        <input class="btn btn-secondary m-3" type="button" value="Back" onclick="window.location.href='/'">
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>