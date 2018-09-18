if(window.localStorage.getItem('token')==null){
    window.location.href='login.html'
}

var vm = new Vue({
	el:'#page',
	data:{
		orderRecordList :{}
	},
	/*created: function(){
	    var token = window.localStorage.getItem('token');
	    $.ajax({
            url: api + 'orderRecord/my',
            type:'POST',
            dataType:'json',
            data:{
                token:token
            },
            success:function(res){
                console.log(res);
                if(res.code==0){
                    vm.orderRecordList = res.orderRecordList;
                }else{
                    console.log(res);
                }
            },
            error: function(res) {
                console.log(res);
            }
        });
    },*/
    methods: {
        setTab: function(name,cursel,n){
            for(i=1;i<=n;i++){
                  var menu=document.getElementById(name+i);
                  var con=document.getElementById("con_"+name+"_"+i);
                  menu.className=i==cursel?"hover":"";
                  con.style.display=i==cursel?"block":"none";
             }
        }
    }
});