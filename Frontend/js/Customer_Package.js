// Load salon packages when the DOM is fully loaded
document.addEventListener('DOMContentLoaded', function() {
    // Add this style creation code (unchanged from original customer_package.js)
    const styleSheet = document.createElement('style');
    styleSheet.textContent = `
    /* Card Styling */
.card {
    border: none;
    border-radius: 15px;
    overflow: hidden;
    transition: transform 0.3s ease, box-shadow 0.3s ease;
    box-shadow: 0 6px 16px rgba(0, 0, 0, 0.1);
    margin-bottom: 2rem;
    background: #ffffff;
    padding-bottom: 20px;
}

.card:hover {
    transform: translateY(-5px);
    box-shadow: 0 10px 24px rgba(0, 0, 0, 0.15);
}

.card-img-top {
    height: 220px;
    object-fit: cover;
    border-bottom: 3px solid #f5f5f5;
}

.card-body {
    padding: 1.5rem;
    text-align: left;
}

/* Title */
.card-title {
    font-weight: 700;
    color: #2c3e50;
    margin-bottom: 0.75rem;
    font-size: 1.3rem;
}

/* Description */
.card-body p:nth-of-type(1) {
    font-size: 1rem;
    font-weight: 400;
    color: #7f8c8d;
    margin-bottom: 0.5rem;
    line-height: 1.4;
}

/* Total Duration */
.card-body p:nth-of-type(2) {
    color: #95a5a6;
    font-size: 1rem;
    font-weight: 500;
    margin-bottom: 0.5rem;
}

/* Validity */
.card-body p:nth-of-type(3) {
    color: #3498db;
    font-size: 1rem;
    font-weight: 500;
    margin-bottom: 0.5rem;
}

/* Price Styling */
.card-body p:nth-of-type(4) {
    font-size: 2rem;
    font-weight: 800;
    color: #d81b60;
    background: rgba(216, 27, 96, 0.1);
    padding: 12px 18px;
    border-radius: 10px;
    display: inline-block;
    margin-top: 10px;
}

/* Button Styling */
.btn-primary {
    background: linear-gradient(135deg, #ff6f61 0%, #d81b60 100%);
    border: none;
    padding: 12px 30px;
    font-weight: 600;
    font-size: 1rem;
    border-radius: 30px;
    text-transform: uppercase;
    letter-spacing: 0.5px;
    box-shadow: 0 4px 8px rgba(216, 27, 96, 0.2);
    transition: all 0.3s ease;
    display: block;
    width: fit-content;
    margin-top: 15px;
}

.btn-primary:hover {
    transform: translateY(-3px);
    box-shadow: 0 6px 12px rgba(216, 27, 96, 0.3);
    background: linear-gradient(135deg, #ff8a80 0%, #f06292 100%);
}
    `;
    document.head.appendChild(styleSheet);

    fetchSalonPackages();
});

// Fetch all salon packages from the API
function fetchSalonPackages() {
    fetch('http://localhost:8080/api/v1/package/get-all', {
        headers: {
            'Authorization': 'Bearer ' + localStorage.getItem('jwt_token')
        }
    })
        .then(response => response.json())
        .then(data => {
            const packageContainer = document.getElementById('packageContainer');
            packageContainer.innerHTML = ''; // Clear existing content
            data.data.forEach(packageItem => {
                const card = document.createElement('div');
                card.className = 'col-md-4 package-card';
                const imageUrl = packageItem.imageURL ? `data:image/png;base64,${packageItem.imageURL}` : 'assets/Images/noImage.png';
                card.innerHTML = `
                    <div class="card mb-4">
                        <img src="${imageUrl}" class="card-img-top" alt="Package Image">
                        <div class="card-body">
                            <h5 class="card-title">${packageItem.name}</h5>
                            <p class="card-title description-label">Description: <span>${packageItem.description}</span></p>
                            <p class="card-title">Total Duration: <span>${packageItem.totalDurationMinutes} minutes</span></p>
                            <p class="card-title">Validity: <span>${packageItem.validityDays} days</span></p>
                            <p class="card-title price-label">Rs. <span>${packageItem.totalPrice}</span></p>
                            <a href="Package_Appointment.html?package=${encodeURIComponent(packageItem.name)}&price=${encodeURIComponent(packageItem.totalPrice)}" class="btn btn-primary">BOOK NOW</a>
                        </div>
                    </div>
                `;
                packageContainer.appendChild(card);
            });
        })
        .catch(error => console.error('Error fetching salon packages:', error));
}

