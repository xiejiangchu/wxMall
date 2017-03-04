mall.controller('categoryController', function ($rootScope, $scope, $http, $state) {
    $scope.options = {};
    $scope.options.paging = true;
    $scope.options.lengthChange = false;
    $scope.options.searching = false;
    $scope.options.ordering = true;
    $scope.options.info = true;
    $scope.options.autoWidth = false;
    $scope.options.title = '商品一级分类';
    $scope.options.paginate = {};

    $scope.pageChanged = function (page) {
        $http.get('/category/getCid1', {pageNum: page, pageSize: 10}).then(function (response) {
            $scope.options.paginate = response.data.data;
        }, function (error) {
            console.log(response);
        });
    };

    $http.get('/category/getCid1', {pageNum: 1, pageSize: 10}).then(function (response) {
        $scope.options.paginate = response.data.data;
    }, function (error) {
        console.log(response);
    });
});
