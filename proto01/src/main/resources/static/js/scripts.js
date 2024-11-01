document.getElementById('login-form').addEventListener('submit', function(event) {
    event.preventDefault();

    var email = document.getElementById('email').value;
    var password = document.getElementById('password').value;

    var errorElement = document.getElementById('login-error');

    if (!email || !password) {
        errorElement.textContent = 'Please fill in all fields.';
    } else if (!validateEmail(email)) {
        errorElement.textContent = 'Invalid email address.';
    } else {
        errorElement.textContent = '';
        alert('Login successful');
    }
});

document.getElementById('signup-form').addEventListener('submit', function(event) {
    event.preventDefault();

    var fullname = document.getElementById('fullname').value;
    var email = document.getElementById('signup-email').value;
    var contact = document.getElementById('contact').value;
    var age = document.getElementById('age').value;
    var location = document.getElementById('location').value;
    var gender = document.getElementById('gender').value;

    var errorElement = document.getElementById('signup-error');

    if (!fullname || !email || !contact || !age || !location || !gender) {
        errorElement.textContent = 'Please fill in all fields.';
    } else if (!validateEmail(email)) {
        errorElement.textContent = 'Invalid email address.';
    } else {
        errorElement.textContent = '';
        alert('Sign Up successful');
    }
});

function validateEmail(email) {
    var re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return re.test(email);
}
