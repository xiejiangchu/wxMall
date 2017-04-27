mall.controller('imageController', function ($rootScope, $scope, $http, $state, $timeout) {

    $scope.options = {};
    $scope.options.paging = true;
    $scope.options.lengthChange = false;
    $scope.options.searching = false;
    $scope.options.ordering = true;
    $scope.options.info = true;
    $scope.options.autoWidth = false;
    $scope.options.title = '图片管理';
    $scope.options.paginate = {};

    $scope.pageChanged = function (page) {
        $http.get('/image/list', {params: {pageNum: page, pageSize: 30}}).then(function (response) {
            $scope.options.paginate = response.data.data;
        }, function (error) {
            console.log(response);
        });
    };

    $http.get('/image/list', {params: {pageNum: 1, pageSize: 30}}).then(function (response) {
        $scope.options.paginate = response.data.data;
    }, function (error) {
        console.log(response);
    });

    $scope.itemDelete = function (id) {
        $http.delete('/image/' + id).then(function (response) {
            $scope.operateSuccess = true;
            $timeout(function () {
                $scope.operateSuccess = false;
            }, 1000);
        }, function (error) {
        });
    }
});
