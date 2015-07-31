define(['jquery', 'datatables'], function($) {

    /* dataTable default setting */
    $.extend(true, $.fn.dataTable.defaults, {
        oLanguage: {
            sSearch: "搜索：",
            sEmptyTable: "没有记录",
            sLengthMenu: "每页显示 _MENU_ 条",
            sZeroRecords: "没有找到任何记录",
            sInfo: "从第 _START_ 条 到 第 _END_ 条，总共： _TOTAL_ 条",
            sInfoEmpty: "总共：0 条",
            sInfoFiltered: "(从 _MAX_ 条记录中查找)",
            sLoadingRecords: "请等待,正在加载...",
            sProcessing: "正在生成表格",
            oPaginate: {
                sFirst: "首页",
                sLast: "尾页",
                sNext: "下一页",
                sPrevious: "上一页"
            }
        }
    });
    /* dataTable default setting */
});

