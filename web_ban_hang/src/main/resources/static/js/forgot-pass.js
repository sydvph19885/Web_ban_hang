$(document).ready(function () {
    $(".senMail").click(function () {
        var email = $("#typeEmail").val();
        $.ajax({
            url: "http://localhost:8081/user/check-mail/" + email,
            dataType: "json",
            method: "GET",
            contentType: "application/json",
            success: function (response) {
                $(".loader").show();
                $(".formSend").hide();
                $.ajax({
                    url: "http://localhost:8081/user/send-mail",
                    dataType: "json",
                    contentType: "application/json",
                    method: "POST",
                    data: JSON.stringify({
                        id: response.id,
                        username: response.username,
                        email: response.email,
                        image: response.image,
                        matKhau: response.matKhau,
                        role: response.role,
                        sdt: response.sdt,
                        ma: response.ma,
                    }),
                    success: function (data) {
                        sessionStorage.setItem('account', JSON.stringify(data));
                        window.location.href = 'http://localhost:8081/user/xac-thuc'
                    },
                    error: function (error) {
                        console.log(error);
                        alert("Gửi mail lỗi!")
                    }
                })
            },
            error: function (error) {
                console.log(error);
                alert("Không tìm thấy mail!");
            }
        })
    })
    $(".loader").hide();
})