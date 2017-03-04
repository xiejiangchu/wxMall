mall.controller('itemController', function ($rootScope, $scope, $http, $state) {
    $scope.options = {};
    $scope.options.paging = true;
    $scope.options.lengthChange = false;
    $scope.options.searching = false;
    $scope.options.ordering = true;
    $scope.options.info = true;
    $scope.options.autoWidth = false;
    $scope.options.title = '商品一级分类';
    $scope.options.paginate = {};
    $scope.options.pageSize = 30;

    $scope.pageChanged = function (page) {
        console.log(page);
        $http.get('/item/getAll', {
            params: {
                pageNum: page,
                pageSize: $scope.options.pageSize
            }
        }).then(function (response) {
            $scope.options.paginate = response.data.data;
        }, function (error) {
            console.log(response);
        });
    };

    $scope.itemClick = function (id) {
        $state.go('itemDetail',{id:id});
    };

    $http.get('/item/getAll', {params: {pageNum: 1, pageSize: $scope.options.pageSize}}).then(function (response) {
        $scope.options.paginate = response.data.data;
    }, function (error) {
        console.log(response);
    });
});
