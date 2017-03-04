mall.controller('dashBoardController', function ($rootScope, $scope, $http, $state) {
    $scope.dashBoardDto = {};

    $http.get('/statistics/dashboard').then(function (response) {
        $scope.dashBoardDto = response.data.data;
    }, function (error) {
        console.log(response)
    });
});