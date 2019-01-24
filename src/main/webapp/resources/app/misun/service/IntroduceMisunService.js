angular.module('meta-app').factory('MisunService', function ($http) {
    /**
     *  목록 조회
     */
    function getBoardList(){
        return $http({
            url: '/rest/getBoardList',
            method: "GET"
        });
    }

    return {
        'getBoardList': function(){
            return getBoardList();
        }
    }
});