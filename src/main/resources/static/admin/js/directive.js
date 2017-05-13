mall.directive('alert', function () {
    return {
        restrict: 'E',
        scope: {
            name: '@',
            mtitle: '@',
            okFunc: '&',
            level: '@'
        },
        template: '<div class="modal fade in open" id="{{name}}" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static">' +
        '<div class="modal-dialog vdmc-alert">' +
        '<div class=" alert alert-{{level}} row">' +
        '<div class="modalimg">' +
        '<i ng-if=" level===\'danger\'" class="glyphicon glyphicon-remove-circle "></i>' +
        '<i ng-if=" level===\'info\'" class="glyphicon glyphicon-info-sign "></i>' +
        '<i ng-if=" level===\'success\'" class="glyphicon glyphicon-ok-circle "></i>' +
        '<i ng-if=" level===\'warning\'" class="glyphicon glyphicon-alert "></i>' +
        '</div>' +
        '<div class="modaltitle">' +
        '<a class="close"  ng-click="okFunc()">×</a>' +
        '<div>' +
        '<b ng-if=" level===\'danger\'">操作失败！</b>' +
        '<b ng-if=" level===\'info\'">提示：</b>' +
        '<b ng-if=" level===\'success\'">操作成功！</b>' +
        '<b ng-if=" level===\'warning\'">警告：</b>' +
        '</div>' +
        '<div>{{mtitle}}</div>' +
        '</div>' +
        '</div>' +
        '</div>' +
        '</div>',
        replace: true,
        link: function (scope, element) {
            var $ele = $(element), modalId = '#' + scope.name;
            $ele.on("click", ".close", function () {
                $(modalId).modal('hide');
                $(modalId).remove();
                var modalback = $('.modal-backdrop');
                if (modalback.length > 0) modalback[modalback.length - 1].remove();
            });
            $('body').addClass('modal-open').append('<div class="modal-backdrop fade in"></div>');
        }
    };
}).directive('datatablebonus', function () {
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
        templateUrl: '/admin/template/dataTableBonus.html',
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
}).directive('datatableerror', function () {
    return {
        restrict: 'EA',
        scope: {
            options: "=",
            pageChanged: "&"
        },
        templateUrl: '/admin/template/dataTableError.html',
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
}).directive('datatablebonustype', function () {
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
        templateUrl: '/admin/template/dataTableBonusType.html',
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
}).directive('datatablecategory', function () {
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
            pageChanged: "&",
            itemSend: "&",
            itemPackage: "&",
            itemCancel: "&"
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
            imgHost: "@",
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
            scope.$watch('imgHost', function (newData) {
                scope.imgHost = newData || "";
            }, true);

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
            imgHost: "@",
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
            scope.$watch('imgHost', function (newData) {
                scope.imgHost = newData || "";
            }, true);
            function titleChanged(newData) {
                scope.options.title = newData || "";
            }

            function handleModelUpdates(newData) {
                scope.options.paginate = newData || {};
            }
        }
    };
}).directive('datatablepost', function () {
    return {
        restrict: 'EA',
        scope: {
            options: "=",
            imgHost: "@",
            pageChanged: "&",
            itemClick: "&",
            itemDelete: "&",
            itemOnline: "&",
            itemOffline: "&"
        },
        templateUrl: '/admin/template/dataTablePost.html',
        link: function (scope, element, attrs) {
            scope.$watch('options.paginate', handleModelUpdates, true);
            scope.$watch('options.title', titleChanged, true);
            scope.$watch('imgHost', function (newData) {
                scope.imgHost = newData || "";
            }, true);
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
            imgHost: "@",
            pageChanged: "&",
            itemDelete: "&"
        },
        templateUrl: '/admin/template/dataTableImage.html',
        link: function (scope, element, attrs) {
            scope.$watch('options.paginate', handleModelUpdates, true);
            scope.$watch('options.title', titleChanged, true);
            scope.$watch('imgHost', function (newData) {
                scope.imgHost = newData || "";
            }, true);
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
            imgHost: "@",
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

            scope.$watch('imgHost', function (newData) {
                scope.imgHost = newData || "";
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
                            scope.attList = [];
                            scope.attList.push(item.value);
                        } else {
                            scope.attList.push(item.value);
                        }
                    }
                }
            };
            scope.$watch('attList', function (value, oldValue, scope) {
                console.log(value)
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
                eval("scope.$parent." + scope.timeStart + "='" + start.format('YYYY/MM/DD hh:mm:ss') + "'");
                eval("scope.$parent." + scope.timeEnd + "='" + end.format('YYYY/MM/DD hh:mm:ss') + "'");
            });
        }
    };
}).directive('simplemde', function () {
    return {
        restrict: 'EA',
        scope: {
            text: '='
        },
        templateUrl: '/admin/template/simpleMDE.html',
        link: function (scope, element, attrs) {
            scope.simplemde = new SimpleMDE({
                element: $(element).find('#detail')[0],
                spellChecker: false,
                autosave: {
                    enabled: true,
                    unique_id: "detail"
                }
            });
            scope.$watch('text', function (newData) {
                scope.text = newData || "";
                scope.simplemde.value(scope.text);
            }, true);
        }
    };
}).directive('mbSimplemde', function () {
    return {
        restrict: 'A',
        require: 'ngModel',
        link: function ($scope, element, attributes, ngModel) {
            var inputEvents = ['+input', 'paste'];
            var options = $scope.$eval(attributes.mbSimplemde) || {};
            options.element = element.get(0);
            var simplemde = new SimpleMDE(options);

            var maxLength = parseInt(attributes.mbSimplemdeMaxLength, 10);
            if (!!maxLength) {
                element.siblings('.editor-statusbar').append('<span class="maxlength"></span>');
            }
            var maxLengthElement = element.siblings('.editor-statusbar').find('.maxlength');

            $scope.$watch(attributes.ngModel, function (value) {
                if (simplemde.value() !== value) {
                    simplemde.value(value);
                }
            });

            simplemde.codemirror.on('change', function (instance, changeObj) {
                // Check if we're now at max length and set a warning.
                if (!!maxLength) {
                    if (simplemde.value().length === maxLength) {
                        maxLengthElement.text('Maximum characters reached');
                    } else {
                        maxLengthElement.text('');
                    }
                }

                // Update the view value, so that all standard ngModel
                // parsers/validators get triggered
                //
                // We have to apply this explicitly as the
                // $setViewValue doesn't always force a digest
                $scope.$apply(function () {
                    ngModel.$setViewValue(simplemde.value());
                });
            });

            simplemde.codemirror.on('beforeChange', function (instance, changeObj) {
                // If we have a maxlength set, make sure that this input won't exceed it
                // Currently only handling character '+input' and 'paste' events
                if (!!maxLength && _.include(inputEvents, changeObj.origin)) {
                    var newTextLength = _.reduce(changeObj.text, function (memo, text) {
                        return memo + text.length;
                    }, changeObj.text.length - 1);
                    if (simplemde.value().length + newTextLength > maxLength) {
                        maxLengthElement.text('This would exceed your character limit');
                        changeObj.cancel();
                    }
                }
            });
        }
    };
}).directive('duiDateRange', function ($filter) {

    var template = '<span class="in_block">\
							<span>\
								<ul class="dui-component-daterange-datetype in_block center" dui-if="isButton">\
									<li ng-class="{current: dateType==Constants.TODAY}" class="first-item" ng-click="setDate(today, today);dateType=Constants.TODAY;">{{Constants.TODAY}}</li>\
									<li ng-class="{current: dateType==Constants.THIS_WEEK}" ng-click="setDate(weekStart, weekEnd);dateType=Constants.THIS_WEEK;">{{Constants.THIS_WEEK}}</li>\
									<li ng-class="{current: dateType==Constants.THIS_MONTH}" ng-click="setDate(monthStart, monthEnd);dateType=Constants.THIS_MONTH;">{{Constants.THIS_MONTH}}</li>\
									<li ng-class="{current: dateType==Constants.ALL}" ng-click="setDate(\'\', \'\');dateType=Constants.ALL;">{{Constants.ALL}}</li>\
								</ul>\
								<span dui-if="!isButton">\
									<label><input type="radio" value="{{Constants.TODAY}}" ng-click="setDate(today, today)" ng-model="dateType" name="dateType1">{{Constants.TODAY}}</label>\
									<label class="ml_10"><input value="{{Constants.THIS_WEEK}}" type="radio" ng-click="setDate(weekStart, weekEnd)" ng-model="dateType" name="dateType1">{{Constants.THIS_WEEK}}</label>\
									<label class="ml_10"><input value="{{Constants.THIS_MONTH}}" type="radio" ng-click="setDate(monthStart, monthEnd)" ng-model="dateType" name="dateType1">{{Constants.THIS_MONTH}}</label>\
									<label class="ml_10"><input value="{{Constants.ALL}}" type="radio" ng-click="setDate(\'\', \'\')" ng-model="dateType" name="dateType1">{{Constants.ALL}}</label>\
								</span>\
								<span class="ml_10" ng-show="dateType == Constants.ALL">\
									<dui-date-picker date="startDate"></dui-date-picker>\
									<dui-date-picker date="endDate"></dui-date-picker>\
								</span>\
							</span>\
						</span>';

    return {
        restrict: 'EA',
        replace: true,
        transclude: true,
        scope: {
            startDate: '=',
            endDate: '='
        },
        template: template,
        link: function (scope, el, attrs) {

            scope.isButton = attrs.uiStyle === 'button' || attrs.uiStyle === undefined;

            var now = new Date(),
                weekRange = getWeekRange(now),
                monthRange = getMonthRange(now);

            scope.today = formatDate(now);
            scope.weekStart = formatDate(weekRange.begin);
            scope.weekEnd = formatDate(weekRange.end);
            scope.monthStart = formatDate(monthRange.begin);
            scope.monthEnd = formatDate(monthRange.end);

            scope.Constants = {
                TODAY: '今日',
                THIS_WEEK: '本周',
                THIS_MONTH: '本月',
                ALL: '所有'
            };

            //初始化
            scope.$watch('startDate+endDate', function (val) {
                if (scope.startDate === scope.today && scope.endDate === scope.today) {
                    scope.dateType = scope.Constants.TODAY;
                } else if (scope.startDate === scope.weekStart && scope.endDate === scope.weekEnd) {
                    scope.dateType = scope.Constants.THIS_WEEK;
                } else if (scope.startDate === scope.monthStart && scope.endDate === scope.monthEnd) {
                    scope.dateType = scope.Constants.THIS_MONTH;
                } else {
                    scope.dateType = scope.Constants.ALL;
                }
            });

            scope.setDate = function (begin, end) {
                scope.startDate = begin;
                scope.endDate = end;
            }

            function formatDate(date) {
                return $filter('date')(date, 'yyyy-MM-dd');
            }
        }
    };
}).directive('duiDatePicker', function ($http, $filter, $rootScope, duiDatePickerService) {
    /**
     内部模型接口：
     --------------------------
     year：选中的年份
     month：选中的月份
     yearRange: 年份的可选范围
     months：展示可选择的月份范围
     days：展示可选的天（根据年份和月份计算出来）
     **/

    return {
        restrict: 'EA',
        replace: true,
        template: '<div class="in_block pos_r">\
						<input type="text" ng-class="{bg_none: date, disable: disabledInput}" class="txtDate" value="{{formatDisplayDate()}}" ng-click="clickSelectInput($event)" readonly>\
						<div class="date_selector js_date_selector" ng-click="clickPanel($event)" style="width:{{isMonth && 185 || 215}}px;">\
							<div class="nav_d clearfix" style="width:{{isMonth && 185 || 215}}px;">\
								<p class="year_nav">\
									<select class="selYear" ng-model="year" ng-options="y for y in getYearRange()"></select>年\
								</p>\
								<p class="month_nav">\
									<select ng-hide="isMonth" class="selMonth" ng-model="month" ng-options="month for month in monthRange"></select>\
									<span class="in_block" style="padding-top:3px;" ng-show="isMonth">{{month}}<span ng-class="{bold: isMonth}">月</span></span>\
								</p>\
								<a href="javascript:;" class="clearDate" ng-hide="cannotEmpty" ng-click="clearDate()">清空</a>\
							</div>\
							<table ng-class="{mt_5: isMonth}">\
								<tbody ng-hide="isMonth">\
									<tr><th ng-repeat="week in weekRange">{{week}}</th></tr>\
									<tr ng-repeat="row in days">\
										<td ng-class="getDayClass(day)" ng-repeat="day in row" ng-click="selectDate(day)">{{day.getDate()}}</td>\
									</tr>\
								</tbody>\
								<tbody ng-show="isMonth">\
									<tr ng-repeat="month in months">\
										<td ng-repeat="day in month" ng-class="getDayClass(day)" ng-click="selectDate(day)">{{day.getMonth() + 1}}月</td>\
									</tr>\
								</tbody>\
							</table>\
						</div>\
					</div>',
        scope: {
            date: '=',
            yearStart: '@',
            yearEnd: '@',
            selectableStart: '@',
            selectableEnd: '@',
            disabled: '@',
            selectCallback: '&'
        },
        link: function (scope, el, attrs) {
            scope.now = new Date();
            scope.isMonth = attrs.onlymonth !== undefined;		//是选日期控件， 还是选月份控件
            scope.cannotEmpty = attrs.hasEmpty === 'false';		//判断可否清空选择的日期

            scope.$watch('disabled', function (disabled) {
                scope.disabledInput = disabled === 'true';
            });

            var $popup = el[0].querySelector('.js_date_selector');

            //初始化选中的日期，年份，月份
            scope.$watch('date', function (value, oldVal) {
                if (angular.isNumber(value)) {
                    scope.date = $filter('date')(value, 'yyyy-MM-dd');
                }
                var initDate = computeSelectedDate();

                scope.year = initDate.getFullYear();		//选中年
                scope.month = (initDate.getMonth() + 1);	//选中月份
            });

            //选择日期
            scope.selectDate = function (clickDate) {
                if (!duiDatePickerService.isSelectable(clickDate, scope.month, scope.selectableStart, scope.selectableEnd, scope.isMonth)) return;

                scope.date = $filter('date')(clickDate, 'yyyy-MM-dd');

                if (attrs.selectCallback !== undefined) {
                    scope.selectCallback({date: scope.date});
                }

                hidePopup();
            };

            //构建年份的区间
            scope.getYearRange = function () {
                var start = scope.yearStart ? Number(scope.yearStart) : (scope.now.getFullYear() - 10),
                    end = scope.yearEnd ? Number(scope.yearEnd) : (scope.now.getFullYear() + 10);

                return duiDatePickerService.buildRange(start, end).reverse();
            };

            //月区间
            scope.monthRange = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12];
            //周区间
            scope.weekRange = ['一', '二', '三', '四', '五', '六', '日'];

            //年份和月份变化时，重新构建面板内容
            scope.$watch('year + month', function (newValue, oldValue) {
                if (scope.isMonth) {
                    scope.months = duiDatePickerService.buildMonthPanel(scope.year);
                } else {
                    scope.days = duiDatePickerService.buildDatePanel(scope.year, scope.month);
                }
            });

            //点击选择框
            var allPopup = null;
            scope.clickSelectInput = function ($event) {
                if (scope.disabledInput) return;

                $rootScope.$broadcast('angular-show-datepicker', 'angularDatepicker');

                $event.stopPropagation();

                if (!allPopup) {
                    allPopup = document.querySelectorAll('.js_date_selector') || [];
                }
                for (var i = 0, len = allPopup.length; i < len; i++) {
                    allPopup[i].style.display = 'none';
                }
                showPopup();
            };

            //点击控件外部区域，隐藏日期选择框
            angular.element(document).bind('click', function () {
                hidePopup();
            });

            //点击日期选择框阻止冒泡
            scope.clickPanel = function ($event) {
                $event.stopPropagation();
            };

            //清空日期
            scope.clearDate = function () {
                scope.date = undefined;
                hidePopup();
            }

            //格式化显示的日期
            scope.formatDisplayDate = function () {
                return scope.isMonth ? $filter('date')(scope.date, 'yyyy年MM月') : $filter('date')(scope.date, 'yyyy-MM-dd');
            }

            //生成面板中每个格子需要的class
            scope.getDayClass = function (currentDay) {
                var isSelectable = duiDatePickerService.isSelectable(currentDay, scope.month, scope.selectableStart, scope.selectableEnd, scope.isMonth);
                return {
                    selectable_day: isSelectable,
                    unselected_month: !isSelectable,
                    today: duiDatePickerService.isDateEqual(scope.now, currentDay, scope.isMonth),
                    selected: duiDatePickerService.isDateEqual(computeSelectedDate(), currentDay, scope.isMonth)
                };
            };

            function computeSelectedDate() {
                var initDate = scope.date || scope.selectableStart;
                return initDate ? new Date(initDate) : new Date();
            }

            function showPopup() {
                $popup.style.display = 'block';
            }

            function hidePopup() {
                $popup.style.display = 'none';
                $rootScope.$broadcast('angular-hide-datepicker', 'angularDatepicker');
            }
        }
    }
}).factory('duiDatePickerService', function ($filter) {
    //计算每个月多少天算法
    var dateUtils = {
        start_of_week: 1,
        daysBetween: function (start, end) {
            start = Date.UTC(start.getFullYear(), start.getMonth(), start.getDate());
            end = Date.UTC(end.getFullYear(), end.getMonth(), end.getDate());
            return (end - start) / 86400000;
        },
        changeDayTo: function (dayOfWeek, date, direction) {
            var difference = direction * (Math.abs(date.getDay() - dayOfWeek - (direction * 7)) % 7);
            return new Date(date.getFullYear(), date.getMonth(), date.getDate() + difference)
        },
        rangeStart: function (date) {
            return this.changeDayTo(this.start_of_week, new Date(date.getFullYear(), date.getMonth()), -1)
        },
        rangeEnd: function (date) {
            return this.changeDayTo((this.start_of_week - 1) % 7, new Date(date.getFullYear(), date.getMonth() + 1, 0), 1)
        },
        isFirstDayOfWeek: function (date) {
            return date.getDay() == this.start_of_week;
        },
        isLastDayOfWeek: function (date) {
            return date.getDay() == (this.start_of_week - 1) % 7
        }
    };

    return {
        buildMonthPanel: function (year) {		//构建月份面板数据
            if (!year) return [];

            //面板要显示为两行，所以数组中为两个元素，每个元素表示一行
            return [this.buildRange(1, 6, buildDate), this.buildRange(7, 12, buildDate)];

            function buildDate(month) {
                return new Date(year, month - 1, 1);
            }
        },
        buildDatePanel: function (year, month) {	//构建日期面板数据
            if (!year || !month) return [];

            var date = new Date(year, month - 1, 1),
                rangeStart = dateUtils.rangeStart(date),
                rangeEnd = dateUtils.rangeEnd(date),
                numDays = dateUtils.daysBetween(rangeStart, rangeEnd),
                row = [],
                days = [];

            for (var i = 0; i <= numDays; i++) {
                var currentDay = new Date(rangeStart.getFullYear(), rangeStart.getMonth(), rangeStart.getDate() + i, 12, 00);
                if (dateUtils.isFirstDayOfWeek(currentDay)) {
                    row = [];
                }
                row.push(currentDay);
                if (dateUtils.isLastDayOfWeek(currentDay)) {
                    days.push(row);
                }
            }
            ;

            return days;
        },
        isSelectable: function (currentDay, currentMonth, selectableStart, selectableEnd, isMonth) {		//处理日期是否可选择
            selectableStart = selectableStart || '1800-01-01';
            selectableEnd = selectableEnd || '2099-01-01';
            var strCurDay = $filter('date')(currentDay, isMonth ? 'yyyy-MM-01' : 'yyyy-MM-dd');

            if (isMonth) {
                return strCurDay >= selectableStart && strCurDay <= selectableEnd;
            }
            return currentDay.getMonth() == (currentMonth - 1) && strCurDay >= selectableStart && strCurDay <= selectableEnd;
        },
        isDateEqual: function (date1, date2, isMonth) {	//判断日期是否相等
            var result = date1.getFullYear() === date2.getFullYear() && date1.getMonth() == date2.getMonth();
            if (!isMonth) {
                result = result && date1.getDate() == date2.getDate();
            }
            return result;
        },
        buildRange: function (start, end, callback) {		//构建数据区间
            var result = [];
            for (var y = start; y <= end; y++) {
                result.push(callback ? callback(y) : y);
            }
            return result;
        }
    };
}).directive('datePicker', function () {
    return {
        restrict: 'A',
        require: '?ngModel',
        scope: {
            select: '&'
        },
        link: function (scope, element, attrs, ngModel) {
            if (element.data('initialized') !== true) {
                element.date_input();
            }

            if (!ngModel) return;

            //view ==> model
            element.change(function () {
                scope.$apply(function () {
                    ngModel.$setViewValue(element.hasClass('onlyMonth') ? element.attr('onlymonthdate') : element.val());

                    if (scope.select) {
                        scope.select();
                    }
                });
            });

            //model ==> view
            ngModel.$render = function () {
                var result = ngModel.$viewValue || '';
                element.val(element.hasClass('onlyMonth') ? date2MonthDate(result) : result);

                if (result != '') {
                    element.addClass('bg_none');
                }
            };

            function date2MonthDate(date) {
                if (date == '') return '';

                var objDate = new Date(date);
                return objDate.getFullYear() + '年' + (objDate.getMonth() + 1) + '月';
            }
        }
    };
});

function getWeekRange(date) {		//获取每周的起始日期
    var nowdate = date || new Date(),
        nowtime = nowdate.getTime(),
        dayOfThisWeek = nowdate.getDay(),
        beginDayOfThisWeek = (dayOfThisWeek === 0) ? -6 : (-dayOfThisWeek + 1),        //上周日距离今天的天数（负数表示）; 如果今天是周日，则为-7
        endDayOfThisWeek = (dayOfThisWeek === 0) ? 0 : 7 - dayOfThisWeek; // 周日距离今天的天数

    return {
        begin: new Date(nowtime + beginDayOfThisWeek * 24 * 3600 * 1000),
        end: new Date(nowtime + endDayOfThisWeek * 24 * 3600 * 1000)
    }
}

function getMonthRange(date) {	//获取每月的起始日期
    var nowdate = date || new Date(),
        year = nowdate.getFullYear(),
        month = nowdate.getMonth(),
        monthlist = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];

    if ((year % 4 == 0 && year % 100 != 0) || (year % 100 == 0 && year % 400 == 0)) {
        monthlist[1] = 29;
    }

    return {
        begin: new Date(year, month, 1),
        end: new Date(year, month, monthlist[month])
    }
}
