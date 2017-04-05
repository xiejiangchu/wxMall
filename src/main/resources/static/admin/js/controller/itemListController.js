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
    $scope.options.pageSize = 30;
    $scope.showDeleteDialog = false;
    $scope.showSuccessDialog = false;
    $scope.operateSuccess = false;

    $scope.page = $stateParams.page || 1;

    $scope.pageChanged = function (page) {
        $state.go('item.list', {'page': page});
    };

    $scope.itemClick = function (id) {
        $state.go('itemDetail', {id: id});
    };

    $scope.itemOffline = function (id) {
        $scope.showSuccessDialog = true;
    };

    $scope.itemOnline = function (id) {
        $http.put('/item/offline', {
            id: id,
            is_online: 1
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

    $scope.itemDelete = function (id) {
        $scope.showDeleteDialog = true;

    };
    $scope.dimissDeleteDialog = function () {
        $scope.showDeleteDialog = false;
    };
    $scope.confirmDeleteDialog = function () {
        $scope.showDeleteDialog = false;
        $http.delete('/item/' + id).then(function (response) {
        }, function (error) {
        });
    };

    $scope.dimissSuccessDialog = function () {
        $scope.showSuccessDialog = false;
    };
    $scope.confirmSuccessDialog = function () {
        $scope.showSuccessDialog = false;
        $http.put('/item/offline', {
            id: id,
            is_online: 0
        }, {
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            transformRequest: function (obj) {
                var str = [];
                for (var o in obj)
                    str.push(encodeURIComponent(o) + "=" + encodeURIComponent(obj[o]));
                return str.join("&");
            }
        }).then(function (response) {

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
