<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>AngularJS1 Seed : Metabuild</title>

    <link rel="icon" href="${pageContext.request.contextPath }/resources/files/assets/images/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/files/bower_components/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/files/assets/pages/waves/css/waves.min.css" type="text/css" media="all">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/files/assets/icon/feather/css/feather.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/files/assets/icon/themify-icons/themify-icons.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/files/assets/icon/icofont/css/icofont.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/files/assets/icon/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/files/assets/css/style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/files/assets/css/pages.css">

    <script src="${pageContext.request.contextPath }/resources/files/bower_components/angular/angular.min.js"></script>
    <script src="${pageContext.request.contextPath }/resources/files/bower_components/angular-route/angular-route.min.js"></script>
    <script src="${pageContext.request.contextPath }/resources/files/bower_components/angular-ui-router/release/angular-ui-router.js"></script>
    <script src="${pageContext.request.contextPath }/resources/files/bower_components/angular-touch/angular-touch.js"></script>
    <script src="${pageContext.request.contextPath }/resources/files/bower_components/jquery/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath }/resources/files/bower_components/bootstrap/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath }/resources/files/bower_components/angular-bootstrap/ui-bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath }/resources/files/bower_components/angular-animate/angular-animate.min.js"></script>
    <script src="${pageContext.request.contextPath }/resources/files/bower_components/angular-ui-sortable/sortable.min.js"></script>
    <script src="${pageContext.request.contextPath }/resources/files/bower_components/angular-smart-table/dist/smart-table.js"></script>
    <script src="${pageContext.request.contextPath }/resources/app/app.js"></script>
    <script src="${pageContext.request.contextPath }/resources/app/appController.js"></script>
    <script src="${pageContext.request.contextPath }/resources/app/misun/controller/IntroduceMisunCtrl.js"></script>
    <script src="${pageContext.request.contextPath }/resources/app/misun/service/IntroduceMisunService.js"></script>

    <style>
        .navbar { border-radius:0; }
        /*
         * Jeju Gothic (Korean) http://www.google.com/fonts/earlyaccess
         */
        @font-face {
            font-family: 'Jeju Gothic';
            font-style: normal;
            font-weight: 400;
            src: url(//fonts.gstatic.com/ea/jejugothic/v3/JejuGothic-Regular.eot);
            src: url(//fonts.gstatic.com/ea/jejugothic/v3/JejuGothic-Regular.eot?#iefix) format('embedded-opentype'),
            url(//fonts.gstatic.com/ea/jejugothic/v3/JejuGothic-Regular.woff2) format('woff2'),
            url(//fonts.gstatic.com/ea/jejugothic/v3/JejuGothic-Regular.woff) format('woff'),
            url(//fonts.gstatic.com/ea/jejugothic/v3/JejuGothic-Regular.ttf) format('truetype');
        }

        body {
            font-family: 'Jeju Gothic', sans-serif;
        }
    </style>
</head>
<body></body>

<body ng-app="meta-app" ng-controller="AppCtrl">
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="#">신입교육용</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    자기소개
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a ng-repeat="user in userList" class="dropdown-item" ui-sref="{{user.id}}">{{user.name}} <span class="small-text"> {{user.position}} / {{user.department}}</span></a>
                </div>
            </li>
        </ul>
    </div>
</nav>
<!--  CONTENT -->
<div class="container">
    <div ui-view></div>
</div>

</body>
</html>