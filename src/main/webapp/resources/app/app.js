(function () {
    'use strict';
    angular.module('meta-app', [
        'ui.router',
        'ngAnimate',
        'ui.bootstrap',
        'ui.sortable',
        'ui.router',
        'ngTouch',
        'smart-table'
    ])
    .config(routeConfig);

    /**
     * Route 설정
     * @param $stateProvider
     * @param $urlRouterProvider
     */
    function routeConfig($stateProvider, $urlRouterProvider){
        $urlRouterProvider.otherwise('misun-kim');
        $stateProvider
        .state('misun-kim', {
            url: '/misun-kim',
            controller: 'MisunCtrl',
            templateUrl: 'nav/view/misun-kim'
        });
    }
})();
