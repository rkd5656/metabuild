'use strict';
angular.module('meta-app').controller('MisunCtrl', function ($scope, $http, MisunService) {
    $scope.introduce = { text: '안녕하세요 지능융합기술실 김미선 선임입니다. 각자 자기 메뉴에 자기소개 및 1주차 배운 내용에대해 정리해주세요.'};

    console.log($scope.$root.commonText);

    MisunService.getBoardList().success(function(resp){
        console.log(resp);
    })
/*
    $scope.getDetailInfo = function () {

        MisunService.getDetailInfo(_id).success(function(resp){
            console.log(resp);
        }).error(function(resp){
        });
    }*/
});