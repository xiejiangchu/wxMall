mall.config(function ($stateProvider, $urlRouterProvider, $httpProvider) {

    $httpProvider.defaults.headers.post = {
        'Content-Type': 'application/json'
    };
    $stateProvider.state('dashboard', {
            //工作台
            url: '/dashboard',
            templateUrl: 'html/dashboard.html',
            controller: 'dashboardController'
        });
    $urlRouterProvider.otherwise("/");
});
