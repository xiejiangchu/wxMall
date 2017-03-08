mall.controller('orderListController', function ($rootScope, $scope, $http, $state, $stateParams, $dictionary) {
    $scope.page = $stateParams.page || 1;
    $scope.options = {
        title: '订单管理',
        paginate: {},
    };
    $scope.params = {
        type: 0,
        pageSize: 5
    }
    $scope.dictionary = $dictionary;

    $http.get('/order/getAll', {
        params: {
            type: $scope.params.type,
            pageNum: $scope.page,
            pageSize: $scope.params.pageSize
        }
    }).then(function (response) {
        $scope.options.paginate = response.data.data;
    }, function (error) {
        console.log(response);
    });

    $scope.pageChanged = function (page) {
        $state.go('order.list', {'page': page});
    };

    $scope.search = function () {
        $http.get('/order/getAll', {
            params: {
                type: $scope.params.type,
                pageNum: $scope.page,
                pageSize: $scope.params.pageSize
            }
        }).then(function (response) {
            $scope.options.paginate = response.data.data;
        }, function (error) {
            console.log(response);
        });
    }
});
