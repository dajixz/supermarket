layui.use(['form', 'layer'], function () {
    $ = layui.jquery;
    var form = layui.form;
    var layer = layui.layer;

    form.on('submit(add)', function (res) {
        $.ajax({
            type: 'POST',
            url: '/user/updateUser',
            data: res.field,
            async: true,
            success: function (res) {
                if(res.code==200){
                    layer.alert(res.msg, {icon: 6}, function () {
                        var index = parent.layer.getFrameIndex(window.name);
                        parent.layer.close(index);
                        window.parent.location.reload();
                    });
                }else if(res.code==403){
                    layer.msg(res.msg);
                    return;
                }
            }
        })
        return false;
    });

    form.on('submit(ad)', function (res) {
        $.ajax({
            type: 'POST',
            url: '/admin/saveUser',
            data: res.field,
            async: true,
            success: function (res) {
                if(res.code==200){
                    layer.alert(res.msg, {icon: 6}, function () {
                        var index = parent.layer.getFrameIndex(window.name);
                        parent.layer.close(index);
                        window.parent.location.reload();
                    });
                }else if(res.code==403){
                    layer.msg(res.msg);
                    return;
                }
            }
        })
        return false;
    });

});