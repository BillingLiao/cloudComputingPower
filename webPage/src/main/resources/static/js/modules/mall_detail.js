function getUrlParam(name){
    var obj = {};
    var url_0=window.location.href;
    var url;
    if(url_0.indexOf('?')!=-1){
        url = url_0.split('?')[1].split('&');
    }else{
        return false
    }
    var nameList = [];
    url.forEach(function(item) {
        let tempArr = item.split('=');
        let key = decodeURIComponent(tempArr[0]);
        let val = decodeURIComponent(tempArr[1]);
        obj[key] = val;
        nameList.push(key);
    })
    if (nameList.indexOf(name) == -1) {
        return false
    } else {
        return obj[name]
    }
}
var vm = new Vue({
	el:'#page',
	data:{
		cloudProduct :{},
		amount: null,
		actualReceipts:null
	},
	watch: {
        amount: function(newAmount, oldAmount){
            this.debouncedGetActualReceipts();
        }
     },
	created: function(){
       var productId = getUrlParam('productId');
        $.get(api + 'cloud/findOne/'+productId, function(r){
            console.log(r);
            vm.cloudProduct = r.cloudProduct;
        });
        this.debouncedGetActualReceipts = _.debounce(this.getActualReceipts, 500)
    },
    methods:{
        getActualReceipts: function(){
            $.ajax({
                url: api + 'order/actualReceipts',
                type:'POST',
                dataType:'json',
                data:{
                    amount: vm.amount,
                    price: vm.cloudProduct.price
                },
                success:function(res){
                    console.log(res);
                    if(res.code==0){
                        vm.actualReceipts = res.actualReceipts;
                    }else{
                        console.log(res);
                    }
                },
                error: function(res) {
                    console.log(res);
                }
            });
        },
        goToPay: function(){
            var token = window.localStorage.getItem('token');
            var productId = getUrlParam('productId');
            if(token == null){
                alert('请先登录再购买');
                window.location.href='login.html'
            }else{
                var productId = getUrlParam('productId');
                $.ajax({
                    url: api + 'order/add',
                    type:'POST',
                    dataType:'json',
                    data:{
                        token: token,
                        productId:productId,
                        actualReceipts:vm.actualReceipts,
                        amount:vm.amount
                    },
                    success:function(res){
                        if(res.code==0){
                            var orderId = res.order.orderId;
                            window.location.href='pay.html?orderId='+orderId;
                        }else{
                            console.log(res);
                        }
                    },
                    error: function(res) {
                        console.log(res);
                    }
                });
            }

        }
    }

});