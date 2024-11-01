document.querySelector('.report-incident-button').addEventListener('mousedown', function() {
    this.classList.add('active');
});

document.querySelector('.report-incident-button').addEventListener('mouseup', function() {
    this.classList.remove('active');
});

document.querySelector('.report-incident-button').addEventListener('mouseleave', function() {
    this.classList.remove('active');
});

function scrollToContact() {
    const contactSection = document.getElementById('contact');
    contactSection.scrollIntoView({ behavior: 'smooth' });
}

function scrollToAbout(){
    const aboutSection = document.getElementById('about-us');
    aboutSection.scrollIntoView( {behavior: 'smooth'});
}

let currentSlide = 0;

const slides = document.querySelectorAll('.slide');

// Function to show the current slide
function showSlide(index) {
  slides.forEach((slide, i) => {
    if (i === index) {
      slide.classList.add('active');
    } else {
      slide.classList.remove('active');
    }
  });
}

// Function to change the slide based on arrow click
function changeSlide(direction) {
  currentSlide += direction;

  // Loop back to the first slide if the index exceeds the total slides
  if (currentSlide >= slides.length) {
    currentSlide = 0;
  } else if (currentSlide < 0) {
    currentSlide = slides.length - 1;
  }
  
  showSlide(currentSlide);
}

function openMap(){
    window.location.href= '/map'
}



