var App = function () {
    //iCheck
    var _masterCheckbox;
    var _checkbox;

    var _idArray;

    var handlerInitCheckbox = function () {

        //激活
        $('input[type="checkbox"].minimal, input[type="radio"].minimal').iCheck({
            checkboxClass: 'icheckbox_minimal-blue',
            radioClass: 'iradio_minimal-blue'
        })
        //获取控制端的Checkbox
        _masterCheckbox = $('input[type="checkbox"].minimal.icheck_master');
        //获取全部Checkbox集合
        _checkbox = $('input[type="checkbox"].minimal');
    };

    /**
     * Checkbox 全选功能
     */
    var handlerCheckboxAll = function () {
        _masterCheckbox.on('ifClicked', function (e) {
            //返回true 表示未选中
            if (e.target.checked) {
                _checkbox.iCheck("uncheck");
            }
            //
            else {
                _checkbox.iCheck("check");
            }
        });
    };
//批量删除
    var handlerDeleteMiti = function (url) {
        _idArray = new Array();

        _checkbox.each(function () {
            var _id = $(this).attr("id");
            if (_id != null && _id != "undefine" && $(this).is(":checked")) {
                _idArray.push(_id);
            }
        });
        //判断用户是否选择数据项
        if (_idArray.length === 0) {
            $("#modal-message").html("你还没有选择任何数据,请至少选择一项");
        } else {
            $("#modal-message").html("您确定删除?");
        }

        $("#modal-default").modal("show");

        $("#btnModalOk").bind("click", function () {
            del();
        })

        /**
         * 当前私有函数的私有函数,删除数据
         */
        function del() {

            if (_idArray.length === 0) {
                $("#modal-default").modal("hide");
            }
            //删除操作
            else {
                $.ajax({
                    "url": url,
                    "type": "POST",
                    "data": {"ids": _idArray.toString()},
                    "dataType": "JSON",
                    "success": function (data) {
                        //删除成功
                        if (data.status === 200) {
                            window.location.reload();
                        }
                        //删除失败
                        else {
                            _idArray = new Array();
                            $("#modal-message").html(data.message);
                            //    $("#modal-message").modal("show");
                        }
                    }
                })
            }
        }
    }
    /**
     * 初始化dataTable
     */
    var handlerInitDataTables = function (url, colums) {
        var _dataTable = $("#dataTable").DataTable({
            "paging": true,
            "info": true,
            "lengthChange": false,
            "ordering": false,
            "processing": true,
            "searching": false,
            "serverSide": true,
            "deferRender": true,
            "language": {
                "sProcessing": "处理中...",
                "sLengthMenu": "显示 _MENU_ 项结果",
                "sZeroRecords": "没有匹配结果",
                "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
                "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
                "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
                "sInfoPostFix": "",
                "sSearch": "搜索:",
                "sUrl": "",
                "sEmptyTable": "表中数据为空",
                "sLoadingRecords": "载入中...",
                "sInfoThousands": ",",
                "oPaginate": {
                    "sFirst": "首页",
                    "sPrevious": "上页",
                    "sNext": "下页",
                    "sLast": "末页"
                },
                "oAria": {
                    "sSortAscending": ": 以升序排列此列",
                    "sSortDescending": ": 以降序排列此列"
                }
            },
            "ajax": {
                "url": url,

            },
            "columns": colums,
            "drawCallback": function (settings) {
                handlerInitCheckbox();
                handlerCheckboxAll();
            }
        });
        return _dataTable;
    };

    /**
     * 查看详情
     * @param url
     */
    var handerShowDetail = function (url) {
        //这里是通过ajax 请求 html 将 jsp 转载进模态框中
        $.ajax({
            url: url,
            type: "get",
            dataType: "html",
            success: function (data) {
                $("#modal-detail-body").html(data);
                $("#modal-detail").modal("show");

            }
        })
    };
    /**
     *初始化ztree
     * @param url
     */
    var handlerInitZTree = function (url,autoParam,callback) {
        var setting = {
            view: {
                selectedMulti: false
            },
            async: {
                enable: true,
                url: url,
                autoParam: autoParam
            }
        };
        $.fn.zTree.init($("#myTree"), setting);

        $("#btnModalOk").bind("click", function () {
            var zTree = $.fn.zTree.getZTreeObj("myTree");
            var nodes = zTree.getSelectedNodes();

            //type = e.data.type,
                //silent = e.data.silent,

            if (nodes.length == 0) {
                alert("请先选择一个节点");
            }
            //选中
            else {
               callback(nodes);
            }
        });
    }


    return {
        init: function () {
            handlerInitCheckbox();
            handlerCheckboxAll();
        },
        getCheckbox: function () {
            return _checkbox;
        },
        /**
         * 批量删除
         * @param url
         */
        deleteMuli: function (url) {
            handlerDeleteMiti(url);
        },
        /**
         * DataTable表格初始化
         * @param url
         * @param columns
         * @returns {jQuery}
         */
        initDataTables: function (url, columns) {
            return handlerInitDataTables(url, columns)
        },

        initZtree:function(url,autoParam,callback){
           handlerInitZTree(url,autoParam,callback);
        },
        /**
         *查看详情
         * @param url
         */
        showDetail: function (url) {
            handerShowDetail(url);
        },
    }
}();

$(document).ready(function () {
    App.init();
});