var danhSachSP = [];
$(document).ready(function () {
    $.ajax({
        url: "http://localhost:8081/admin/top-10",
        method: "GET",
        dataType: "json",
        contentType: "application/json",
        success: function (data) {
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
        labels: danhSachSP.map(sp => sp.chiTietSP.tenSanPham), // Tên sản phẩm
        datasets: [
            {
                label: "Số lượng",
                data: danhSachSP.map(sp => sp.soLuongBan), // Số lượng sản phẩm
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


function search() {
    var keyword = document.getElementById("searchInput").value;
    var filteredData = danhSachSP.filter(sp => sp.chiTietSP.tenSanPham.toLowerCase().includes(keyword.toLowerCase()));
    chart.data.labels = filteredData.map(sp => sp.chiTietSP.tenSanPham);
    chart.data.datasets[0].data = filteredData.map(sp => sp.soLuongBan);
    chart.update();
}