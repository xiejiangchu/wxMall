mall.controller('itemListController', function ($rootScope, $scope, $timeout, $http, $state, $stateParams) {
    $scope.options = {};
    $scope.options.paging = true;
    $scope.options.lengthChange = false;
    $scope.options.searching = false;
    $scope.options.ordering = true;
    $scope.options.info = true;
    $scope.options.autoWidth = false;
    $scope.options.title = '商品一级分类';
    $scope.options.paginate = {};
    $scope.options.pageSize = 60;
    $scope.showDeleteDialog = false;
    $scope.showSuccessDialog = false;
    $scope.operateSuccess = false;
    $scope.operateId = 0;

    $scope.page = $stateParams.page || 1;

    $scope.pageChanged = function (page) {
        $state.go('item.list', {'page': page});
    };

    $scope.itemClick = function (id) {
        $state.go('itemDetail', {id: id});
    };

    $scope.itemOffline = function (id) {
        $scope.showSuccessDialog = true;
        $scope.operateId = id;
    };

    $scope.itemOnline = function (id) {
        $http.put('/item/offline2', {
            id: id,
            online: 1
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
            }
        }, function (error) {
        });
    };

    /**
     * 删除
     * @param id
     */
    $scope.itemDelete = function (id) {
        $scope.showDeleteDialog = true;
        $scope.operateId = id;
    };
    $scope.dimissDeleteDialog = function () {
        $scope.showDeleteDialog = false;
    };
    $scope.confirmDeleteDialog = function () {
        $scope.showDeleteDialog = false;
        $http.delete('/item/' + $scope.operateId).then(function (response) {
            $scope.operateSuccess = true;
            $timeout(function () {
                $scope.operateSuccess = false;
            }, 1000);
        }, function (error) {
        });
    };

    /**
     * 下架
     */
    $scope.dimissSuccessDialog = function (id) {
        $scope.showSuccessDialog = false;

    };
    $scope.confirmSuccessDialog = function () {
        $scope.showSuccessDialog = false;
        $http.put('/item/offline2', {
            id: $scope.operateId,
            online: 0
        }, {
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            transformRequest: function (obj) {
                var str = [];
                for (var o in obj)
                    str.push(encodeURIComponent(o) + "=" + encodeURIComponent(obj[o]));
                return str.join("&");
            }
        }).then(function (response) {
            $scope.operateSuccess = true;
            $timeout(function () {
                $scope.operateSuccess = false;
            }, 1000);
        }, function (error) {
        });
    };

    $http.get('/item/getAll', {
        params: {
            pageNum: $scope.page,
            pageSize: $scope.options.pageSize
        }
    }).then(function (response) {
        $scope.options.paginate = response.data.data;
    }, function (error) {
        console.log(response);
    });

});
