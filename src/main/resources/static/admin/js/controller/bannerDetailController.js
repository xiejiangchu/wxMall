mall.controller('bannerDetailController', function ($rootScope, $scope, $http, $state, $stateParams) {

    $scope.bid = $stateParams.id;
    $scope.banner = {};

    $http.get('/banner/' + $scope.bid, {}).then(function (response) {
        $scope.banner = response.data.data;
    }, function (error) {
    });

    $http.get('/image/list', {params: {pageNum: 1, pageSize: $scope.pageSize}}).then(function (response) {
        $scope.images1 = response.data.data;
    }, function (error) {
    });

    $scope.pageChanged1 = function (id) {
        $http.get('/image/list', {params: {pageNum: id, pageSize: $scope.pageSize}}).then(function (response) {
            $scope.images1 = response.data.data;
        }, function (error) {
        });
    };

    $scope.submit = function () {
        $http.put('/banner/' + $scope.item.id, {
            item: $scope.item,
            masterImageSelected: $scope.masterImageSelected,
            slaveImageSelected: $scope.slaveImageSelected
        }).then(function (response) {
            if (response.data.code == 0) {
                history.back();
            }
        }, function (error) {
        });
    };

    $scope.delete = function () {

    };

});
