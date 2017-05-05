mall.controller('postController', function ($rootScope, $scope, $http, $state) {
    $scope.add = function (id) {
        $state.go('post.add');
    };
});
