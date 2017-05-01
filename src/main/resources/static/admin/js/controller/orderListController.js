mall.controller('orderListController', function ($rootScope, $scope, $http, $state, $stateParams, $dictionary, $timeout) {
    $scope.options = {
        title: '订单管理',
        paginate: {},
    };
    $scope.params = {
        type: 0,
        pageNum: 1,
        pageSize: 50,
        time_start: '',
        time_end: '',
        created_at_start: '',
        created_at_end: ''
    };
    $scope.params.pageNum = $stateParams.page || 1;
    $scope.dictionary = $dictionary;

    $http.get('/order/getAll', {
        params: $scope.params
    }).then(function (response) {
        $scope.options.paginate = response.data.data;
    }, function (error) {
    });

    $scope.pageChanged = function (page) {
        $state.go('order.list', {'page': page});
    };

    $scope.search = function () {
        $http.get('/order/getAll', {
            params: $scope.params
        }).then(function (response) {
            $scope.options.paginate = response.data.data;
        }, function (error) {
        });
    }

    $scope.itemSend = function (id) {
        $http.put('/order/sendOrder', {
            oid: id,
            sending_status: 20
        }, {
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            transformRequest: function (obj) {
                var str = [];
                for (var o in obj)
                    str.push(encodeURIComponent(o) + "=" + encodeURIComponent(obj[o]));
                return str.join("&");
            }
        }).then(function (response) {
            if (response.data.code == 0) {
                $scope.operateSuccess = true;
                $timeout(function () {
                    $scope.operateSuccess = false;
                }, 1000);
            } else {
                alert(response.data.msg);
            }
        }, function (error) {
        });
    }

    $scope.itemPackage = function (id) {
        $http.put('/order/packageOrder', {
            oid: id,
            package_status: 10
        }, {
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            transformRequest: function (obj) {
                var str = [];
                for (var o in obj)
                    str.push(encodeURIComponent(o) + "=" + encodeURIComponent(obj[o]));
                return str.join("&");
            }
        }).then(function (response) {
            if (response.data.code == 0) {
                $scope.operateSuccess = true;
                $timeout(function () {
                    $scope.operateSuccess = false;
                }, 1000);
            } else {
                alert(response.data.msg);
            }
        }, function (error) {
        });
    };

    $scope.itemCancel = function (id) {
        $http.put('/order/cancelOrder', {
            oid: id
        }, {
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            transformRequest: function (obj) {
                var str = [];
                for (var o in obj)
                    str.push(encodeURIComponent(o) + "=" + encodeURIComponent(obj[o]));
                return str.join("&");
            }
        }).then(function (response) {
            if (response.data.code == 0) {
                $scope.operateSuccess = true;
                $timeout(function () {
                    $scope.operateSuccess = false;
                }, 1000);
            } else {
                alert(response.data.msg);
            }
        }, function (error) {
        });
    }
});
