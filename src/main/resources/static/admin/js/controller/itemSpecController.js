mall.controller('itemSpecController', function ($rootScope, $scope, $http, $state) {

    $scope.cid1 = [];
    $scope.cid2 = [];
    $scope.items = [];
    $scope.itemSpecs = {};
    $scope.itemSpec = {};

    $scope.cid1Filter = [];
    $scope.cid2Filter = [];
    $scope.itemsFilter = [];
    $scope.itemSpecsFilter = [];
    $scope.params = {
        cid1: '0',
        cid2: '0',
        gid: 0,
        itemSpecId: 0,
        pageNum: 1,
        pageSize: 50
    };
    $scope.mode = "modify";

    $http.get('/category/getCategoryLevel1', {params: {pageNum: 1, pageSize: 10}}).then(function (response) {
        $scope.cid1 = response.data.data;
        $scope.params.cid1 = response.data.data[0].id;
        angular.forEach($scope.cid1, function (value, key) {
            this.push({
                'text': value.name,
                'value': value.id
            });
        }, $scope.cid1Filter);
    }, function (error) {
        console.log(response);
    });

    $scope.$watch('params.cid1', function (value, oldValue, scope) {
        if (value > 0) {
            $scope.mode = "modify";
            $http.get('/category/getCategoryLevel2/' + $scope.params.cid1, {}).then(function (response) {
                $scope.cid2 = response.data.data;
                $scope.cid2Filter = [];
                $scope.params.cid2 = 0;
                angular.forEach($scope.cid2, function (value, key) {
                    this.push({
                        'text': value.name,
                        'value': value.id
                    });
                }, $scope.cid2Filter);
            }, function (error) {
            });
        }
    });
    $scope.$watch('params.cid2', function (value, oldValue, scope) {
        if (value > 0) {
            $scope.mode = "modify";
            $http.get('/item/getByCategory/', {
                params: $scope.params
            }).then(function (response) {
                $scope.items = response.data.data;
                $scope.itemsFilter = [];
                angular.forEach($scope.items.list, function (value, key) {
                    this.push({
                        'text': value.name,
                        'value': value.id
                    });
                }, $scope.itemsFilter);
            }, function (error) {
            });
        }
    });
    $scope.$watch('params.gid', function (value, oldValue, scope) {
        if (value > 0) {
            $scope.mode = "modify";
            $http.get('/itemSpec/getAllByGid', {
                params: {
                    gid: $scope.params.gid
                }
            }).then(function (response) {
                $scope.itemSpecs = response.data.data;
                $scope.itemSpec = {};
                if ($scope.itemSpecs.length > 0) {
                    $scope.params.itemSpecId = $scope.itemSpecs[0].id;
                }
                $scope.itemSpecsFilter = [];
                angular.forEach($scope.itemSpecs, function (value, key) {
                    this.push({
                        'text': value.shop_price + "元/" + value.unit + "/销量:" + value.sale_num,
                        'value': value.id
                    });
                }, $scope.itemSpecsFilter);
            }, function (error) {
            });
        }
    });

    $scope.$watch('params.itemSpecId', function (value, oldValue, scope) {
        if (value > 0) {
            $scope.mode = "modify";
            angular.forEach($scope.itemSpecs, function (item, key) {
                if (item.id == $scope.params.itemSpecId) {
                    $scope.itemSpec = item;
                }
            });
        }
    });

    $scope.itemNew = function () {
        $scope.mode = "new"
        $scope.params.itemSpecId=null;
        $scope.itemSpec={};
    };

    $scope.submit = function () {
        if ($scope.mode == 'modify') {
            $http.put('/itemSpec/', $scope.itemSpec).then(function (response) {
                if (response.data.code == 0) {
                    alert('成功');
                } else {
                    alert(response.data.msg);
                }
            }, function (error) {
            });
        } else if ($scope.mode == 'new') {
            $scope.itemSpec.gid = $scope.params.gid;
            $http.post('/itemSpec/', $scope.itemSpec).then(function (response) {
                if (response.data.code == 0) {
                    alert('成功');
                } else {
                    alert(response.data.msg);
                }
            }, function (error) {
            });
        }

    };

    $scope.delete = function () {
        $scope.showDeleteDialog = true;
    };

    $scope.dimissDeleteDialog = function () {
        $scope.showDeleteDialog = false;
    };
    $scope.confirmDeleteDialog = function () {
        $scope.showDeleteDialog = false;
        $http.delete('/itemSpec/' + $scope.params.itemSpecId).then(function (response) {
            if (response.data.code == 0) {
               alert('成功')
            }
        }, function (error) {
        });
    };

});