mall.config(function ($stateProvider, $urlRouterProvider, $httpProvider) {

    $httpProvider.defaults.headers.post = {
        'Content-Type': 'application/json'
    };
    $stateProvider.state('dashBoard', {
        url: '/dashBoard',
        templateUrl: 'html/dashBoard.html',
        controller: 'dashBoardController'
    }).state('item', {
        url: '/item/:page',
        templateUrl: 'html/item.html',
        controller: 'itemController'
    }).state('image', {
        url: '/image',
        templateUrl: 'html/image.html',
        controller: 'imageController'
    }).state('itemDetail', {
        url: '/itemDetail/:id',
        templateUrl: 'html/itemDetail.html',
        controller: 'itemDetailController'
    }).state('category', {
        url: '/category',
        templateUrl: 'html/category.html',
        controller: 'categoryController'
    }).state('order', {
        url: '/order',
        templateUrl: 'html/order.html',
        controller: 'orderController'
    }).state('search', {
        url: '/search',
        templateUrl: 'html/search.html',
        controller: 'searchController'
    });
    $urlRouterProvider.otherwise("/dashBoard");
});
