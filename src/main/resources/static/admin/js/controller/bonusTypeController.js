mall.controller('bonusTypeController', function ($rootScope, $scope, $http, $state) {
    $scope.add = function (id) {
        $state.go('bonusType.add');
    };
});
