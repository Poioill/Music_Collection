$(document).ready(function(){
    $('.content__slider').slick({
        slidesToShow: 1,
        dots: true,
        arrows: false,
        customPaging: function(slider, i){
            if (i == 0){
                return 'Songs';
            }else if (i == 1){
                return 'Albums';
            }
        }
    });
    var block1 = document.querySelector(".picture__album-info-1");
    var block2 = document.querySelector(".picture__album-info-2");
    var blocks = document.querySelectorAll('.album-info__');
    var songItem = document.querySelectorAll('.slick-dots li')[0];
    var albumItem = document.querySelectorAll('.slick-dots li')[1];
    for (var i = 0; i < blocks.length; i++) {
        blocks[i].style.display = 'none';
    }
    songItem.addEventListener("click", function() {
        block1.style.display = "block";
        block2.style.display = "none";

    });
    albumItem.addEventListener("click", function() {
        block1.style.display = "none";
        block2.style.display = "block";
        for (var i = 0; i < blocks.length; i++) {
            blocks[0].style.display = 'block';
        }
    });
});
function showAlbumInfo(albumNumber){

    var contents = document.getElementsByClassName('album-info');
    var blocks = document.getElementsByClassName('album__card');

    for (var i = 0; i < contents.length; i++) {
        contents[i].style.display = 'none';
    }

    document.getElementById('album-info-' + albumNumber).style.display = 'block';

    for (var i = 0; i < blocks.length; i++) {
        blocks[i].classList.remove('active');
    }

    blocks[albumNumber - 1].classList.add('active');


}