
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

function dotEvent(i) {
  let slides = document.getElementsByClassName('slides');
   for(let y of slides)
   {
     y.style.display = "none";
   }

  slides[i].style.display = "block";

}