// Filter salon packages based on input
function filterSalonPackages() {
    const filter = document.getElementById('filterInput').value.toLowerCase();
    const cards = document.getElementsByClassName('package-card');
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


/*

// Load salon packages when the DOM is fully loaded
document.addEventListener('DOMContentLoaded', function() {
    // Add this style creation code:
    const styleSheet = document.createElement('style');
    styleSheet.textContent = `
    /!* Card Styling *!/
.card {
    border: none;
    border-radius: 15px;
    overflow: hidden;
    transition: transform 0.3s ease, box-shadow 0.3s ease;
    box-shadow: 0 6px 16px rgba(0, 0, 0, 0.1);
    margin-bottom: 2rem;
    background: #ffffff;
    padding-bottom: 20px;
}

.card:hover {
    transform: translateY(-5px);
    box-shadow: 0 10px 24px rgba(0, 0, 0, 0.15);
}

.card-img-top {
    height: 220px;
    object-fit: cover;
    border-bottom: 3px solid #f5f5f5;
}

.card-body {
    padding: 1.5rem;
    text-align: left;
}

/!* Title *!/
.card-title {
    font-weight: 700;
    color: #2c3e50;
    margin-bottom: 0.75rem;
    font-size: 1.3rem;
}

/!* Description *!/
.card-body p:nth-of-type(1) {
    font-size: 1rem;
    font-weight: 400;
    color: #7f8c8d;
    margin-bottom: 0.5rem;
    line-height: 1.4;
}

/!* Total Duration *!/
.card-body p:nth-of-type(2) {
    color: #95a5a6;
    font-size: 1rem;
    font-weight: 500;
    margin-bottom: 0.5rem;
}

/!* Validity *!/
.card-body p:nth-of-type(3) {
    color: #3498db;
    font-size: 1rem;
    font-weight: 500;
    margin-bottom: 0.5rem;
}

/!* Price Styling *!/
.card-body p:nth-of-type(4) {
    font-size: 2rem;
    font-weight: 800;
    color: #d81b60;
    background: rgba(216, 27, 96, 0.1);
    padding: 12px 18px;
    border-radius: 10px;
    display: inline-block;
    margin-top: 10px;
}

/!* Button Styling *!/
.btn-primary {
    background: linear-gradient(135deg, #ff6f61 0%, #d81b60 100%);
    border: none;
    padding: 12px 30px;
    font-weight: 600;
    font-size: 1rem;
    border-radius: 30px;
    text-transform: uppercase;
    letter-spacing: 0.5px;
    box-shadow: 0 4px 8px rgba(216, 27, 96, 0.2);
    transition: all 0.3s ease;
    display: block;
    width: fit-content;
    margin-top: 15px;
}

.btn-primary:hover {
    transform: translateY(-3px);
    box-shadow: 0 6px 12px rgba(216, 27, 96, 0.3);
    background: linear-gradient(135deg, #ff8a80 0%, #f06292 100%);
}


    `;
    document.head.appendChild(styleSheet);

    fetchSalonPackages();
});

// Fetch all salon packages from the API
function fetchSalonPackages() {
    fetch('http://localhost:8080/api/v1/package/get-all', {
        headers: {
            'Authorization': 'Bearer ' + localStorage.getItem('jwt_token')
        }
    })
        .then(response => response.json())
        .then(data => {
            const packageContainer = document.getElementById('packageContainer');
            packageContainer.innerHTML = ''; // Clear existing content
            data.data.forEach(packageItem => {
                const card = document.createElement('div');
                card.className = 'col-md-4 package-card';
                const imageUrl = packageItem.imageURL ? `data:image/png;base64,${packageItem.imageURL}` : 'assets/Images/noImage.png';
                card.innerHTML = `
              <div class="card mb-4">
        <img src="${imageUrl}" class="card-img-top" alt="Package Image">
        <div class="card-body">
            <h5 class="card-title">${packageItem.name}</h5>
            <p class="card-title">Description: <span>${packageItem.description}</span></p>
            <p class="card-title">Total Duration: <span>${packageItem.totalDurationMinutes} minutes</span></p>
            <p class="card-title">Validity: <span>${packageItem.validityDays} days</span></p>
            <p class="card-title">Rs. <span>${packageItem.totalPrice}</span></p>
            <a href="#" class="btn btn-primary">View</a>
        </div>
    </div>
                `;
                packageContainer.appendChild(card);
            });
        })
        .catch(error => console.error('Error fetching salon packages:', error));
}

// Filter salon packages based on input
function filterSalonPackages() {
    const filter = document.getElementById('filterInput').value.toLowerCase();
    const cards = document.getElementsByClassName('package-card');
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
*/
