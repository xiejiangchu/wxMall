mall.controller('dashBoardController', function ($rootScope, $scope, $http, $state) {
    $scope.dashBoardDto = {};

    $http.get('/statistics/dashboard').then(function (response) {
        $scope.dashBoardDto = response.data.data;
    }, function (error) {
    });

    $scope.checkAll= function () {
        $state.go('item.list', {'page': 1});
    }
});
