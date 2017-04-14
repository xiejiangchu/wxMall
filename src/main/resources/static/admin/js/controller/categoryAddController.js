mall.controller('categoryAddController', function ($rootScope, $scope, $http, $state) {
    $scope.cid1 = [];
    $scope.images = {};
    $scope.masterImageSelected = [];

    $scope.category = {
        is_delete: 0,
        level: 1,
        sort: 10
    };
    $scope.pageSize=50;

    $http.get('/image/list', {params: {pageNum: 1, pageSize: $scope.pageSize}}).then(function (response) {
        $scope.images = response.data.data;
    }, function (error) {
    });

    $http.get('/category/getCategoryLevel1', {params: {pageNum: 1, pageSize: 10}}).then(function (response) {
        $scope.cid1 = response.data.data;
    }, function (error) {
        console.log(response);
    });

    $scope.pageChanged = function (id) {
        $http.get('/image/list', {params: {pageNum: id, pageSize: $scope.pageSize}}).then(function (response) {
            $scope.images = response.data.data;
        }, function (error) {
        });
    };

    $scope.itemSelected1 = function (id) {
        $scope.category.pid = id;
    };

    $scope.submit = function () {
        $http.post('/category/', {
            category: $scope.category,
            masterImageSelected: $scope.masterImageSelected
        }).then(function (response) {
            if (response.data.code == 0) {
                alert('添加成功');
            }
        }, function (error) {
        });
    };

    $scope.cancel = function () {

    };
});
