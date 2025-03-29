// Add Package Form Submission
document.getElementById('addPackageForm').addEventListener('submit', function(event) {
    event.preventDefault();

    const formData = new FormData();
    const packageData = {
        name: document.getElementById('packageName').value,
        description: document.getElementById('packageDescription').value,
        totalPrice: document.getElementById('packageTotalPrice').value,
        totalDurationMinutes: document.getElementById('packageDuration').value,
        validityDays: document.getElementById('packageValidity').value,
        imageURL: document.getElementById('packageImage').value // Temporary placeholder
    };
    formData.append('package', JSON.stringify(packageData));

    const packageImageFile = document.getElementById('packageImage');
    if (packageImageFile && packageImageFile.files.length > 0) {
        formData.append('file', packageImageFile.files[0]);
    } else {
        console.error('No image file selected');
        return;
    }

    fetch('http://localhost:8080/api/v1/package/save', {
        method: 'POST',
        headers: {
            "Authorization": "Bearer " + localStorage.getItem("jwt_token")
        },
        body: formData
    })
        .then(response => {
            if (!response.ok) {
                getAllPackages();
                return response.json().then(errorData => {
                    throw new Error(JSON.stringify(errorData));
                });
            }
            return response.json();
        })
        .then(data => {
            console.log('Package saved successfully:', data);
            getAllPackages();
            $('#addPackageModal').modal('hide');
        })
        .catch(error => {
            console.error('Error:', error);
            try {
                const errorData = JSON.parse(error.message);
                console.log('Response data:', errorData);
                if (errorData.data) {
                    for (const [field, message] of Object.entries(errorData.data)) {
                        // Optionally display field-specific errors
                    }
                }
            } catch (e) {
                console.error('Failed to parse error message:', e);
            }
        });
});

// Fetch and Display All Packages
const getAllPackages = () => {
    $.ajax({
        url: 'http://localhost:8080/api/v1/package/get-all',
        type: "GET",
        headers: {
            "Authorization": "Bearer " + localStorage.getItem("jwt_token")
        },
        success: (res) => {
            console.log(res);
            $('#packageTableBody').empty();

            res.data.forEach(pkg => {
                const imageUrl = pkg.imageURL ? `data:image/png;base64,${pkg.imageURL}` : 'assets/Images/noImage.png';
                $('#packageTableBody').append(`
          <tr>
            <td>${pkg.id}</td>
            <td>${pkg.name}</td>
            <td>${pkg.description}</td>
            <td>${pkg.totalPrice}</td>
            <td>${pkg.totalDurationMinutes}</td>
            <td>${pkg.validityDays}</td>
            <td><img src="${imageUrl}" alt="Package Image" style="width: 100px; height: auto;"></td>
            <td>${pkg.createdAt}</td>
            <td>${pkg.updatedAt}</td>
            <td>
              <button class="btn btn-light btn-sm" onclick="editPackage('${pkg.id}', '${pkg.name}', '${pkg.description}', '${pkg.totalPrice}', '${pkg.totalDurationMinutes}', '${pkg.validityDays}')">
                Update
              </button>
            </td>
            <td>
              <button class="btn btn-light btn-sm" onclick="deletePackage('${pkg.id}')">
                Delete
              </button>
            </td>
          </tr>
        `);
            });
        },
        error: (err) => {
            console.log(err);
        }
    });
};

// Load packages on page load
$(document).ready(function() {
    getAllPackages();
});

// Delete Package
const deletePackage = (id) => {
    $.ajax({
        url: `http://localhost:8080/api/v1/package/delete/${id}`,
        type: 'DELETE',
        headers: {
            "Authorization": "Bearer " + localStorage.getItem("jwt_token")
        },
        success: (res) => {
            console.log(res);
            getAllPackages();
        },
        error: (err) => {
            console.log(err);
        }
    });
};

// Edit Package (Populate Update Modal)
const editPackage = (id, name, description, totalPrice, totalDurationMinutes, validityDays) => {
    $('#updatedPackageId').val(id);
    $('#updatedPackageName').val(name);
    $('#updatedPackageDescription').val(description);
    $('#updatedPackageTotalPrice').val(totalPrice);
    $('#updatedPackageDuration').val(totalDurationMinutes);
    $('#updatedPackageValidity').val(validityDays);

    $('#updatePackageModal').modal('show');
};

// Update Package Form Submission
$('#updatePackageForm').submit(function(event) {
    event.preventDefault();

    const packageData = {
        id: $('#updatedPackageId').val(),
        name: $('#updatedPackageName').val(),
        description: $('#updatedPackageDescription').val(),
        totalPrice: $('#updatedPackageTotalPrice').val(),
        totalDurationMinutes: $('#updatedPackageDuration').val(),
        validityDays: $('#updatedPackageValidity').val()
    };

    const updatedImageFile = $('#updatedPackageImage')[0].files[0];
    if (updatedImageFile) {
        // If an image is provided, use the save endpoint to overwrite the package with new image
        const formData = new FormData();
        formData.append('package', JSON.stringify(packageData));
        formData.append('file', updatedImageFile);

        $.ajax({
            url: 'http://localhost:8080/api/v1/package/save',
            method: 'POST',
            headers: {
                "Authorization": "Bearer " + localStorage.getItem("jwt_token")
            },
            data: formData,
            processData: false,
            contentType: false,
            success: function(response) {
                console.log('Package updated successfully with new image:', response);
                getAllPackages();
                $('#updatePackageModal').modal('hide');
            },
            error: function(err) {
                console.error('Error updating package with image:', err);
            }
        });
    } else {
        // If no image is provided, use the update endpoint to update package details only
        $.ajax({
            url: 'http://localhost:8080/api/v1/package/update',
            method: 'PUT',
            headers: {
                "Authorization": "Bearer " + localStorage.getItem("jwt_token")
            },
            contentType: 'application/json',
            data: JSON.stringify(packageData),
            success: function(response) {
                console.log('Package updated successfully:', response);
                getAllPackages();
                $('#updatePackageModal').modal('hide');
            },
            error: function(err) {
                console.error('Error updating package:', err);
            }
        });
    }
});