// Sends a request to the back-end to delete the specified expense.
function deleteExpense(button) {

    const expenseId = button.getAttribute("data-id");

    fetch("/api/expenses/delete/" + expenseId, {
        method: "DELETE"
    }).then(response => {

        if (response.ok) {

            fadeOut(button.closest(".alert"))

        } else {

            alert("Failed to delete expense with ID: " + expenseId);

        }

    })

}

function fadeOut(element) {

    let opacity = 1;
    const interval = setInterval(() => {

        if (opacity > 0) {

            opacity -= 0.05;
            element.style.opacity = opacity;

        } else {

            clearInterval(interval);
            element.remove();

        }

    }, 25);

}