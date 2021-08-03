
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










// let img = document.querySelector('img');
// let btn1 = document.querySelector('#btn1');
// let btn2 = document.querySelector('#btn2');
// let btn3 = document.querySelector('#btn3');
// let btn4 = document.querySelector('#btn4');
// let btn5 = document.querySelector('#btn5');


// btn1.addEventListener('click', Addimage());

// btn2.addEventListener('click', () => {
//   img.src = './img/img2.jpg';
// })

// btn3.addEventListener('click', () => {
//   img.src = './img/img3.jpg';
// })

// btn4.addEventListener('click', () => {
//   img.src = './img/img4.jpg';
// })

// btn5.addEventListener('click', () => {
//   img.src = './img/img5.jpg';
// })




//Dynamic

  // var image = document.createElement('img');
  // image.src = 'img/img1.jpg';
  
  // document.getElementById('slide-container').appendChild(image);



