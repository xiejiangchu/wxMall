mall.controller('postListController', function ($rootScope, $scope, $http, $state,$stateParams) {
    $scope.options = {};
    $scope.options.paging = true;
    $scope.options.lengthChange = false;
    $scope.options.searching = false;
    $scope.options.ordering = true;
    $scope.options.info = true;
    $scope.options.autoWidth = false;
    $scope.options.title = '广告位管理';
    $scope.options.paginate = {};

    $scope.page = $stateParams.page || 1;

    $http.get('/post/list', {params: {pageNum: $scope.page, pageSize: 20}}).then(function (response) {
        $scope.options.paginate = response.data.data;
    }, function (error) {
    });

    $scope.itemClick = function (id) {
        $state.go('postDetail', {id: id});
    };

    $scope.itemOnline = function (id) {
        $http.put('/post/offline', {
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
        $http.put('/post/offline', {
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
        $http.delete('/post/' + id).then(function (response) {
        }, function (error) {
        });
    };

    $scope.itemDelete = function (id) {
        $scope.showDeleteDialog = true;
    };
});
