mall.config(function ($stateProvider, $urlRouterProvider, $httpProvider) {

    $httpProvider.defaults.headers.post = {
        'Content-Type': 'application/json'
    };
    $stateProvider.state('dashBoard', {
        url: '/dashBoard',
        templateUrl: '/html/dashBoard.html',
        controller: 'dashBoardController'
    }).state('item', {
        abstract: true,
        url: '/item',
        templateUrl: '/html/item.html',
        controller: 'itemController'
    }).state('item.list', {
        url: '/itemList/:page',
        templateUrl: '/html/itemList.html',
        controller: 'itemListController'
    }).state('item.add', {
        url: '/itemAdd',
        templateUrl: '/html/itemAdd.html',
        controller: 'itemAddController'
    }).state('item.addBatch', {
        url: '/itemAddBatch',
        templateUrl: '/html/itemAddBatch.html',
        controller: 'itemAddBatchController'
    }).state('image', {
        url: '/image',
        templateUrl: '/html/image.html',
        controller: 'imageController'
    }).state('itemDetail', {
        url: '/itemDetail/:id',
        templateUrl: '/html/itemDetail.html',
        controller: 'itemDetailController'
    }).state('category', {
        abstract: true,
        url: '/category',
        templateUrl: '/html/category.html',
        controller: 'categoryController'
    }).state('category.list', {
        url: '/categoryList/:page',
        templateUrl: '/html/categoryList.html',
        controller: 'categoryListController'
    }).state('category.add', {
        url: '/categoryAdd',
        templateUrl: '/html/categoryAdd.html',
        controller: 'categoryAddController'
    }).state('categoryDetail', {
        url: '/categoryDetail/:id',
        templateUrl: '/html/categoryDetail.html',
        controller: 'categoryDetailController'
    }).state('order', {
        url: '/order',
        templateUrl: '/html/order.html',
        controller: 'orderController'
    }).state('search', {
        url: '/search',
        templateUrl: '/html/search.html',
        controller: 'searchController'
    });
    $urlRouterProvider.otherwise("/dashBoard");
});
