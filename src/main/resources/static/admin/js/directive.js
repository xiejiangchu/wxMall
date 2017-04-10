mall.directive('datatablecategory', function () {
    return {
        restrict: 'EA',
        scope: {
            options: "=",
            pageChanged: "&",
            itemClick: "&",
            itemDelete: "&",
            itemOnline: "&",
            itemOffline: "&"
        },
        templateUrl: '/admin/template/dataTableCategory.html',
        link: function (scope, element, attrs) {
            scope.$watch('options.paginate', handleModelUpdates, true);
            scope.$watch('options.title', titleChanged, true);

            function titleChanged(newData) {
                scope.options.title = newData || "";
            }

            function handleModelUpdates(newData) {
                scope.options.paginate = newData || {};
            }
        }
    };
}).directive('datatableorder', function () {
    return {
        restrict: 'EA',
        scope: {
            options: "=",
            pageChanged: "&"
        },
        templateUrl: '/admin/template/dataTableOrder.html',
        link: function (scope, element, attrs) {
            scope.$watch('options.paginate', handleModelUpdates, true);
            scope.checkDetail = function ($event) {
                var element = $($event.target);
                element.parents('tr').next('tr').toggle('slow');
                element.find(".table-expandable-arrow").toggleClass("up");
                element.toggleClass("up");
            };

            function handleModelUpdates(newData) {
                scope.options.paginate = newData || {};
            }
        }
    };
}).directive('datatableitem', function () {
    return {
        restrict: 'EA',
        scope: {
            options: "=",
            pageChanged: "&",
            itemClick: "&",
            itemDelete: "&",
            itemOnline: "&",
            itemOffline: "&"
        },
        templateUrl: '/admin/template/dataTableItem.html',
        link: function (scope, element, attrs) {
            scope.$watch('options.paginate', handleModelUpdates, true);
            scope.$watch('options.title', titleChanged, true);

            function titleChanged(newData) {
                scope.options.title = newData || "";
            }

            function handleModelUpdates(newData) {
                scope.options.paginate = newData || {};
            }
        }
    };
}).directive('datatablebanner', function () {
    return {
        restrict: 'EA',
        scope: {
            options: "=",
            pageChanged: "&",
            itemClick: "&",
            itemDelete: "&",
            itemOnline: "&",
            itemOffline: "&"
        },
        templateUrl: '/admin/template/dataTableBanner.html',
        link: function (scope, element, attrs) {
            scope.$watch('options.paginate', handleModelUpdates, true);
            scope.$watch('options.title', titleChanged, true);

            function titleChanged(newData) {
                scope.options.title = newData || "";
            }

            function handleModelUpdates(newData) {
                scope.options.paginate = newData || {};
            }
        }
    };
}).directive('datatableimage', function () {
    return {
        restrict: 'EA',
        scope: {
            options: "=",
            pageChanged: "&",
            itemClick: "&"
        },
        templateUrl: '/admin/template/dataTableImage.html',
        link: function (scope, element, attrs) {
            scope.$watch('options.paginate', handleModelUpdates, true);
            scope.$watch('options.title', titleChanged, true);

            function titleChanged(newData) {
                scope.options.title = newData || "";
            }

            function handleModelUpdates(newData) {
                scope.options.paginate = newData || {};
            }
        }
    };
}).directive('select2', function () {
    return {
        restrict: 'EA',
        scope: {
            options: "=",
            itemSelected: "&",
            title: "@",
            initItem: "@"
        },
        templateUrl: '/admin/template/select2.html',
        link: function (scope, element, attrs) {
            $(element).find('.select2').select2();
            scope.$watch('initItem', function (newData) {
                scope.initItem = newData;
                scope.item_selected = {
                    id: scope.initItem
                };
            }, true);
            scope.$watch('options', optionsChanged, true);
            function optionsChanged(newData) {
                scope.options = newData || "";
            }
        }
    };
}).directive('imageselector', function () {
    return {
        restrict: 'EA',
        scope: {
            paginate: "=",
            imageSelected: "=",
            pageChanged: "&",
            single: '='
        },
        templateUrl: '/admin/template/imageSelector.html',
        link: function (scope, element, attrs) {
            scope.$watch('paginate', function (newData) {
                scope.paginate = newData || "";
            }, true);

            scope.$watch('imageSelected', function (newData) {
                scope.imageSelected = newData || [];
            }, true);

            scope.itemSelected = function (id) {

                if (scope.single) {
                    if (scope.imageSelected[0] == id) {
                        scope.imageSelected.splice(0, 1);
                    } else {
                        scope.imageSelected.splice(0, 1);
                        scope.imageSelected.push(id);
                    }
                    return;
                }
                var exists = false;
                var index = -1;
                angular.forEach(scope.imageSelected, function (value, key) {
                    if (value == id) {
                        exists = true;
                        index = key;
                    }
                });
                if (!exists) {
                    scope.imageSelected.push(id);
                } else {
                    if (index >= 0) {
                        scope.imageSelected.splice(index, 1);
                    }
                }
            };

            scope.check = function (id) {
                var exists = false;
                angular.forEach(scope.imageSelected, function (value, key) {
                    if (value == id) {
                        exists = true;
                    }
                });
                return exists;
            };
        }
    };
}).directive('operateFilter', function () {
    /**
     * 选择框-checkbox/radio
     * required list:数据源 list: [{value: "0", text: "不限"},...}
     * radio:true(单选),false(多选),默认多选
     */
    return {
        restrict: 'E',
        scope: {
            list: '=',
            selected: '=',
            radio: '=',
            clickCallback: '&',
            result: '@'
        },
        template: '<span  ng-class="{search_nav:true,search_focus:item.value==0}"  ng-repeat="item in list" ng-click="select(item,$event)">' + '{{item.text}}' + '</span>',
        link: function (scope, el, attrs) {
            scope.attList = [0];
            scope.select = function (item, self) {
                //什么都没选 默认不限
                if (item.value == "0" || !$(self.target).siblings().hasClass("search_focus")) {
                    $(self.target).siblings().removeClass("search_focus");
                    $(self.target).removeClass("search_focus");
                    $(self.target).parent().children(":first").addClass("search_focus");
                    scope.attList = [0];
                } else {
                    $(self.target).parent().children(":first").removeClass("search_focus");
                    //已选中,移除
                    if ($(self.target).hasClass("search_focus")) {
                        $(self.target).removeClass("search_focus");
                        scope.attList.splice(attList.indexOf(item.value), 1);
                    }
                    //未选择,选上
                    else {
                        $(self.target).addClass("search_focus");
                        if (scope.radio) {
                            $(self.target).siblings().removeClass("search_focus");
                            scope.attList = item.value;
                        } else {
                            scope.attList.push(item.value);
                        }
                    }
                }
            };
            scope.$watch('attList', function (value, oldValue, scope) {
                eval("scope.$parent." + scope.result + "='" + scope.attList.join(',') + "'");
            }, true);
        }
    }

}).directive('operateSelect', function () {
    return {
        restrict: 'E',
        scope: {
            list: '=',
            selected: '='
        },
        templateUrl: '/admin/template/operateSelect.html',
        link: function (scope, el, attrs) {
            scope.$watch('list', function (newData) {
                scope.list = newData || [];
            }, true);
            scope.$watch('selected', function (newData) {
                scope.selected = newData || '';
            }, true);
            scope.itemSelected = function (value) {
                scope.selected = value;
            };
        }
    }

}).directive('itemSelect', function () {
    return {
        restrict: 'E',
        scope: {
            list: '=',
            selected: '='
        },
        templateUrl: '/admin/template/itemSelect.html',
        link: function (scope, el, attrs) {
            scope.$watch('list', function (newData) {
                scope.list = newData || [];
            }, true);
            scope.$watch('selected', function (newData) {
                scope.selected = newData || '';
            }, true);
            scope.itemSelected = function (value) {
                scope.selected = value;
            };
        }
    }

}).directive('datetimepicker', function () {
    return {
        restrict: 'EA',
        scope: {
            timeStart: '@',
            timeEnd: '@'
        },
        templateUrl: '/admin/template/dateTimePicker.html',
        link: function (scope, element, attrs) {
            $(element).find('#picker').daterangepicker({
                timePicker: true,
                timePickerIncrement: 1,
                timePicker24Hour: true,
                locale: {
                    format: 'YYYY/MM/DD hh:mm:ss',
                    applyLabel: '确认',
                    cancelLabel: '取消',
                    fromLabel: '从',
                    toLabel: '到',
                    weekLabel: 'W',
                    customRangeLabel: 'Custom Range',
                    daysOfWeek: ["日", "一", "二", "三", "四", "五", "六"],
                    monthNames: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
                }
            }, function (start, end, label) {
                eval("scope.$parent." + scope.timeStart + "='" + start.format('YYYY/MM/DD hh:MM:ss') + "'");
                eval("scope.$parent." + scope.timeEnd + "='" + end.format('YYYY/MM/DD hh:MM:ss') + "'");
            });
        }
    };
});

