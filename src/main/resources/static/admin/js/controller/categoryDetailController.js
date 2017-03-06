mall.controller('categoryDetailController', function ($rootScope, $scope, $http, $state, $stateParams) {

    $scope.cid1 = [];
    $scope.images = {};
    $scope.masterImageSelected = [];

    $scope.cid = $stateParams.id;
    $scope.category = {};
    $scope.pageSize = 60;

    $http.get('/category/' + $scope.cid, {}).then(function (response) {
        $scope.category = response.data.data;
        if ($scope.category.level == 1) {
            $http.get('/category/getCategoryLevel2/' + $scope.category.id, {}).then(function (response) {
                $scope.category.cid2 = response.data.data;
            }, function (error) {
            });
        }
    }, function (error) {
    });


    $http.get('/image/list', {params: {pageNum: 1, pageSize: $scope.pageSize}}).then(function (response) {
        $scope.images = response.data.data;
    }, function (error) {
    });

    $scope.pageChanged = function (id) {
        $http.get('/image/list', {params: {pageNum: id, pageSize: $scope.pageSize}}).then(function (response) {
            $scope.images = response.data.data;
        }, function (error) {
        });
    };

    $http.get('/category/getCategoryLevel1', {
        params: {
            pageNum: 1,
            pageSize: $scope.pageSize
        }
    }).then(function (response) {
        $scope.cid1 = response.data.data;
    }, function (error) {
        console.log(response);
    });

    $scope.itemSelected1 = function (id) {
        $scope.category.pid = id;
    };

    $scope.submit = function () {
        $http.put('/category/' + $scope.category.id, {
            category: $scope.category,
            masterImageSelected: $scope.masterImageSelected
        }).then(function (response) {
            if (response.data.code == 0) {
                history.back();
            }
        }, function (error) {
        });
    };

    $scope.delete = function () {
        $http.delete('/category/' + $scope.category.id).then(function (response) {
            if (response.data.code == 0) {
                history.back();
            }
        }, function (error) {
        });
    };

});
