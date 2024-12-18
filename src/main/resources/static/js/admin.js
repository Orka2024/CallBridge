document.addEventListener("DOMContentLoaded", function () {
  document
    .querySelector("#cInputFileImage")
    .addEventListener("change", function (event) {
      let file = event.target.files[0];
      let reader = new FileReader();
      reader.onload = function () {
        const previewImage = document.querySelector("#cUploadedImagePreview");
        const imageContainer = document.querySelector(".cimage-container");
        
        previewImage.setAttribute("src", reader.result);
        imageContainer.style.display = "block"; // Show the container when an image is selected
      };
      reader.readAsDataURL(file);
    });
});


document
    .querySelector("#cibilupload_file_input")
    .addEventListener("change", function (event) {
      let file = event.target.files[0];
      let reader = new FileReader();
      reader.onload = function () {
        document
		.querySelector("#uploadimage_preview")
		.setAttribute("src", reader.result);
       
      };
      reader.readAsDataURL(file);
    });