/*document.addEventListener('DOMContentLoaded', function() {
    const button = document.getElementById('interest-button');

    // Get the value from the data-th-value attribute
    let cInterest = button.getAttribute('data-th-value') === 'true';

    // Check the value of cInterest and update button properties accordingly
    if (cInterest) {
        button.textContent = 'INTERESTED';
        button.className = 'interested'; // Change class to reflect interested state
    } else {
        button.textContent = 'NOT INTERESTED';
        button.className = 'not-interested'; // Class for not interested state
    }
});
*/

// Function to open the modal
function openModal(cName) {
	document.getElementById("modalName").innerText = cName; // Update the name in the modal
    document.getElementById("callModal").style.display = "flex"; // Flex to center the content
}

function closeModal() {
    document.getElementById("callModal").style.display = "none";
}

function makeCall() {
    alert("Calling...");
}

function endCall() {
    alert("Call Ended");
    closeModal(); // Close modal after call ends
}

window.onclick = function(event) {
    var modal = document.getElementById("callModal");
    if (event.target == modal) {
        closeModal();
    }
}

/* email start */

function showSuccessToast() {
  const toastContainer = document.getElementById('toast-container');
  const toast = document.createElement('div');
  toast.classList.add('toast');

  toast.innerHTML = `
    <span class="icon">✔️</span>
    <span class="message">Success! Your action was completed.</span>
  `;

  toastContainer.appendChild(toast);

  setTimeout(() => {
    toast.remove();
  }, 4000);
}


/* email end  */


/* Delete Contact */  



function deleterecord(id){
	
	Swal.fire({
	  title: "Do you want to delete the Record?",
/*      icon: "warning",
*/	  showCancelButton: true,
	  confirmButtonText: "Delete",
	}).then((result) => {
	  /* Read more about isConfirmed, isDenied below */
	  if (result.isConfirmed) {
             const url = "http://localhost:8282/user/delete/" +id;
			 window.location.replace(url);
		  }
	  
	  
	});
	
}







/* Delete end */








