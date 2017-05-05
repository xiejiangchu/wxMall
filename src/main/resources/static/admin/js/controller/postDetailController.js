mall.controller('postDetailController', function ($rootScope, $scope, $http, $state, $stateParams) {

    $scope.pid = $stateParams.id;
    $scope.post = {};
    $scope.image = [];
    $scope.images1=[];

    $scope.params={};
    $scope.pageSize = 60;

    $http.get('/post/' + $scope.pid, {}).then(function (response) {
        $scope.post = response.data.data;
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
        $http.put('/post/' + $scope.pid, {
            post: $scope.post,
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
        $http.delete('/post/' + $scope.pid).then(function (response) {
            if (response.data.code == 0) {
                history.back();
            }
        }, function (error) {
        });
    };

});
