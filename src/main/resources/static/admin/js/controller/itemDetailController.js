mall.controller('itemDetailController', function ($rootScope, $scope, $http, $state, $stateParams) {

    $scope.cid1 = [];
    $scope.cid2 = [];
    $scope.images = {};
    $scope.imageSelected = [];

    $scope.iid = $stateParams.id;
    $scope.item = {};

    $http.get('/item/' + $scope.iid, {}).then(function (response) {
        $scope.item = response.data.data;
        $http.get('/category/getCategoryLevel2/' + $scope.item.cid1).then(function (response) {
            $scope.cid2 = response.data.data;
        }, function (error) {
        });
    }, function (error) {
    });

    $http.get('/category/getCategoryLevel1', {params: {pageNum: 1, pageSize: 10}}).then(function (response) {
        $scope.cid1 = response.data.data;
    }, function (error) {
        console.log(response);
    });


    $http.get('/image/list', {params: {pageNum: 1, pageSize: 50}}).then(function (response) {
        $scope.images = response.data.data;
    }, function (error) {
    });

    $scope.itemSelected1 = function (id) {
        $scope.item.cid1 = id;
        $http.get('/category/getCategoryLevel2/' + id, {}).then(function (response) {
            $scope.cid2 = response.data.data;
        }, function (error) {
        });
    };

    $scope.itemSelected2 = function (id) {
        $scope.item.cid2 = id;
    };

    $scope.submit = function () {
        $http.put('/item/' + $scope.item.id,
            {item: $scope.item, images: $scope.imageSelected}
        ).then(function (response) {
            console.log(response);
        }, function (error) {
        });
    };

    $scope.cancel = function () {

    };

});
