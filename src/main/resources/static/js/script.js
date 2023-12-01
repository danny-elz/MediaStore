function verify() {
    var password1 = document.forms['form']['password'].value;
    var password2 = document.forms['form']['verifyPassword'].value;
    if (password1 == null || password1 == "" || password1 != password2) {
        document.getElementById("error").innerHTML = "Please check your passwords";
        return false;
    }
}
// JavaScript to control the modal
/*function closeModal() {
    document.getElementById("addToCartModal").style.display = "none";
}
// Assuming this function is called when an item is added to the cart
function openModal() {
    document.getElementById("addToCartModal").style.display = "block";
}
// Close the modal when the user clicks anywhere outside of it
window.onclick = function(event) {
    var modal = document.getElementById("addToCartModal");
    if (event.target === modal) {
        closeModal();
    }
}

 */

window.onload = function() {
    var itemAdded = /*[[${itemAdded}]]*/ false;
    if (itemAdded) {
        openModal();
    }
}
function openModal() {
    document.getElementById("addToCartModal").style.display = "block";
}

function closeModal() {
    document.getElementById("addToCartModal").style.display = "none";
}
