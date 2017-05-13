mall.controller('appListController', function ($rootScope, $scope, $http, $state,$stateParams) {
    $scope.options = {};
    $scope.options.paging = true;
    $scope.options.lengthChange = false;
    $scope.options.searching = false;
    $scope.options.ordering = true;
    $scope.options.info = true;
    $scope.options.autoWidth = false;
    $scope.options.title = '错误信息';
    $scope.options.paginate = {};

    $scope.page = $stateParams.page || 1;

    $http.get('/sysConfig/errors', {params: {pageNum: $scope.page, pageSize: 20}}).then(function (response) {
        $scope.options.paginate = response.data.data;
    }, function (error) {
    });
});
