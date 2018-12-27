layui.use(['layer', 'laydate','laypage'], function () {
    var layer = layui.layer,
        laydate = layui.laydate,
        laypage = layui.laypage;
    var app = new Vue({
        el: '#layui',
        data: {
            userList: [],
            totalElements:''
        },
        created: function () {
            this.getUserList(1)
        },
        methods: {
            getUserList: function (page) {
                var page = page
                $.ajax({
                    type: 'GET',
                    url: '/user/getUserList',
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
                        app.userList = res.content
                        app.totalElements =res.totalElements
                        laypage.render({
                            elem: 'laypage',
                            count: res.totalElements,
                            curr: res.number + 1,
                            limit: res.size,
                            jump: function (obj, first) {
                                if (!first) {
                                    app.getUserList(obj.curr)
                                }

                            }
                        });
                    }
                })
            }
        }
    })
})
function user_del(url,obj) {
    if(obj!=null){
        var id= $(obj).attr("lay-id");
    }
    layer.open({
        content:"删除用户",
        btn:['确定','取消'],
        yes:function () {
            $.ajax({
                type:'delete',
                url:url,
                dataType:'json',
                data:{
                    userId:id
                },
                async:true,
                success:function (res) {
                    if(res.code==200){
                        layer.alert(res.msg,{icon:6});
                        window.location.reload();
                    }else if(res.code=403){
                        layer.msg(res.msg)
                    }
                }
            })
        },
        btn2:function () {

        }

    })
}
function toUpdataShow(url,obj) {
    if(obj!=null){
        var id= $(obj).attr("lay-id");
        url+="/"+id;
    }
    layer.open({
        type: 2,
        area: [($(window).width()*0.9)+'px', ($(window).height() - 50) +'px'],
        fix: false, //���̶�
        maxmin: true,
        shadeClose: true,
        shade:0.4,
        title: '编辑用户',
        content: url
    });
}