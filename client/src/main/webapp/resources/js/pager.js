(function() {

    //定义一个字符串缓存类
    function StringBuilder() {
        this.arr = new Array(); //该数组用来缓存需要拼接的HTML字符串
    }

    //该方法用于把参数内容添加到字符串缓存类的数组中
    StringBuilder.prototype.append = function(html) {
        this.arr.push(html);
    }

    //该方法用于把数组中的每一个元素通过指定字符拼接起来。
    StringBuilder.prototype.toString = function(html) {
        return this.arr.join('');
    }

    //创建分页对象
    window.gec = {
        init : function(eleId, pageModel) { //初始化分页属性的方法
            this.eleId = eleId;
            this.curPage = pageModel.pageIndex;
            this.pageSize = pageModel.pageSize;
            this.total = pageModel.recordCount;
            this.totalPage = pageModel.totalPage;
            this.submitUrl = pageModel.submitUrl;
        },
        pager : function(eleId, pageModel) { //构建分页对象，第一个参数代表分页栏的id属性，第二个参数是一个保存了分页信息的json对象
            this.init(eleId, pageModel);
            var hb = new StringBuilder(); //创建字符串缓存对象
            //如果当前是第一页
            if (this.curPage == 1) {
                hb.append('【首页】【上一页】');
                var tempUrl = this.submitUrl.replace('{0}', this.curPage + 1);
                hb.append('【<a href="' + tempUrl + '">下一页</a>】');
                tempUrl = this.submitUrl.replace('{0}', this.totalPage);
                hb.append('【<a href="' + tempUrl + '">尾页</a>】');
            } else if (this.curPage == this.totalPage) {
                var tempUrl = this.submitUrl.replace('{0}', 1);
                hb.append('【<a href="' + tempUrl + '">首页</a>】');
                var tempUrl = this.submitUrl.replace('{0}',  this.curPage - 1);
                hb.append('【<a href="' + tempUrl + '">上一页</a>】');
                hb.append('【下一页】【尾页】');
            } else {
                var tempUrl = this.submitUrl.replace('{0}', 1);
                hb.append('【<a href="' + tempUrl + '">首页</a>】');
                tempUrl = this.submitUrl.replace('{0}',  this.curPage - 1);
                hb.append('【<a href="' + tempUrl + '">上一页</a>】');
                tempUrl = this.submitUrl.replace('{0}', this.curPage + 1);
                hb.append('【<a href="' + tempUrl + '">下一页</a>】');
                tempUrl = this.submitUrl.replace('{0}', this.totalPage);
                hb.append('【<a href="' + tempUrl + '">尾页</a>】');
            }
            hb.append("当前显示第" + this.curPage + "/" + this.totalPage + "页");
            hb.append("跳到<input type='text' id='cur_page' size=2/>页 <input type='button' id='save_btn' value='确定'/>");

            //把字符串缓存对象中的内容输出到页面上
            document.getElementById("pageBar").innerHTML = hb.toString();


            var submitUrl = this.submitUrl;
            var totalPage = this.totalPage;
            document.getElementById("save_btn").onclick = function() {
                var cur_page = document.getElementById("cur_page").value; //获取页码
                if (!/^\d+$/.test(cur_page) || cur_page < 1 || cur_page > totalPage) {
                    alert('无效页码');
                    return;
                }
                var tempUrl = submitUrl.replace('{0}', cur_page);
                location = tempUrl;
            };


        }
    };


}());

