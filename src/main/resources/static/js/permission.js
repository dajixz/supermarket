layui.use(['layer','form'], function () {
    var layer = layui.layer;
    var form =layui.form;
    var app = new Vue({
        el: '#layui',
        data: {
            permissionList: []
        },
        created: function () {
            this.getPermissionList()
        },
        methods: {
            getPermissionList: function () {
                $.ajax({
                    type: 'GET',
                    url: '/admin/getPermissionList',
                    dataType: 'json',
                    async: true,
                    success: function (res) {
                        if(res.code == 403){
                            layer.msg(res.msg)
                        }
                        if(res.code ==200){
                            app.permissionList = res.data;
                        }

                    }
                })
            }
        }
    })
})