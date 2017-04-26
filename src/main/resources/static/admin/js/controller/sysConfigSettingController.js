mall.controller('sysConfigSettingController', function ($rootScope, $scope, $http, $state) {

    $scope.sysConfig = {};
    $scope.options = {
        spellChecker: false,
        autosave: {
            enabled: false,
            unique_id: "detail"
        }
    };

    $http.get('/sysConfig/questions').then(function (response) {
        $scope.sysConfig.questions = response.data.data;
    }, function (error) {
    });

    $http.get('/sysConfig/about').then(function (response) {
        $scope.sysConfig.about = response.data.data;
    }, function (error) {
    });

    $http.get('/sysConfig/notice').then(function (response) {
        $scope.sysConfig.notice = response.data.data;
    }, function (error) {
    });

    $scope.submit = function () {
        $http.put('/sysConfig/saveQuestionAndAbout', {
            about: $scope.sysConfig.about.value,
            questions: $scope.sysConfig.questions.value,
            notice: $scope.sysConfig.notice.value
        }).then(function (response) {
            if (response.data.code == 0) {
                alert("修改成功");
            }
        }, function (error) {
        });
    }
});
