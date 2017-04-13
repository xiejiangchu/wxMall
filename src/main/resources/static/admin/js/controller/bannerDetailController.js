mall.controller('bannerDetailController', function ($rootScope, $scope, $http, $state, $stateParams) {

    $scope.bid = $stateParams.id;
    $scope.banner = {};
    $scope.image = [];
    $scope.pageSize = 60;

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
        $http.put('/banner/' + $scope.bid, {
            banner: $scope.banner,
            image: $scope.image
        }).then(function (response) {
            if (response.data.code == 0) {
                history.back();
            }
        }, function (error) {
        });
    };

    $scope.delete = function () {
        $scope.showDeleteDialog = true;
    };

    $scope.dimissDeleteDialog = function () {
        $scope.showDeleteDialog = false;
    };
    $scope.confirmDeleteDialog = function () {
        $scope.showDeleteDialog = false;
        $http.delete('/banner/' + $scope.bid).then(function (response) {
            if (response.data.code == 0) {
                history.back();
            }
        }, function (error) {
        });
    };

});
