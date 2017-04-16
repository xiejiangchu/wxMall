mall.controller('bonusTypeAddController', function ($rootScope, $scope, $http) {
    $scope.bonustype = {};
    $scope.cid1 = [];
    $scope.cid2 = [];
    $scope.gid = [];
    $scope.pageSize = 60;


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
        $scope.bonustype.cid1 = id;
        $http.get('/category/getCategoryLevel2/' + id, {}).then(function (response) {
            $scope.cid2 = response.data.data;
        }, function (error) {
        });
    };

    $scope.itemSelected2 = function (id) {
        $scope.bonustype.cid2 = id;
        $http.get('/item/getByCategoryWithoutPaginate', {params:{
            cid1:$scope.bonustype.cid1,
            cid2:$scope.bonustype.cid2
        }}).then(function (response) {
            $scope.gid = response.data.data;
        }, function (error) {
        });
    };

    $scope.itemSelected3 = function (id) {
        $scope.bonustype.gid = id;
    };


    $scope.submit = function () {
        $http.post('/bonusType/', $scope.bonustype).then(function (response) {
            if (response.data.code == 0) {
                history.back();
            }
        }, function (error) {
        });
    };
});
