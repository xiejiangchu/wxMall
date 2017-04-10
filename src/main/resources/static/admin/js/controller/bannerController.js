mall.controller('bannerController', function ($rootScope, $scope, $http, $state) {
    $scope.add = function (id) {
        $state.go('banner.add');
    };
});
