<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html lang="en">
<head>
    <meta charset="utf-8">
</head>
<body>
<div class="row m-t-5">
    <div class="col-12 ">
        <h4>{{ $root.commonText }}</h4>
    </div>
</div>
<div class="row simple-cards users-card">
    <div class="col-md-4 col-lg-4 col-sm-12">
        <div class="card user-card">
            <div class="card-header-img">
                <img class="img-fluid img-radius" src="../../../resources/files/assets/images/user-card/img-round2.jpg" alt="card-img">
                <h4>김미선</h4>
                <h5>kimmi4823@naver.com</h5>
                <h6>지능융합기술실</h6>
            </div>
            <div class="row justify-content-center">
                <div class="col-auto">
                    <div class="label-icon">
                        <i class="icofont icofont-bell-alt"></i>
                        <label class="badge badge-primary badge-top-right">9</label>
                    </div>
                </div>
                <div class="col-auto">
                    <div class="label-icon">
                        <i class="icofont icofont-heart"></i>
                        <label class="badge badge-success badge-top-right">9</label>
                    </div>
                </div>
                <div class="col-auto">
                    <div class="label-icon">
                        <i class="icofont icofont-bag-alt"></i>
                        <label class="badge badge-danger badge-top-right">9</label>
                    </div>
                </div>
                <div class="col-auto">
                    <div class="label-icon">
                        <i class="icofont icofont-ui-message"></i>
                        <label class="badge badge-info badge-top-right">9</label>
                    </div>
                </div>
            </div>
            <p>{{ introduce.text }}</p>
            <div>
                <button type="button" class="btn btn-success waves-effect waves-light"><i class="icofont icofont-user m-r-5"></i>Profile</button>
            </div>
        </div>
    </div>

    <div class="col-md-8 col-lg-8 col-sm-12">
        <table class="table table-hover bg-white">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">제목</th>
                <th scope="col">수정일</th>
                <th scope="col">작업</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th scope="row"><input type="checkbox"></th>
                <td><a href="javascript:" >플랫폼/프레임워크/라이브러리란?</a></td>
                <td>2019-01-24 10:53:20</td>
                <td><button type="button" class="btn btn-success waves-effect waves-light">상세보기</button></td>
            </tr>
            <tr>
                <th scope="row"><input type="checkbox"></th>
                <td><a href="javascript:">AngularJS란?</a></td>
                <td>2019-01-24 10:53:20</td>
                <td><button type="button" class="btn btn-success waves-effect waves-light">상세보기</button></td>
            </tr>
            <tr>
                <th scope="row"><input type="checkbox"></th>
                <td><a href="javascript:" >SPA란?</a></td>
                <td>2019-01-24 10:53:20</td>
                <td><button type="button" class="btn btn-success waves-effect waves-light">상세보기</button></td>
            </tr>
            <tr>
                <th scope="row"><input type="checkbox"></th>
                <td><a href="javascript:">NodeJS에 대하여,...</a></td>
                <td>2019-01-24 10:53:20</td>
                <td><button type="button" class="btn btn-success waves-effect waves-light">상세보기</button></td>
            </tr>
            <tr>
                <th scope="row"><input type="checkbox"></th>
                <td><a href="javascript:">NPM에 대하여...</a></td>
                <td>2019-01-24 10:53:20</td>
                <td><button type="button" class="btn btn-success waves-effect waves-light">상세보기</button></td>
            </tr>
            </tbody>
        </table>

    </div>
</div>


</body>