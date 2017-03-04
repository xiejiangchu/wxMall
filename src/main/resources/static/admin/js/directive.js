mall.directive('datatablecategory', function () {
    return {
        restrict: 'EA',
        scope: {
            options: "=",
            pageChanged: "&"
        },
        templateUrl: 'template/dataTableCategory.html',
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
            itemClick: "&"
        },
        templateUrl: 'template/dataTableItem.html',
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
        templateUrl: 'template/select2.html',
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
            imageSelected: "="
        },
        templateUrl: 'template/imageSelector.html',
        link: function (scope, element, attrs) {
            scope.$watch('paginate', optionsChanged, true);
            function optionsChanged(newData) {
                scope.paginate = newData || "";
                init();
            }

            scope.itemSelected = function (id) {
                if (!scope.images) {
                    return;
                }
                angular.forEach(scope.images, function (value, key) {
                    if (value.image.id == id) {
                        value.selected = !value.selected;
                    }
                });
                rebuild();
            };

            function init() {
                if (!scope.paginate) {
                    return;
                }
                scope.images = [];
                angular.forEach(scope.paginate.list, function (value, key) {
                    this.push({
                        image: value,
                        selected: false
                    });
                }, scope.images);
            }

            function rebuild() {
                if (!scope.images) {
                    return;
                }
                scope.imageSelected = [];
                angular.forEach(scope.images, function (value, key) {
                    if (value.selected) {
                        this.push(value.image.id);
                    }
                }, scope.imageSelected)
            }
        }
    };
});

