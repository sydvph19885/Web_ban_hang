$(document).ready(function () {
    $(".formThongBao").hide();
    $(".formUpdate").hide();
    $(".deleteGH").click(function () {
        var id = $(this).data("id");
        $.ajax({
            url: "http://localhost:8081/user/cart/delete/" + id,
            method: "DELETE",
            success: function () {
                $(".formThongBao").toggle(10000);
                location.reload();
            },
            error: function (error) {
                console.log(error);
            }
        })
    })
    $(".updateCart").click(function () {
        console.log(document.getElementById("form1").value);
        var id = $(this).data("id");
        $.ajax({
            url: "http://localhost:8081/user/cart-detail/" + id,
            method: "GET",
            success: function (response) {
                update(response);
                location.reload();
            },
            error: function (error) {
                console.log(error);
            }
        })
    })
})

function update(response) {
    $.ajax({
        url: "http://localhost:8081/user/cart/update",
        dataType: "json",
        method: "POST",
        contentType: "application/json",
        data: JSON.stringify({
            id: response.id,
            chiTietSP: response.chiTietSP,
            donGia: response.donGia,
            gioHang: response.gioHang,
            soLuong: $(".valueSoLuong").val(),
        }),
        success: function () {
            $(".formUpdate").toggle(6000);
            window.location.href = 'http://localhost:8081/user/cart';
            location.reload();
        },
        error: function (error) {
            console.log(error);
        }
    })
}

