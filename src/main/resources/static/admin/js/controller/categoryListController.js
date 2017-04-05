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
        $http.get('/category/getAll', {params: {pageNum: page, pageSize: 20}}).then(function (response) {
            $scope.options.paginate = response.data.data;
        }, function (error) {
            console.log(response);
        });
    };

    $scope.itemClick = function (id) {
        $state.go('categoryDetail', {id: id});
    };

    $scope.itemDelete = function (id) {
        $http.delete('/category/' + id).then(function (response) {
            console.log("asf");
        }, function (error) {

        });
    };

    $scope.itemOnline = function (id) {
        $http.put('/category/offline', {
            id: id,
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
            alert("上架成功");
        }, function (error) {

        });
    };

    $scope.itemOffline = function (id) {
        $http.put('/category/offline', {
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
            alert("下架成功")
        }, function (error) {

        });
    };

    $http.get('/category/getAll', {params: {pageNum: $scope.page, pageSize: 20}}).then(function (response) {
        $scope.options.paginate = response.data.data;
    }, function (error) {
        console.log(response);
    });
});
