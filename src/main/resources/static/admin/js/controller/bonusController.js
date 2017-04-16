mall.controller('bonusController', function ($rootScope, $scope, $http, $state) {
    $scope.add = function (id) {
        $state.go('bonus.add');
    };
});
