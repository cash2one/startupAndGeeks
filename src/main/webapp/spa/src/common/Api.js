// all apis
define(function() {

    var mockHost = '//www.mocky.io/v2';
    // var testHost = '//192.168.11.111:8080'; //ceshi
    var testHost = '//112.54.207.9:9080'; //ceshi public
    // var testHost = '//172.16.16.143:8080'; //haowentao
    // var testHost = '//172.20.7.11:8080'; //haowentao wifi
    // var testHost = '//localhost:8080'; //本地

    return {
        code: {
            loginSuccess: 0, //登录成功
            loginFail: 1, //登录失败
            tokenFail: 1001 //登录失败
        },
        organization: {
            // orgList:  mockHost + '/559e41ab6384a6cc097284f3'
            orgList: testHost + '/BaiYe/m/corporation/list',
            orgExport: testHost + '/BaiYe/m/corporation/export',
            orgDisable: testHost + '/BaiYe/m/corporation/patch/',
            orgAdd: testHost + '/BaiYe/m/corporation/post',
            orgUpdate: testHost + '/BaiYe/m/corporation/put/'
        },
        url: {
            login: testHost + '/BaiYe/m/log_in'
        },
        extra: {

        },
        createApp: {
            // sortList: mockHost + '/55924ae33c82b2da01eea5d8',
            // templateList: mockHost + '/55924abc3c82b2d701eea5d7',
            categoryList: testHost + '/BaiYe/m/industry/list/',
            categoryListItems: testHost + '/BaiYe/m/industry/moudle/',
            categoryListItemsDetail: testHost + '/BaiYe/m/moudleprop/'
        },
        isDebug: true, //show log information
        logger: function(type, loggerInfo){
            this.isDebug && console.log( '[ '+ new Date() + ' ]: ' + type + ': ');
            this.isDebug && console.log( loggerInfo );
        }
    }
});

