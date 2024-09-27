document.addEventListener('DOMContentLoaded', function() {
    const button = document.getElementById('interest-button');

    button.addEventListener('click', function() {
        if (button.classList.contains('not-interested')) {
            button.classList.remove('not-interested');
            button.classList.add('interested');
            button.textContent = 'INTERESTED';
        } else {
            button.classList.remove('interested');
            button.classList.add('not-interested');
            button.textContent = 'NOT INTERESTED';
        }
    });
});

// Function to open the modal
       function openModal() {
           document.getElementById("callModal").style.display = "flex"; // Flex to center the content
       }

       // Function to close the modal
       function closeModal() {
           document.getElementById("callModal").style.display = "none";
       }

       // Function for Call button (can trigger additional actions)
       function makeCall() {
           alert("Calling...");
       }

       // Function for End Call button (can trigger additional actions)
       function endCall() {
           alert("Call Ended");
           closeModal(); // Close modal after call ends
       }

       // Close the modal when clicking outside of the modal content
       window.onclick = function(event) {
           var modal = document.getElementById("callModal");
           if (event.target == modal) {
               modal.style.display = "none";
           }
       }
	   
