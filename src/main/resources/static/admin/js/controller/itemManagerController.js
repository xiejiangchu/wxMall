mall.controller('itemManagerController', function ($rootScope, $scope, $http, $state) {

    $scope.cid1 = [];
    $scope.cid2 = [];
    $scope.items = [];
    $scope.editItem = {};

    $scope.images1 = {};
    $scope.images2 = {};
    $scope.masterImageSelected = [];
    $scope.slaveImageSelected = [];
    //图片分页大小
    $scope.pageSize = 100;

    $scope.cid1Filter = [];
    $scope.cid2Filter = [];
    $scope.itemsFilter = [];
    $scope.params = {
        cid1: '0',
        cid2: '0',
        iid: 0,
        pageNum: 1,
        pageSize: 50
    };
    $scope.options={spellChecker: false,
        autosave: {
            enabled: false,
            unique_id: "detail"
        }};


    $scope.add = function (id) {
        $state.go('item.add');
    };

    $scope.addBatch = function () {
        $state.go('item.addBatch');
    };

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
    });

    $scope.$watch('params.cid1', function (value, oldValue, scope) {
        $scope.masterImageSelected = [];
        $scope.slaveImageSelected = [];
        if (value > 0) {
            $http.get('/category/getCategoryLevel2/' + $scope.params.cid1, {}).then(function (response) {
                $scope.cid2 = response.data.data;
                $scope.cid2Filter = [];
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
        $scope.masterImageSelected = [];
        $scope.slaveImageSelected = [];
        if (value > 0) {
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
    $scope.$watch('params.iid', function (value, oldValue, scope) {
        $scope.masterImageSelected = [];
        $scope.slaveImageSelected = [];
        if (value > 0) {
            $http.get('/item/' + $scope.params.iid, {}).then(function (response) {
                $scope.editItem = response.data.data;
            }, function (error) {
            });
        }
    });


    $http.get('/image/list', {params: {pageNum: 1, pageSize: $scope.pageSize}}).then(function (response) {
        $scope.images1 = response.data.data;
        $scope.images2 = response.data.data;
    }, function (error) {
    });

    $scope.itemSelected1 = function (id) {
        $scope.editItem.cid1 = id;
        $http.get('/category/getCategoryLevel2/' + id, {}).then(function (response) {
            $scope.cid2 = response.data.data;
        }, function (error) {
        });
    };

    $scope.itemSelected2 = function (id) {
        $scope.editItem.cid2 = id;
    };

    $scope.pageChanged1 = function (id) {
        $http.get('/image/list', {params: {pageNum: id, pageSize: $scope.pageSize}}).then(function (response) {
            $scope.images1 = response.data.data;
        }, function (error) {
        });
    };

    $scope.pageChanged2 = function (id) {
        $http.get('/image/list', {params: {pageNum: id, pageSize: $scope.pageSize}}).then(function (response) {
            $scope.images2 = response.data.data;
        }, function (error) {
        });
    };

    $scope.submit = function () {
        $http.put('/item/' + $scope.editItem.id, {
            item: $scope.editItem,
            masterImageSelected: $scope.masterImageSelected,
            slaveImageSelected: $scope.slaveImageSelected
        }).then(function (response) {
            if (response.data.code == 0) {
                alert('成功');
            } else {
                alert(response.data.msg);
            }
            $scope.masterImageSelected = [];
            $scope.slaveImageSelected = [];
        }, function (error) {
        });

    };

    $scope.delete = function () {

    };


});