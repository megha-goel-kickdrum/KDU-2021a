
let flag = 0;


function controller(x)
{
  flag= flag + x;  
  slideshow(flag);
}

slideshow(flag);

function slideshow(num)
{
    let slides = document.getElementsByClassName('slides');

    if(num == slides.length)
    {
      flag = 0;
      num = 0;

    }

    if(num < 0)
    {
      flag = slides.length-1;
      num = slides.length-1;
    }

    for(let y of slides)
    {
      y.style.display = "none";
    }

    slides[num].style.display = "block";
}





// var slideIndex = 1;
// showSlides(slideIndex);

// // Next/previous controls
// function plusSlides(n) {
//   showSlides(slideIndex += n);
// }

// // Thumbnail image controls
// function currentSlide(n) {
//   showSlides(slideIndex = n);
// }

// function showSlides(n) {
//   var i;
//   var slides = document.getElementsByClassName("mySlides");
//   var dots = document.getElementsByClassName("dot");
//   if (n > slides.length) {slideIndex = 1}
//   if (n < 1) {slideIndex = slides.length}
//   for (i = 0; i < slides.length; i++) {
//       slides[i].style.display = "none";
//   }
//   for (i = 0; i < dots.length; i++) {
//       dots[i].className = dots[i].className.replace(" active", "");
//   }
//   slides[slideIndex-1].style.display = "block";
//   dots[slideIndex-1].className += " active";
// }