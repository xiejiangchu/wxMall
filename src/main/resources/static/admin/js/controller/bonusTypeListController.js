mall.controller('bonusTypeListController', function ($rootScope, $scope, $http, $state,$stateParams) {
    $scope.options = {};
    $scope.options.paging = true;
    $scope.options.lengthChange = false;
    $scope.options.searching = false;
    $scope.options.ordering = true;
    $scope.options.info = true;
    $scope.options.autoWidth = false;
    $scope.options.title = '红包管理';
    $scope.options.paginate = {};

    $scope.page = $stateParams.page || 1;

    $http.get('/bonusType/getAll', {params: {pageNum: $scope.page, pageSize: 20}}).then(function (response) {
        $scope.options.paginate = response.data.data;
    }, function (error) {
    });

    $scope.itemClick = function (id) {
        $state.go('bonusTypeDetail', {id: id});
    };

    $scope.itemOnline = function (id) {
        $http.put('/bonusType/offline', {
            id: id,
            online: 1
        },{
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            transformRequest: function (obj) {
                var str = [];
                for (var o in obj)
                    str.push(encodeURIComponent(o) + "=" + encodeURIComponent(obj[o]));
                return str.join("&");
            }
        }).then(function (response) {
            if (response.data.code == 0) {
                alert("成功");
            }
        }, function (error) {
        });
    };

    $scope.itemOffline = function (id) {
        $http.put('/bonusType/offline', {
            id: id,
            online: 0
        },{
            headers: {'Content-Type': 'application/x-www-form-urlencoded'},
            transformRequest: function (obj) {
                var str = [];
                for (var o in obj)
                    str.push(encodeURIComponent(o) + "=" + encodeURIComponent(obj[o]));
                return str.join("&");
            }
        }).then(function (response) {
            if (response.data.code == 0) {
                alert("成功");
            }
        }, function (error) {
        });
    };

    $scope.dimissDeleteDialog = function () {
        $scope.showDeleteDialog = false;
    };
    $scope.confirmDeleteDialog = function () {
        $scope.showDeleteDialog = false;
        $http.delete('/bonusType/' + $scope.bid).then(function (response) {
            if (response.data.code == 0) {
                alert('删除成功');
            }
        }, function (error) {
        });
    };

    $scope.itemDelete = function (id) {
        $scope.bid=id;
        $scope.showDeleteDialog = true;
    };
});
