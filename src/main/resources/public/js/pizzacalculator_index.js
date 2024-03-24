document.getElementById("quantity").addEventListener("input", function() {
    document.getElementById("quantityValue").innerHTML = document.getElementById("quantity").value
});

document.getElementById("weight").addEventListener("input", function() {
    document.getElementById("weightValue").innerHTML = document.getElementById("weight").value
});

document.getElementById("hydration").addEventListener("input", function() {
    document.getElementById("hydrationValue").innerHTML = document.getElementById("hydration").value
});

document.getElementById("temperature").addEventListener("input", function() {
    document.getElementById("temperatureValue").innerHTML = document.getElementById("temperature").value
});

function confirmSubmission(formId) {
    console.log("confirmSubmission called")
    var confirmAction = confirm("Are you sure you want to delete?");
    if (confirmAction) {
        // If the user clicks OK, submit the form
        document.getElementById(formId).submit();
    } else {
        // If the user clicks Cancel, do nothing
        console.log("Deletion cancelled.");
    }
}