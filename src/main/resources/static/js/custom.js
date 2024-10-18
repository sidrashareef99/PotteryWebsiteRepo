document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('imageSelectionForm');
    const imageOptions = document.querySelectorAll('.image-option');
    const selectedImageInput = document.getElementById('selectedImage');

    imageOptions.forEach(option => {
        option.addEventListener('click', function() {
            imageOptions.forEach(opt => opt.classList.remove('selected'));
            this.classList.add('selected');
            selectedImageInput.value = this.dataset.value;
        });
    });

    form.addEventListener('submit', function(e) {
        e.preventDefault();
        if (selectedImageInput.value) {
            alert('Selected image: ' + selectedImageInput.value);
            // Here you can send the form data to a server
        } else {
            alert('Please select an image');
        }
    });
});