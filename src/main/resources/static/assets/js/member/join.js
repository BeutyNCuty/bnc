$(document).ready(function(){
    $('#joinBtn').click(function (){
        $.ajax({
            url: '/joinMember',
            type: 'post',
            dataType : 'json',
            contentType: 'application/json;charset=utf-8',
            data: JSON.stringify( {
                "userId" : $('#userId').val(),
                "password": $('#password').val(),
                "phone": $('#phone').val(),
                "addr": $('#sample2_address').val() + "!" + $('#sample2_detailAddress').val()
            }),
            success : function(value){
                var userid =  $('#userId').val();
                alert(userid+ "님 회원가입을 경하드리옵니다");
                location.href = '/login';
            }
        });
    });
});
