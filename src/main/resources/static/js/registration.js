// Displays an alert if the password and confirm password fields do not match.
document.addEventListener("DOMContentLoaded", function () {

    const registrationForm = document.getElementById("registrationForm");
    const passwordField = document.getElementById("password");
    const confirmPasswordField = document.getElementById("confirmPassword");
    const errorAlert = document.getElementById("errorAlert");

    registrationForm.addEventListener("submit", function (event) {

        if (passwordField.value !== confirmPasswordField.value) {

            event.preventDefault();
            errorAlert.style.display = "block";

            setTimeout(function() {

                errorAlert.style.display = "none";

            }, 4000);

        } else {

            errorAlert.style.display = "none";

        }

    });

});