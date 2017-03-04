mall.controller('mallController', function ($rootScope, $scope, $http, $state) {
    $scope.img_host = "http://wxmall.image.alimmdn.com/";
    $scope.user = {};
    $http.get('/user').then(function (response) {
        $scope.user = response.data.data;
    }, function (error) {
        console.log(response);
    });
});
