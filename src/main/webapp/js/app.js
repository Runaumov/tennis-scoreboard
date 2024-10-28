// JavaScript for toggling the dropdown menu
document.addEventListener("DOMContentLoaded", function () {
    const navToggle = document.querySelector(".nav-toggle");
    const navLinks = document.querySelector(".nav-links");
    const resetButton = document.getElementById("reset-filter-btn");
    const filterInput = document.getElementById("filter-input");

    navToggle.addEventListener("click", function () {
        navLinks.classList.toggle("active");
    });

    resetButton.addEventListener("click", function () {
        filterInput.value = '';
    });
});