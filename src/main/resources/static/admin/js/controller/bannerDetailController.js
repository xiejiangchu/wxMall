mall.controller('bannerDetailController', function ($rootScope, $scope, $http, $state, $stateParams) {

    $scope.bid = $stateParams.id;
    $scope.banner = {};
    $scope.image = [];

    $scope.cid1 = [];
    $scope.cid2 = [];
    $scope.gid = [];

    $scope.params={};
    $scope.pageSize = 60;

    $http.get('/banner/' + $scope.bid, {}).then(function (response) {
        $scope.banner = response.data.data;
    }, function (error) {
    });

    $http.get('/image/list', {params: {pageNum: 1, pageSize: $scope.pageSize}}).then(function (response) {
        $scope.images1 = response.data.data;
    }, function (error) {
    });


    $http.get('/category/getCategoryLevel1', {
        params: {
            pageNum: 1,
            pageSize: $scope.pageSize
        }
    }).then(function (response) {
        $scope.cid1 = response.data.data;
    }, function (error) {
    });

    $scope.itemSelected1 = function (id) {
        $scope.params.cid1 = id;
        $http.get('/category/getCategoryLevel2/' + id, {}).then(function (response) {
            $scope.cid2 = response.data.data;
        }, function (error) {
        });
    };

    $scope.itemSelected2 = function (id) {
        $scope.params.cid2 = id;
        $http.get('/item/getByCategoryWithoutPaginate', {params:{
            cid1:$scope.params.cid1,
            cid2:$scope.params.cid2
        }}).then(function (response) {
            $scope.gid = response.data.data;
        }, function (error) {
        });
    };

    $scope.itemSelected3 = function (id) {
        $scope.params.gid = id;
        $scope.banner.target = id;
    };



    $scope.pageChanged1 = function (id) {
        $http.get('/image/list', {params: {pageNum: id, pageSize: $scope.pageSize}}).then(function (response) {
            $scope.images1 = response.data.data;
        }, function (error) {
        });
    };

    $scope.submit = function () {
        $http.put('/banner/' + $scope.bid, {
            banner: $scope.banner,
            image: $scope.image
        }).then(function (response) {
            if (response.data.code == 0) {
                history.back();
            }
        }, function (error) {
        });
    };

    $scope.delete = function () {
        $scope.showDeleteDialog = true;
    };

    $scope.dimissDeleteDialog = function () {
        $scope.showDeleteDialog = false;
    };
    $scope.confirmDeleteDialog = function () {
        $scope.showDeleteDialog = false;
        $http.delete('/banner/' + $scope.bid).then(function (response) {
            if (response.data.code == 0) {
                history.back();
            }
        }, function (error) {
        });
    };

});
