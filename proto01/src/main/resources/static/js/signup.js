document.addEventListener('DOMContentLoaded', () => {
    const signUpForm = document.getElementById('signUpForm');

    signUpForm.addEventListener('submit', (e) => {
        e.preventDefault();
        const fullName = document.getElementById('fullName').value;
        const emailSignUp = document.getElementById('emailSignUp').value;
        const contact = document.getElementById('contact').value;
        const location = document.getElementById('location').value;
        const age = document.getElementById('age').value;
        const gender = document.getElementById('gender').value;
        const signUpPassword = document.getElementById('signUpPassword').value;

        // Reset error messages
        document.getElementById('fullNameError').textContent = '';
        document.getElementById('emailSignUpError').textContent = '';
        document.getElementById('contactError').textContent = '';
        document.getElementById('locationError').textContent = '';
        document.getElementById('ageError').textContent = '';
        document.getElementById('genderError').textContent = '';
        document.getElementById('signUpPasswordError').textContent = '';

        let isValid = true;

        if (!fullName) {
            document.getElementById('fullNameError').textContent = 'Full Name is required';
            isValid = false;
        }
        if (!emailSignUp || !validateEmail(emailSignUp)) {
            document.getElementById('emailSignUpError').textContent = 'Invalid email address';
            isValid = false;
        }
        if (!contact) {
            document.getElementById('contactError').textContent = 'Contact number is required';
            isValid = false;
        }
        if (!location) {
            document.getElementById('locationError').textContent = 'Location is required';
            isValid = false;
        }
        if (!age || age < 1) 
            document.get
