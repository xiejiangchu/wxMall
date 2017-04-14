var mall = angular.module('com.xie.mall', ['ui.router']);
mall.factory('$dictionary', function () {
    var dictionary = {};

    dictionary.checkStatus = [
        {value: "0", text: "不限"},
        {value: "10", text: "待支付"},
        {value: "20", text: "待发货"},
        {value: "30", text: "待收货"},
        {value: "40", text: "已完成"}

    ];

    return dictionary;
});


mall.filter('orderstatus', function () {
    var orderStatus = {
        10: '进行中',
        20: '已取消',
        30: '已完成',
        40: '已删除',
        50: '系统回收'
    };
    return function (input, param) {
        if (typeof (input) == 'number') {
            return orderStatus[input];
        }
        return input;
    }
});
mall.filter('paystatus', function () {
    var payStatus = {
        00: '货到付款',
        10: '未支付',
        20: '已支付'
    };
    return function (input, param) {
        if (typeof (input) == 'number') {
            return payStatus[input];
        }
        return input;
    }
});
mall.filter('shipstatus', function () {
    var shipStatus = {
        10: '待配送',
        20: '配送中',
        30: '已配送'
    };
    return function (input, param) {
        if (typeof (input) == 'number') {
            return shipStatus[input];
        }
        return input;
    }
});
mall.filter('packagestatus', function () {
    var packageStatus = {
        10: '已打包',
        20: '未打包'
    };
    return function (input, param) {
        if (typeof (input) == 'number') {
            return packageStatus[input];
        }
        return input;
    }
});