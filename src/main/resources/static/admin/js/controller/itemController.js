mall.controller('itemController', function ($rootScope, $scope, $http, $state) {
    $scope.add = function (id) {
        $state.go('item.add');
    };

    $scope.addBatch = function () {
        $state.go('item.addBatch');
    };
});
