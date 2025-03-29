
// Add Salon Service Form Submission
document.getElementById('addSalonServiceForm').addEventListener('submit', function(event) {
    event.preventDefault();

    const formData = new FormData();
    const service = {
        name: document.getElementById('serviceName').value,
        description: document.getElementById('serviceDescription').value,
        price: document.getElementById('servicePrice').value,
        durationMinutes: document.getElementById('serviceDuration').value,
        imageURL: document.getElementById('serviceImage').value // Temporary placeholder
    };
    formData.append('service', JSON.stringify(service));

    const serviceImageFile = document.getElementById('serviceImage');
    if (serviceImageFile && serviceImageFile.files.length > 0) {
        formData.append('file', serviceImageFile.files[0]);
    } else {
        console.error('No image file selected');
        return;
    }

    fetch('http://localhost:8080/api/v1/salon-service/save', {
        method: 'POST',
        headers: {
            "Authorization": "Bearer " + localStorage.getItem("jwt_token")
        },
        body: formData
    })
        .then(response => {
            if (!response.ok) {
                getAllSalonServices();
                return response.json().then(errorData => {
                    throw new Error(JSON.stringify(errorData));
                });
            }
            return response.json();
        })
        .then(data => {
            console.log('Salon service saved successfully:', data);
            getAllSalonServices();
            $('#addSalonServiceModal').modal('hide');
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

// Fetch and Display All Salon Services
const getAllSalonServices = () => {
    $.ajax({
        url: 'http://localhost:8080/api/v1/salon-service/get-all',
        type: "GET",
        headers: {
            "Authorization": "Bearer " + localStorage.getItem("jwt_token")
        },
        success: (res) => {
            console.log(res);
            $('#salonServiceTableBody').empty();

            res.data.forEach(service => {
                const imageUrl = service.imageURL ? `data:image/png;base64,${service.imageURL}` : 'assets/Images/noImage.png';
                $('#salonServiceTableBody').append(`
          <tr>
            <td>${service.id}</td>
            <td>${service.name}</td>
            <td>${service.description}</td>
            <td>${service.price}</td>
            <td>${service.durationMinutes}</td>
            <td><img src="${imageUrl}" alt="Service Image" style="width: 100px; height: auto;"></td>
            <td>${service.createdAt}</td>
            <td>
              <button class="btn btn-light btn-sm" onclick="editSalonService('${service.id}', '${service.name}', '${service.description}', '${service.price}', '${service.durationMinutes}')">
                Update
              </button>
            </td>
            <td>
              <button class="btn btn-light btn-sm" onclick="deleteSalonService('${service.id}')">
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

// Load services on page load
$(document).ready(function() {
    getAllSalonServices();
});

// Delete Salon Service
const deleteSalonService = (id) => {
    $.ajax({
        url: `http://localhost:8080/api/v1/salon-service/delete/${id}`,
        type: 'DELETE',
        headers: {
            "Authorization": "Bearer " + localStorage.getItem("jwt_token")
        },
        success: (res) => {
            console.log(res);
            getAllSalonServices();
        },
        error: (err) => {
            console.log(err);
        }
    });
};

// Edit Salon Service (Populate Update Modal)
const editSalonService = (id, name, description, price, durationMinutes) => {
    $('#updatedServiceId').val(id);
    $('#updatedServiceName').val(name);
    $('#updatedServiceDescription').val(description);
    $('#updatedServicePrice').val(price);
    $('#updatedServiceDuration').val(durationMinutes);

    $('#updateSalonServiceModal').modal('show');
};

// Update Salon Service Form Submission
// Update Salon Service Form Submission - IMPROVED VERSION
$('#updateSalonServiceForm').submit(function(event) {
    event.preventDefault();

    const serviceData = {
        id: $('#updatedServiceId').val(),
        name: $('#updatedServiceName').val(),
        description: $('#updatedServiceDescription').val(),
        price: $('#updatedServicePrice').val(),
        durationMinutes: $('#updatedServiceDuration').val()
    };

    const updatedImageFile = $('#updatedServiceImage')[0].files[0];

    if (updatedImageFile) {
        // With image - use save endpoint
        const formData = new FormData();
        formData.append('service', JSON.stringify(serviceData));
        formData.append('file', updatedImageFile);

        $.ajax({
            url: 'http://localhost:8080/api/v1/salon-service/save',
            method: 'POST',
            headers: {
                "Authorization": "Bearer " + localStorage.getItem("jwt_token")
            },
            data: formData,
            processData: false,
            contentType: false,
            success: function(response) {
                console.log('Service updated successfully with new image:', response);
                getAllSalonServices();
                $('#updateSalonServiceModal').modal('hide');
            },
            error: function(err) {
                console.error('Error updating service with image:', err);
            }
        });
    } else {
        // Without image - use update endpoint
        $.ajax({
            url: 'http://localhost:8080/api/v1/salon-service/update',
            method: 'PUT',
            headers: {
                "Authorization": "Bearer " + localStorage.getItem("jwt_token")
            },
            contentType: 'application/json',
            data: JSON.stringify(serviceData),
            success: function(response) {
                console.log('Service updated successfully:', response);
                getAllSalonServices();
                $('#updateSalonServiceModal').modal('hide');
            },
            error: function(err) {
                console.error('Error updating service:', err);
            }
        });
    }
});