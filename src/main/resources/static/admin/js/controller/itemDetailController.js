mall.controller('itemDetailController', function ($rootScope, $scope, $http, $state, $stateParams) {

    $scope.cid1 = [];
    $scope.cid2 = [];
    $scope.images1 = {};
    $scope.images2 = {};
    $scope.masterImageSelected = [];
    $scope.slaveImageSelected = [];
    $scope.pageSize = 60;
    $scope.options={
        spellChecker: false,
        autosave: {
            enabled: false,
            unique_id: "detail"
        }
    };

    $scope.iid = $stateParams.id;
    $scope.item = {};

    $http.get('/item/detail/' + $scope.iid, {}).then(function (response) {
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
    });


    $http.get('/image/list', {params: {pageNum: 1, pageSize: $scope.pageSize}}).then(function (response) {
        $scope.images1 = response.data.data;
        $scope.images2 = response.data.data;
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
        $http.put('/item/' + $scope.item.id, {
            item: $scope.item,
            masterImageSelected: $scope.masterImageSelected,
            slaveImageSelected: $scope.slaveImageSelected
        }).then(function (response) {
            if (response.data.code == 0) {
                alert("成功")
            }
        }, function (error) {
        });
    };

    $scope.delete = function () {

    };

});
