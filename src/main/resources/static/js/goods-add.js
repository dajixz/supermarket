layui.use(['form', 'layer', 'laydate'], function () {
    $ = layui.jquery;
    var form = layui.form;
    var laydate = layui.laydate;
    var layer = layui.layer;
    laydate.render({
        elem: "#produceTime"
    });
    laydate.render({
        elem: "#dueTime"
    });
    form.on('radio(filter)', function (data) {
        if (data.value == 0) {
            $("#div").append('<div id="d">\n' +
                '                <div class="layui-form-item">\n' +
                '                    <label for="produceTime" class="layui-form-label">\n' +
                '                        <span class="x-red">*</span>生产日期\n' +
                '                    </label>\n' +
                '                    <div class="layui-input-inline">\n' +
                '                        <input class="layui-input" placeholder="生产日期" name="produceTime" id="produceTime" lay-verify="date">\n' +
                '                    </div>\n' +
                '                </div>\n' +
                '                <div class="layui-form-item">\n' +
                '                    <label for="dueTime" class="layui-form-label">\n' +
                '                        <span class="x-red">*</span>到期时间\n' +
                '                    </label>\n' +
                '                    <div class="layui-input-inline">\n' +
                '                        <input class="layui-input" placeholder="到期时间" name="dueTime" id="dueTime" lay-verify="date">\n' +
                '                    </div>\n' +
                '                </div>\n' +
                '                <div class="layui-form-item">\n' +
                '                    <label for="producer" class="layui-form-label">\n' +
                '                        <span class="x-red">*</span>生厂商\n' +
                '                    </label>\n' +
                '                    <div class="layui-input-inline">\n' +
                '                        <input class="layui-input" name="producer" id="producer" lay-verify="required">\n' +
                '                    </div>\n' +
                '                </div>\n' +
                '            </div>');
            laydate.render({
                elem: "#produceTime"
            });
            laydate.render({
                elem: "#dueTime"
            });
        } else if (data.value == 1) {
            $("#d").remove();

        }
    })



    //监听提交
    form.on('submit(add)', function (res) {
        if (res.field.produceTime != null) {
            res.field.produceTime = new Date(res.field.produceTime)
            res.field.dueTime = new Date(res.field.dueTime)
        }
        // 发异步，把数据提交
        $.ajax({
            type: 'POST',
            url: '/admin/saveGoods',
            data: res.field,
            async: true,
            success: function (res) {
                if(res.code==200){
                    layer.alert(res.msg, {icon: 6}, function () {
                        // 获得frame索引
                        var index = parent.layer.getFrameIndex(window.name);
                        //关闭当前frame
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