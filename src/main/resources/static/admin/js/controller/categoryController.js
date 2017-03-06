mall.controller('categoryController', function ($rootScope, $scope, $http, $state) {
    $scope.add = function (id) {
        $state.go('category.add');
    };
});
