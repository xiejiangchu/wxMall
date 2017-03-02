mall.directive('task', function (taskService) {
    return {
        restrict: 'E',
        replace: true,
        scope: {
            task: '=',
            /**
             * task显示模式：
             * selfResultMode:显示自己的结果
             * finalResultMode:显示最终结果
             * handleMode:处理模式
             */
            taskMode: '@',
        },
        templateUrl: 'html/common/task.html',
        link: function (scope, ele, attr) {
            scope.finalResult = true;
            scope.selfResult = false;
            //申诉
            scope.appeal = function () {
                scope.$broadcast('appeal');
            };
            //暂缓处理
            scope.deferHandle = function () {
                taskService.loading('暂缓处理中......',
                    taskService.deferHandle(scope.task.appealHandleResult).then(function (data) {
                        taskService.showTip("暂缓成功！");
                        scope.$emit("taskDefer", scope.task);
                    }, function (data) {
                        taskService.showTip("暂缓失败，请重试");
                    })
                )

            }
        }
    }
}).directive('routeActive', function ($rootScope, $location) {
    //路由激活时加下划线
    return {
        restrict: 'A',
        scope: {},
        transclude: true,
        template: '<div ng-transclude></div>' +
        '<div dui-if="show" style="height: 3px;background-color: #00BC9C;margin: 0px 25px;"></div>',
        link: function (scope, el, attrs) {
            scope.$on('$stateChangeSuccess',
                function (event, toState, toParams, fromState, fromParams) {
                    var current = toState.name.substring(toState.name.lastIndexOf('.'));
                    if (current == attrs['uiSref']) {
                        scope.show = true;
                    } else {
                        scope.show = false;
                    }
                });
        },

    }
}).directive('taskPage', function () {
    return {
        restrict: 'EA',
        scope: {
            page: '=',
            pageChange: '&',
            /**
             * task显示模式
             * selfResultMode:显示自己的结果
             * finalResultMode:显示最终结果
             * handleMode:处理模式
             */
            taskMode: '@',
        },
        templateUrl: 'html/common/task-page.html',
        link: function (scope, ele, attr) {
            scope.clickCallback = function (pageNo) {
                scope.pageChange({pageNo: pageNo});
            }
        }
    }
}).directive('taskContainer', function () {
    return {
        restrict: 'EA',
        transclude: true,
        templateUrl: 'html/common/task-container.html',
        link: function (scope, ele, attr) {
        }
    }
}).directive('ssLoading', function () {
    //加载动画
    return {
        restrict: 'A',
        link: function (scope, ele, attr) {
            var element = ele[0];

            function getLoadElement() {
                var str = '<div id="loadingEle" style="position: absolute;left: 50%;top: 50%;-webkit-transform: translateX(-50%) translateY(-50%)" >' +
                    '<div class="pop_mask" style="opacity: 0;"></div>' +
                    '<img src="http://dui.dooioo.com/public/images/icon_ajaxload.gif"/>' +
                    '</div>';
                var loadEle = document.createElement('div');
                loadEle.innerHTML = str;
                return loadEle.childNodes[0];
            }


            scope.$watch(attr.ssLoading, function (value) {
                var loadElement = getLoadElement();
                if (value == true) {
                    element.appendChild(loadElement);
                } else {
                    var e = document.getElementById('loadingEle');
                    element.contains(e) ? element.removeChild(e) : null;
                }
            })


        }
    }
}).directive('loading', function () {
    return {
        restrict: 'E',
        scope: {
            loading: '='
        },
        template: '<div dui-if="loading" ><div class="pop_mask" style="opacity: 0;"></div><img src="http://dui.dooioo.com/public/images/icon_ajaxload.gif"/></div>'
    }
}).directive("jhCheckBox", function () {
    return {
        restrict: 'EA',
        scope: {
            datasource: '=',
            //选中的值
            selected: '='
        },
        template: '<span  style="display: inline-block" ng-class="{search_nav:true,search_focus:selected.indexOf(item.value) != -1}"  ng-repeat="item in datasource" ng-click="click(item)">' + '{{item.text}}' + '</span>',
        link: function (scope, ele, attr) {
            var unlimitedValue = scope.datasource[0].value;
            scope.selected = [unlimitedValue];
            //不限
            scope.click = function (item) {
                var value = item.value;
                //不限
                if (item.text == '不限') {
                    scope.selected = [unlimitedValue];
                    return;
                }

                //去掉不限
                if (scope.selected.indexOf(unlimitedValue) != -1) {
                    scope.selected.splice(scope.selected.indexOf(unlimitedValue), 1);
                }

                if (scope.selected.indexOf(value) == -1) {
                    scope.selected.push(value);
                } else {
                    scope.selected.splice(scope.selected.indexOf(value), 1);
                }

                //加上不限
                if (scope.selected.length == 0) {
                    scope.selected.push(unlimitedValue);
                }
            }
        }
    }
}).directive('history', function ($http) {
    return {
        restrict: 'E',
        scope: {
            res: '='
        },
        templateUrl: 'html/common/historyTemplate.html',
        link: function (scope, el, attr) {
            scope.showHis = function (res) {
                scope.showHisPop = true;
                scope.isLoading = true;
                $http.get("/appealOperateLog/list", {
                    params: {
                        taskId: scope.res.appealTaskHead.id,
                        pageNo: 1,
                        pageSize: 8
                    }
                }).success(function (response) {
                    scope.showHisPop = true;
                    scope.page = response;
                    scope.hisItem = response.pageList;
                    scope.totalHis = response.pageList.length;
                    scope.taskNo = res.appealTaskHead.appealNo;
                    scope.taskTypeName = res.appealEmpError.errType;
                    scope.status = res.appealTaskHead.state == 4 ? "已完成" : "未完成";
                    scope.isLoading = false;
                }).error(function (res) {
                    scope.isLoading = false;
                })
            };

            scope.hideHisPop = function () {
                scope.showHisPop = false;
            }

            scope.pageChange = function (current) {
                scope.isLoading = true;
                $http.get("/appealOperateLog/list", {
                    params: {
                        taskId: scope.res.appealTaskHead.id,
                        pageNo: current,
                        pageSize: 8
                    }
                }).success(function (response) {
                    scope.page = response;
                    scope.hisItem = response.pageList;
                    scope.totalHis = response.pageList.length;
                    scope.isLoading = false;
                }).error(function (res) {
                    scope.isLoading = false;
                })
            }
        }
    }
});