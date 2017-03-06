mall.controller('categoryListController', function ($rootScope, $scope, $http, $state, $stateParams) {
    $scope.options = {};
    $scope.options.paging = true;
    $scope.options.lengthChange = false;
    $scope.options.searching = false;
    $scope.options.ordering = true;
    $scope.options.info = true;
    $scope.options.autoWidth = false;
    $scope.options.title = '商品一级分类';
    $scope.options.paginate = {};

    $scope.page = $stateParams.page || 1;

    $scope.pageChanged = function (page) {
        $scope.page = page;
        $http.get('/category/getCid1', {params: {pageNum: page, pageSize: 20}}).then(function (response) {
            $scope.options.paginate = response.data.data;
        }, function (error) {
            console.log(response);
        });
    };

    $scope.itemClick = function (id) {
        $state.go('categoryDetail', {id: id});
    };

    $http.get('/category/getCid1', {params: {pageNum: $scope.page, pageSize: 20}}).then(function (response) {
        $scope.options.paginate = response.data.data;
    }, function (error) {
        console.log(response);
    });
});
