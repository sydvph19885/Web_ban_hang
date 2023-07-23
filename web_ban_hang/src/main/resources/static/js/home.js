$(document).ready(() => {
  $(".thongTinLoc").hide();
  $(".loc").click(() => {
    $(".thongTinLoc").slideToggle();
    $(".banners").css("height", "450px");
  });
});
