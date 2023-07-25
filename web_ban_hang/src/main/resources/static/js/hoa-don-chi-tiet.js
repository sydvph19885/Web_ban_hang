$(document).ready(function () {
    $(".modalSuccess").hide();
    $(".thanhToan").click(() => {
        $(".modalSuccess").slideDown();
        $("main").hide();
    })

})