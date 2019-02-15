$(function () {
    MembershipUser.init();
});

var MembershipUser = {
    init: function () {
        UserMVC.View.initControl();
        UserMVC.View.bindEvent();
        UserMVC.View.bindValidate();
        UserMVC.View.initData();
    }
};

var UserCommon = {
    baseUrl: EasyReport.ctxPath + '/rest/membership/people/',
    baseRoleUrl: EasyReport.ctxPath + '/rest/membership/role/',
    baseIconUrl: EasyReport.ctxPath + '/assets/custom/easyui/themes/icons/'
};

var UserMVC = {
    URLs: {
        add: {
            url: UserCommon.baseUrl + 'add',
            method: 'POST'
        },
        editOne: {
            url: UserCommon.baseUrl + 'editOne',
            method: 'GET'
        },
        edit: {
            url: UserCommon.baseUrl + 'edit',
            method: 'POST'
        },
        list: {
            url: UserCommon.baseUrl + 'listPeople',
            method: 'GET'
        },
        remove: {
            url: UserCommon.baseUrl + 'remove',
            method: 'POST'
        },
        getRoleList: {
            url: UserCommon.baseRoleUrl + 'getRoleList',
            method: 'GET'
        },
        editPassword: {
            url: UserCommon.baseUrl + 'editPassword',
            method: 'POST'
        }
    },
    Model: {
        roles: {}
    },
    View: {
        initControl: function () {
            $('#user-datagrid').datagrid({
                method: 'get',
                fit: true,
                fitColumns: true,
                singleSelect: true,
                pagination: true,
                rownumbers: true,
                pageSize: 50,
                url: UserMVC.URLs.list.url,

                loadFilter: function (src) {
                    if (src.success) {
                        return src.data;
                    }
                    return $.messager.alert('失败', src.msg, 'error');
                },
                columns: [[{
                    field: 'code',
                    title: '柜员号',
                    width: 100,
                    sortable: true
                }, {
                    field: 'name',
                    title: '姓名',
                    width: 100,
                    sortable: true,
                }, {
                    field: 'orgCode',
                    title: '机构号',
                    width: 100,
                    sortable: true
                }, {
                    field: 'shortName',
                    title: '所在机构',
                    width: 100,
                    sortable: true
                }, {
                    field: 'shortName',
                    title: '机构简称',
                    width: 100,
                    sortable: true
                }, {
                    field: 'type',
                    title: '人员类别',
                    width: 100,
                    sortable: true
                },{
                    field: 'status',
                    title: '状态',
                    width: 50,
                    sortable: true,
                    formatter: function (value, row, index) {
                        return value == 1  ? "禁用" : "启用";
                    }
                },{
                    field: 'options',
                    title: '操作',
                    width: 100,
                    formatter: function (value, row, index) {
                        var icons = [{
                            "name": "edit",
                            "title": "轨迹查看"
                        }];
                        var buttons = [];
                        for (var i = 0; i < icons.length; i++) {
                            var tmpl = '<a href="#" title ="${title}" '
                                + 'onclick="UserMVC.Controller.doOption(\'${index}\',\'${name}\')">'
                                + '<img src="${imgSrc}" alt="${title}"/"></a>';
                            var data = {
                                title: icons[i].title,
                                name: icons[i].name,
                                index: index,
                                imgSrc: UserCommon.baseIconUrl + icons[i].name + ".png"
                            };
                            buttons.push(juicer(tmpl, data));
                        }
                        return buttons.join(' ');
                    }
                }]],
                onDblClickRow: function (rowIndex, rowData) {
                    return UserMVC.Controller.edit();
                }
            });

            // dialogs
            $('#user-dlg').dialog({
                closed: true,
                modal: false,
                width: window.screen.width - 350,
                height: window.screen.height - 350,
                maximizable: true,
                minimizable: true,
                maximized: true,
                iconCls: 'icon-add',
                buttons: [{
                    text: '关闭',
                    iconCls: 'icon-no',
                    handler: function () {
                        $("#user-dlg").dialog('close');
                    }
                }, {
                    text: '保存',
                    iconCls: 'icon-save',
                    handler: UserMVC.Controller.save
                }]
            });


        },
        bindEvent: function () {
            $('#btn-search').bind('click', UserMVC.Controller.find);
        },
        bindValidate: function () {
        },
        initData: function () {
            UserMVC.Util.loadRoleList();
        }
    },
    Controller: {
        doOption: function (index, name) {
            $('#user-datagrid').datagrid('selectRow', index);
            if (name == "edit") {
                return UserMVC.Controller.edit();
            }

        },
        find: function () {
            var name = $("#name").val();
            var code=$("#code").val();
            var card=$("#card").val();
            var orgCode=$("#orgCode").val();
            var url = UserMVC.URLs.list.url + '?name=' + name + '&code='+code+ '&orgCode='+orgCode+ '&card='+card ;
            EasyUIUtils.loadToDatagrid('#user-datagrid', url)
        },
        edit: function () {
            debugger;
            var row = $('#user-datagrid').datagrid('getSelected');
            if (row) {
                var options = UserMVC.Util.getOptions();
                options.iconCls = 'icon-edit1';
                options.data = row;
                options.title = '查看[' + options.data.name + ']用户';
                var id=row.id;
                //
                //
                console.log(123);
                $.ajax({
                    url: UserMVC.URLs.editOne.url,
                    data: {id: id},
                    type: "GET",
                    dataType: "json",
                    success: function(res) {
                        debugger;
                        console.log(res);
                        if(res){
                           var resData=res.data.list;
                           var htm="";
                            for(j = 0,len=resData.length; j < len; j++) {
                                debugger;
                                var temp="<li><b></b><span>"+(j+1);
                                temp=temp+"</span><a href=\"javascript:void(0)\">"+resData[j];
                                temp=temp+"</a></li>";
                               htm=htm+temp;
                            };
                            $("#timeLine").html(htm);
                        }
                    }
                });

                //
                EasyUIUtils.openEditDlg(options);
                $('#password').textbox('setValue', '');
                $('#account').textbox('readonly', true);
                var roleIds = row.roles || "";
                UserMVC.Util.fillRoleCombox("edit", roleIds.split(','));
            } else {
                $.messager.alert('警告', '请选中一条记录!', 'info');
            }
        }
    },
    Util: {
        getOptions: function () {
            return {
                dlgId: '#user-dlg',
                formId: '#user-form',
                actId: '#modal-action',
                rowId: '#roleId',
                title: '',
                iconCls: 'icon-add',
                data: {},
                callback: function (arg) {
                },
                gridId: null,
            };
        },
        fillRoleCombox: function (act, values) {
            var id = '#combox-roles';
            $(id).combobox('clear');
            var data = [];
            var items = UserMVC.Model.roles;
            for (var i = 0; i < items.length; i++) {
                var item = items[i];
                data.push({
                    "value": item.id,
                    "name": item.name,
                    "selected": i == 0
                });
            }
            $(id).combobox('loadData', data);
            if (act == "edit") {
                $(id).combobox('setValues', values);
            }
        },
        loadRoleList: function () {
            $.getJSON(UserMVC.URLs.getRoleList.url, function (src) {
                UserMVC.Model.roles = src.data;
            });
        }
    }
};