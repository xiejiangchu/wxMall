mall.controller('orderListController', function ($rootScope, $scope, $http, $state, $stateParams, $dictionary) {
    $scope.options = {
        title: '订单管理',
        paginate: {},
    };
    $scope.params = {
        type: 0,
        pageNum: 1,
        pageSize: 5,
        time_start: '',
        time_end: '',
        created_at_start: '',
        created_at_end: ''
    };
    $scope.params.pageNum = $stateParams.page || 1;
    $scope.dictionary = $dictionary;

    $http.get('/order/getAll', {
        params:$scope.params
    }).then(function (response) {
        $scope.options.paginate = response.data.data;
    }, function (error) {
        console.log(response);
    });

    $scope.pageChanged = function (page) {
        $state.go('order.list', {'page': page});
    };

    $scope.search = function () {
        console.log($scope.params);
        $http.get('/order/getAll', {
            params: $scope.params
        }).then(function (response) {
            $scope.options.paginate = response.data.data;
        }, function (error) {
            console.log(response);
        });
    }
});
