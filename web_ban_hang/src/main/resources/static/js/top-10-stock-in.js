var danhSachSP = [];
var ngayHienTai = new Date();
$(document).ready(function () {
    $.ajax({
        url: "http://localhost:8081/admin/top-stock-in",
        method: "GET",
        dataType: "json",
        contentType: "application/json",
        success: function (data) {
            data.forEach((element) => {
                console.log(Math.ceil((Math.abs(ngayHienTai - new Date(element.ngayNhap))) / (24 * 60 * 60 * 1000)))
            })
            danhSachSP = data;
            createChart();
        },
        error: function (error) {
            danhSachSP = null;
            console.log(error);
        }
    })
    $.ajax({
        url: "http://localhost:8081/admin/tong-tien-aday",
        method: "GET",
        dataType: "json",
        contentType: "application/json",
        success: function (data) {
            $("#totalToday").text(data.toLocaleString('vi-VN', {style: 'currency', currency: 'VND'}));
        },
        error: function (error) {
            $("#totalToday").text("0");
            console.log(error);
        }
    })
});


function createChart() {
    var data = {
        labels: danhSachSP.map(sp => sp.tenSanPham), // Tên sản phẩm
        datasets: [
            {
                label: "Số ngày tồn",
                data: danhSachSP.map(sp => Math.ceil((Math.abs(ngayHienTai - new Date(sp.ngayNhap))) / (24 * 60 * 60 * 1000))), // Số lượng sản phẩm
                backgroundColor: "rgba(75, 192, 192, 0.2)",
                borderColor: "rgba(75, 192, 192, 1)",
                borderWidth: 1,
            },
        ],
    };

    var ctx = document.getElementById("chart").getContext("2d");
    var chart = new Chart(ctx, {
        type: "bar",
        data: data,
        options: {
            responsive: true,
            scales: {
                y: {
                    beginAtZero: true,
                },
            },
        },
    });
}


