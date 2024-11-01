document.addEventListener('DOMContentLoaded', () => {
    const form = document.querySelector('form');
    const themeToggle = document.getElementById('theme-toggle');

    // Load saved theme from local storage
    const savedTheme = localStorage.getItem('theme');
    if (savedTheme) {
        document.body.classList.toggle('dark-theme', savedTheme === 'dark');
    }

    // Theme toggle event listener
    themeToggle.addEventListener('change', () => {
        const isDarkTheme = themeToggle.checked;
        document.body.classList.toggle('dark-theme', isDarkTheme);
        localStorage.setItem('theme', isDarkTheme ? 'dark' : 'light');
    });

    form.addEventListener('submit', (event) => {
        const newPassword = document.getElementById('new-password').value;
        const confirmPassword = document.getElementById('confirm-password').value;

        if (newPassword !== confirmPassword) {
            event.preventDefault(); // Prevent form submission
            alert('Passwords do not match. Please try again.');
        }
    });
});
