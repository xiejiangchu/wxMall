mall.config(function ($stateProvider, $urlRouterProvider, $httpProvider) {

    $httpProvider.defaults.headers.post = {
        'Content-Type': 'application/json'
    };
    $stateProvider.state('dashBoard', {
        url: '/dashBoard',
        templateUrl: '/admin/html/dashBoard.html',
        controller: 'dashBoardController'
    }).state('item', {
        abstract: true,
        url: '/item',
        templateUrl: '/admin/html/item.html',
        controller: 'itemController'
    }).state('item.list', {
        url: '/itemList/:page',
        templateUrl: '/admin/html/itemList.html',
        controller: 'itemListController'
    }).state('item.add', {
        url: '/itemAdd',
        templateUrl: '/admin/html/itemAdd.html',
        controller: 'itemAddController'
    }).state('item.addBatch', {
        url: '/itemAddBatch',
        templateUrl: '/admin/html/itemAddBatch.html',
        controller: 'itemAddBatchController'
    }).state('image', {
        url: '/image',
        templateUrl: '/admin/html/image.html',
        controller: 'imageController'
    }).state('itemDetail', {
        url: '/itemDetail/:id',
        templateUrl: '/admin/html/itemDetail.html',
        controller: 'itemDetailController'
    }).state('category', {
        abstract: true,
        url: '/category',
        templateUrl: '/admin/html/category.html',
        controller: 'categoryController'
    }).state('category.list', {
        url: '/categoryList/:page',
        templateUrl: '/admin/html/categoryList.html',
        controller: 'categoryListController'
    }).state('category.add', {
        url: '/categoryAdd',
        templateUrl: '/admin/html/categoryAdd.html',
        controller: 'categoryAddController'
    }).state('categoryDetail', {
        url: '/categoryDetail/:id',
        templateUrl: '/admin/html/categoryDetail.html',
        controller: 'categoryDetailController'
    }).state('order', {
        url: '/order',
        templateUrl: '/admin/html/order.html',
        controller: 'orderController'
    }).state('search', {
        url: '/search',
        templateUrl: '/admin/html/search.html',
        controller: 'searchController'
    });
    $urlRouterProvider.otherwise("/dashBoard");
});
