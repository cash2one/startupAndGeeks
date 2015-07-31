//all validation regex
define(['knockout', 'knockout-validation'], function(ko) {

    // init validation
    ko.validation.rules.pattern.message = '格式有误';
    ko.validation.rules.required.message = '必填信息';

    ko.validation.init({
        messagesOnModified: true,
        insertMessages: true
    }, true);

    var regex = {
            username: '^[a-zA-Z]{2,20}$',
            password: '^[a-zA-Z0-9]{2,20}$',
            humanName: "^[A-Za-z0-9\u4E00-\u9FA5_-]{0,20}$",
            orgName: "^[A-Za-z0-9\u4E00-\u9FA5_-]{0,100}$",
            description: "^[A-Za-z0-9\u4E00-\u9FA5_-]{0,40}$",
            longDescription: "^[A-Za-z0-9\u4E00-\u9FA5_-]{0,100}$",
            tel: "^1[0-9]{10}$|^[0-9]{3,4}-[0-9]{7,9}(-[0-9]{3,4})?$",
            cellphone: "^1[0-9]{10}$",
            telephone: "^[0-9]{3,4}-[0-9]{7,9}(-[0-9]{3,4})?$",
            fax: "^[0-9]{3,4}-?[0-9]{7,9}$",
            email: "^[0-9a-zA-Z_\.]+@([-0-9A-Za-z]+\.)+[a-zA-Z]{1,}$",
            zipcode: "^[0-9]{6}$"
        },
        msg = {
            username: "2-20位字母",
            password: "2-20位字母和数字",
            humanName: "最多20个字符。不能包含特殊符号",
            orgName: "最多100个字符。不能包含特殊符号",
            description: '最多40个字符。不能包含特殊符号',
            longDescription: "最多100个字符。不能包含特殊符号",
            tel: "号码格式有误。示例： 13811112222, 0571-88888888",
            cellphone: "手机号码格式有误。示例：13811112222",
            telephone: "固定电话格式有误。示例：0571-88888888, 010-88888888-888",
            fax: "传真格式有误。示例：0571-88888888",
            email: "邮件格式有误。示例：mail@example.com",
            zipcode: "邮政编码为6位数字。示例：310000"
        };

    return {
        regex: regex,
        msg: msg
    }
});

