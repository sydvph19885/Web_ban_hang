$(document).ready(function () {
    $(".laoders").hide();
    $(".xacThuc").click(function () {
        $(".laoders").toggle();
        $(".optForm").hide();
        var valueSession = sessionStorage.getItem("account");
        var account = JSON.parse(valueSession);
        var first = $("#first").val();
        var second = $("#second").val();
        var third = $("#third").val();
        var fourth = $("#fourth").val();
        var fifth = $("#fifth").val();
        var sixth = $("#sixth").val();
        var code = first + second + third + fourth + fifth + sixth;
        $.ajax({
            url: "http://localhost:8081/user/xac-thuc-otp/" + code,
            dataType: "json",
            method: "GET",
            success: function () {
                alert("Mật khẩu mới đã được gửi về mail!");
                window.location.href = 'http://localhost:8081/user/login-form';
                location.reload();
            },
            error: function () {
                $(".optForm").show();
                $(".laoders").toggle();
                alert("Code không đúng!")

            }
        })

    })
})