// Load salon services when the DOM is fully loaded
// Load salon services when the DOM is fully loaded
document.addEventListener('DOMContentLoaded', function() {
    // Add this style creation code:
    const styleSheet = document.createElement('style');
    styleSheet.textContent = `
       .card {
        border: none;
        border-radius: 15px;
        overflow: hidden;
        transition: transform 0.3s ease, box-shadow 0.3s ease;
        box-shadow: 0 4px 12px rgba(0,0,0,0.1);
        margin-bottom: 2rem;
        background: #ffffff;
    }
    .card:hover {
        transform: translateY(-8px);
        box-shadow: 0 12px 24px rgba(0,0,0,0.15);
    }
    .card-img-top {
        height: 200px;
        object-fit: cover;
        border-bottom: 2px solid #f5f5f5;
    }
    .card-body {
        padding: 1.5rem;
        text-align: left;
    }
    .card-title {
        font-weight: 700;
        color: #2c3e50;
        margin-bottom: 0.5rem;
        font-size: 1.25rem;
        text-transform: capitalize;
        line-height: 1.3;
    }
    /* Description */
    .card-body p.card-title:nth-of-type(1) {
        font-size: 0.95rem;
        font-weight: 400;
        color: #7f8c8d;
        margin-bottom: 0.75rem;
        line-height: 1.5;
    }
    /* Price */
    .card-body p.card-title:nth-of-type(3) {
        font-size: 1.5rem;
        font-weight: 700;
        color: #e91e63;
        margin-bottom: 0.5rem;
    }
    /* Duration */
    .card-body p.card-title:nth-of-type(2) {
        color: #95a5a6;
        font-size: 0.9rem;
        font-weight: 500;
        margin-bottom: 1rem;
    }
    .btn-primary {
        background: linear-gradient(135deg, #ff6f61 0%, #d81b60 100%);
        border: none;
        padding: 10px 24px;
        font-weight: 600;
        font-size: 0.95rem;
        border-radius: 25px;
        text-transform: uppercase;
        letter-spacing: 0.5px;
        box-shadow: 0 4px 8px rgba(216, 27, 96, 0.2);
        transition: all 0.3s ease;
    }
    .btn-primary:hover {
        transform: translateY(-3px);
        box-shadow: 0 6px 12px rgba(216, 27, 96, 0.3);
        background: linear-gradient(135deg, #ff8a80 0%, #f06292 100%);
    }
    `;
    document.head.appendChild(styleSheet);

    fetchSalonServices();
});

// Fetch all salon services from the API
function fetchSalonServices() {
    fetch('http://localhost:8080/api/v1/salon-service/get-all', {
        headers: {
            'Authorization': 'Bearer ' + localStorage.getItem('jwt_token')
        }
    })
        .then(response => response.json())
        .then(data => {
            const serviceContainer = document.getElementById('serviceContainer');
            serviceContainer.innerHTML = ''; // Clear existing content
            data.data.forEach(service => {
                const card = document.createElement('div');
                card.className = 'col-md-4 service-card';
                const imageUrl = service.imageURL ? `data:image/png;base64,${service.imageURL}` : 'assets/Images/noImage.png';
                card.innerHTML = `
              <div class="card mb-4">
        <img src="${imageUrl}" class="card-img-top" alt="Service Image">
        <div class="card-body">
            <h5 class="card-title">${service.name}</h5>
            <p class="card-title description-label">Description: <span>${service.description}</span></p>
            <p class="card-title">Duration: <span>${service.durationMinutes}</span></p>
                        <p class="card-title price-label">Rs. <span>${service.price}</span></p>

            <a href="#" class="btn btn-primary">View</a>
<!--            <a href="#" class="btn btn-danger disabled">Delete</a>-->
        </div>
    </div>
                `;
                serviceContainer.appendChild(card);
            });
        })
        .catch(error => console.error('Error fetching salon services:', error));
}

// Filter salon services based on input
function filterSalonServices() {
    const filter = document.getElementById('filterInput').value.toLowerCase();
    const cards = document.getElementsByClassName('service-card');
    for (let i = 0; i < cards.length; i++) {
        const card = cards[i];
        const name = card.getElementsByClassName('card-title')[0].innerText.toLowerCase();
        if (name.includes(filter)) {
            card.style.display = '';
        } else {
            card.style.display = 'none';
        }
    }
}