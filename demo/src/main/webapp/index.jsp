<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>

    <style>
        .divlap4 a{
            margin-right: 10px;
        }
    </style>
</head>
<body>
<h1 style="color: red;text-align: center"><%= "BÀI TẬP LAB 4!" %>
</h1>
<br/>
<div class="d-flex justify-content-center divlap4" >
    <a class="btn btn-primary" href="login">BÀI 1</a>
    <a class="btn btn-success" href="bai2">BÀI 2</a>
    <a class="btn btn-danger" href="product">BÀI 3</a>
    <a class="btn btn-warning" href="upload">BÀI 4</a>
    <a class="btn btn-light" href="">BÀI 5</a>
    <a class="btn btn-secondary" href="">BÀI 6</a>


</div>
</body>
</html>