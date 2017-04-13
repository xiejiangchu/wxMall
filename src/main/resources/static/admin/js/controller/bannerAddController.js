mall.controller('bannerAddController', function ($rootScope, $scope, $http) {
    $scope.banner = {};
    $scope.image = [];
    $scope.pageSize = 60;

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
        $http.post('/banner/', {
            banner: $scope.banner,
            image: $scope.image
        }).then(function (response) {
            if (response.data.code == 0) {
                history.back();
            }
        }, function (error) {
        });
    };
});
