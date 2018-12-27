layui.use(['layer', 'laypage', 'form'], function () {
    var layer = layui.layer,
        form = layui.form
    laypage = layui.laypage;


    var app = new Vue({
        el: '#layui',
        data: {
            goodsList: [],
            totalElements: ''
        },
        created: function () {
            this.getGoodsList(1)
        },
        methods: {
            getGoodsList: function (page) {
                var page = page
                $.ajax({
                    type: 'GET',
                    url: '/goods/getGoodsList',
                    dataType: 'json',
                    data: {
                        page: page,
                    },
                    async: true,
                    success: function (res) {
                        if (res.code == 403) {
                            layer.msg(res.msg)
                            return;
                        }
                        app.goodsList = res.content
                        app.totalElements = res.totalElements
                        laypage.render({
                            elem: 'laypage',
                            count: res.totalElements,//数据总数，从服务端得到
                            curr: res.number + 1,
                            limit: res.size,
                            jump: function (obj, first) {
                                if (!first) {
                                    app.getGoodsList(obj.curr)
                                }

                            }
                        });
                    }
                })
            },
            searchGoodsList: function (page) {
                var goodsName = $("#goodsName").val();
                $.ajax({
                    type: 'GET',
                    url: '/goods/getGoods',
                    dataType: 'json',
                    data: {
                        page: page,
                        goodsName: goodsName
                    },
                    async: true,
                    success: function (res) {
                        app.goodsList = res.content
                        app.totalElements = res.totalElements
                        laypage.render({
                            elem: 'laypage',
                            count: res.totalElements,//数据总数，从服务端得到
                            curr: res.number + 1,
                            limit: res.size,
                            jump: function (obj, first) {
                                if (!first) {
                                    app.searchGoodsList(obj.curr)
                                }
                            }
                        });
                    }
                })
            },
            findGoodsByCategory: function (page,category) {
                $.ajax({
                    type: 'GET',
                    url: '/goods/getGoodsByCategory',
                    dataType: 'json',
                    data: {
                        page: page,
                        category: category
                    },
                    async: true,
                    success: function (res) {
                        app.goodsList = res.content
                        app.totalElements = res.totalElements
                        laypage.render({
                            elem: 'laypage',
                            count: res.totalElements,//数据总数，从服务端得到
                            curr: res.number + 1,
                            limit: res.size,
                            jump: function (obj, first) {
                                if (!first) {
                                    app.findGoodsByCategory(obj.curr)
                                }
                            }
                        });
                    }
                })
            }
        }
    })
})

function goods_del(url, obj) {
    if (obj != null) {
        var id = $(obj).attr("lay-id");
    }
    layer.open({
        content: "删除货品",
        btn: ['确定', '取消'],
        yes: function () {
            $.ajax({
                type: 'delete',
                url: url,
                dataType: 'json',
                data: {
                    id: id
                },
                async: true,
                success: function (res) {
                    if (res.code == 200) {
                        layer.alert(res.msg, {icon: 6});
                        window.location.reload();
                    }else if(res.code=403){
                        layer.msg(res.msg)
                    }
                }
            })
        },
        btn2: function () {
        }
    })
}

function toUpdataShow(url, obj) {
    if (obj != null) {
        var id = $(obj).attr("lay-id");
        url += "/" + id;
    }

    layer.open({
        type: 2,
        area: [($(window).width() * 0.9) + 'px', ($(window).height() - 50) + 'px'],
        fix: false, //不固定
        maxmin: true,
        shadeClose: true,
        shade: 0.4,
        title: '货品修改',
        content: url
    });
}