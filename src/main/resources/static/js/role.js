layui.use(['layer'], function () {
    var layer = layui.layer;
    var app = new Vue({
        el: '#layui',
        data: {
            roleList: []
        },
        created: function () {
            this.getRoleList()
        },
        methods: {
            getRoleList: function () {
                $.ajax({
                    type: 'GET',
                    url: '/admin/getRoleList',
                    dataType: 'json',
                    async: true,
                    success: function (res) {
                        if (res.code == 403) {
                            layer.msg(res.msg)
                            return;
                        }
                        app.roleList = res.data;
                    }
                })
            }
        }
    })
});
function toRoleEditView(url,obj){
    if (obj != null) {
        var id=$(obj).attr("data");
        url = url+"/"+id
    };
    layer.open({
        type: 2,
        area: [($(window).width() * 0.9) + 'px',($(window).height() - 50) + 'px'],
        fix: false, //不固定
        maxmin: true,
        shadeClose: true,
        shade: 0.4,
        title: "角色编辑",
        content: url
    });
}
