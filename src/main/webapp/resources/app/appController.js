'use strict';
angular.module('meta-app').controller('AppCtrl', function ($scope, $rootScope) {
    $rootScope.userList = [
        {id: 'misun-kim', name: '김미선', position: '선임', department: '지능융합기술실'},
        {id: 'gildong', name: '홍길동', position: '전임', department: 'SW연구센터'}
    ];

    $rootScope.commonText = '메타빌드 신입사원 교육용 AngularJS1 템플릿입니다.'
});