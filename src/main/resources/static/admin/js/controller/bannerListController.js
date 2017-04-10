mall.controller('bannerListController', function ($rootScope, $scope, $http, $state,$stateParams) {
    $scope.options = {};
    $scope.options.paging = true;
    $scope.options.lengthChange = false;
    $scope.options.searching = false;
    $scope.options.ordering = true;
    $scope.options.info = true;
    $scope.options.autoWidth = false;
    $scope.options.title = '广告位管理';
    $scope.options.paginate = {};

    $scope.page = $stateParams.page || 1;

    $http.get('/banner/getAll', {params: {pageNum: $scope.page, pageSize: 20}}).then(function (response) {
        $scope.options.paginate = response.data.data;
    }, function (error) {
    });

    $scope.itemClick = function (id) {
        $state.go('bannerDetail', {id: id});
    };

    $scope.itemOnline = function (id) {
        $http.put('/banner/offline', {
            id: id,
            is_online: 1
        }).then(function (response) {
            if (response.data.code == 0) {
                $scope.operateSuccess = true;
                $timeout(function () {
                    $scope.operateSuccess = false;
                }, 1000);
            }
        }, function (error) {
        });
    };

    $scope.dimissDeleteDialog = function () {
        $scope.showDeleteDialog = false;
    };
    $scope.confirmDeleteDialog = function () {
        $scope.showDeleteDialog = false;
        $http.delete('/banner/' + id).then(function (response) {
        }, function (error) {
        });
    };

    $scope.itemDelete = function (id) {
        $scope.showDeleteDialog = true;
    };
});
