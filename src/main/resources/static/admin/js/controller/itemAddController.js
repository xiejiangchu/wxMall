mall.controller('itemAddController', function ($rootScope, $scope, $http, $state) {
    $scope.cid1 = [];
    $scope.cid2 = [];
    $scope.images = [];
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
        $http.post('/item/',
            {
                item: $scope.item,
                masterImageSelected: $scope.masterImageSelected,
                slaveImageSelected: $scope.slaveImageSelected
            }
        ).then(function (response) {
            if (response.data.code == 0) {
                alert('添加成功');
                history.back();
            }
        }, function (error) {
        });
    };
});
