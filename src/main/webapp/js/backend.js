$(function () {
    //产品新增js
    $("#product_add").click(function () {
        var productName = "前端获取的产品名称①";
        var productUrl = "http://pictureulr.jpg";
        var productUrl1 = "http://pictureurl.png";
        var productIntroduction = "产品的介绍";
        var productDetail = "产品的详情";
        var productLang = 0;
        var productPrice = 12.45;
        var productSeries = 1;
        var product ={};
        product.name = productName;
        product.picurls = [];
        product.picurls.push(productUrl);
        product.picurls.push(productUrl1);
        product.introduction = productIntroduction;
        product.detail = productDetail;
        product.price = productPrice;
        product.lang = productLang;
        product.series = 1;

        // $.post("../product/add.do",product,function (rs) {
        //     if(rs.success){
        //         alert("success");
        //     }else {
        //         alert("fail");
        //     }
        // },'json')
        $.ajax({
            type: "POST",
            url: "../product/add.do",
            contentType: "application/json",
            dataType: "json",
            data: JSON.stringify(product),
            success: function (rs) {
                if(rs.success){
                    alert("product add success");
                }
            }
        })

    });


    //产品更新js
    $("#product_update").click(function () {
        var productName = "前端获取的产品名称②";
        var productUrl = "http://pictureulr1.jpg";
        var productUrl1 = "http://pictureurl1.png";
        var productIntroduction = "产品的介绍";
        var productDetail = "产品的详情";
        var productLang = 0;
        var productPrice = 12.45;
        var product ={};
        product.id = 8;
        if(productName){
            product.name = productName;
        }
        product.picurls = [];
        if(productUrl){
            product.picurls.push(productUrl);
        }
        if(productUrl1){
            product.picurls.push(productUrl1);
        }
        if(productIntroduction){
            product.introduction = productIntroduction;
        }
        if (productDetail){
            product.detail = productDetail;
        }
        if(productPrice){
            product.price = productPrice;
        }
        if(productLang){
            product.lang = productLang;
        }
        //
        // $.post("../product/update.do",{dto:product},function (rs) {
        //     if(rs.success){
        //         alert("success");
        //     }else {
        //         alert("fail");
        //     }
        // },'json')

        $.ajax({
            type: "POST",
            url: "../product/update.do",
            contentType: "application/json",
            dataType: "json",
            data: JSON.stringify(product),
            success: function (rs) {
                if(rs.success){
                    alert("product update success");
                }
            }
        })
    })

    //产品查询js
    $("#product_search").click(function () {

        var product ={};
        product.pageNum = 1;
        product.pageSize = 5;

        $.ajax({
            type: "POST",
            url: "../product/list",
            contentType: "application/json",
            dataType: "json",
            data: JSON.stringify(product),
            success: function (rs) {
                if(rs.success){
                    alert("product serach success");
                }
            }
        });

        $.ajax({
            type: "POST",
            url: "../english/product/list",
            contentType: "application/json",
            dataType: "json",
            data: JSON.stringify(product),
            success: function (rs) {
                if(rs.success){
                    alert("english product serach success");
                }
            }
        });
    });



    //资讯新增js
    $("#infomation_add").click(function () {
        var infomationTitle = "新闻标题";
        var infomationProfile = "新闻摘要";
        var infomationContent ="新闻正文";
        var infomationAuthor = "新闻作者";
        var infomationSource = "新闻来源";
        var infomationPicurl = "http://picurl.jpg";
        var infomationCategory = 1;
        var lang = 0;
        var publish = 1;
        var infomation = {};
        infomation.title = infomationTitle;
        infomation.author = infomationAuthor;
        infomation.categoryId =infomationCategory;
        infomation.introduction =infomationProfile;
        infomation.source = infomationSource;
        infomation.content = infomationContent;
        infomation.lang = lang;
        infomation.publish = publish;
        infomation.picurl = infomationPicurl;

        // $.post("../infomation/add.do",{dto:infomation},function (rs) {
        //     if(rs.success){
        //         alert("success");
        //     }else {
        //         alert("failed");
        //     }
        // },'json')
        $.ajax({
            type: "POST",
            url: "../infomation/add.do",
            contentType: "application/json",
            dataType: "json",
            data: JSON.stringify(infomation),
            success: function (rs) {
                if(rs.success){
                    alert("infomation add success");
                }
            }
        });

    })

    //资讯更新js
    $("#infomation_update").click(function () {
        var id = 3;
        var infomationTitle = "新闻标题";
        var infomationProfile = "新闻摘要";
        var infomationContent ="新闻正文";
        var infomationAuthor = "新闻作者";
        var infomationSource = "新闻来源";
        var infomationPicurl = "http://picurl.jpg";
        var infomationCategory = 1;
        var lang = 0;
        var publish = 1;
        var infomation = {};
        infomation.id = id;
        infomation.title = infomationTitle;
        infomation.author = infomationAuthor;
        infomation.categoryId =infomationCategory;
        infomation.introduction =infomationProfile;
        infomation.source = infomationSource;
        infomation.content = infomationContent;
        infomation.lang = lang;
        infomation.publish = publish;
        infomation.picurl = infomationPicurl;

        // $.post("../infomation/update.do",{dto:infomation},function (rs) {
        //     if(rs.success){
        //         alert("success");
        //     }else {
        //         alert("failed");
        //     }
        // },'json')

        $.ajax({
            type: "POST",
            url: "../infomation/update.do",
            contentType: "application/json",
            dataType: "json",
            data: JSON.stringify(infomation),
            success: function (rs) {
                if(rs.success){
                    alert("infomation update success");
                }
            }
        });
    });
    //资讯查询js
    $("#infomation_search").click(function () {
        var dto ={};
        dto.pageNum = 1;
        dto.pageSize = 5;
        // $.post("../infomation/list",{dto:dto},function (rs) {
        //     if(rs.success){
        //         alert("infomation search  success");
        //     }else {
        //         alert("infomation search  fail");
        //     }
        // })

        $.ajax({
            type: "POST",
            url: "../infomation/list",
            contentType: "application/json",
            dataType: "json",
            data: JSON.stringify(dto),
            success: function (rs) {
                if(rs.success){
                    alert("infomation search  success");
                }
            }
        });

        $.ajax({
            type: "POST",
            url: "../english/infomation/list",
            contentType: "application/json",
            dataType: "json",
            data: JSON.stringify(dto),
            success: function (rs) {
                if(rs.success){
                    alert("infomation search success");
                }
            }
        });
    });

    //留言新增js
    $("#message_add").click(function () {
        var name = "留言姓名";
        var telephone = "留言电话";
        var content = "留言正文";
        var email = "留言邮箱";
        var validCode ="QUSF";
        var message = {};
        message.name = name;
        message.telephone = telephone;
        message.message = content;
        message.email = email;
        message.validCode = validCode;

        // $.post("../infomation/addMessage",{message:message,validCode:validCode},function (rs) {
        //     if(rs.success){
        //         alert("message add success");
        //     }else{
        //         alert(rs.message);
        //     }
        // },'json')

        $.ajax({
            type: "POST",
            url: "../infomation/addMessage",
            contentType: "application/json",
            dataType: "json",
            data: JSON.stringify(message),
            success: function (rs) {
                if(rs.success){
                    alert("infomation search success");
                }
            }
        });

    })
    //留言查询js
    $("#message_search").click(function () {
        var message = {};
        message.pageNum = 1;
        message.pageSize = 5;
        $.ajax({
            type: "POST",
            url: "../backend/getMessages",
            contentType: "application/json",
            dataType: "json",
            data: JSON.stringify(message),
            success: function (rs) {
                if(rs.success){
                    alert("infomation search success");
                }
            }
        });


    })





});