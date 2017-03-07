mall.directive('datatablecategory', function () {
    return {
        restrict: 'EA',
        scope: {
            options: "=",
            pageChanged: "&",
            itemClick: "&"
        },
        templateUrl: '../template/dataTableCategory.html',
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
        templateUrl: '../template/dataTableItem.html',
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
        templateUrl: '../template/dataTableImage.html',
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
        templateUrl: '../template/select2.html',
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
        templateUrl: '../template/imageSelector.html',
        link: function (scope, element, attrs) {
            scope.$watch('paginate', function (newData) {
                scope.paginate = newData || "";
            }, true);

            scope.$watch('imageSelected', function (newData) {
                scope.imageSelected = newData || [];
            }, true);

            scope.itemSelected = function (id) {

                if (scope.single) {
                    scope.imageSelected = [];
                    scope.imageSelected.push(id);
                    return;
                }
                var exists = false;
                angular.forEach(scope.imageSelected, function (value, key) {
                    if (value == id) {
                        exists = true;
                    }
                });
                if (!exists) {
                    scope.imageSelected.push(id);
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
});

