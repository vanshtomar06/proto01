function calculateRadius(boundingBox) {
    const minLat = parseFloat(boundingBox[0]);
    const maxLat = parseFloat(boundingBox[1]);
    const minLon = parseFloat(boundingBox[2]);
    const maxLon = parseFloat(boundingBox[3]);
    const width = maxLon - minLon;
    const height = maxLat - minLat;
    const averageDimension = (width + height) / 2;
    const radiusInMeters = averageDimension * 111320;

    return radiusInMeters / 2;
}

function fetchCoordinatesAndRadius(areaName) {
    const url = `https://nominatim.openstreetmap.org/search?q=${encodeURIComponent(areaName)}&format=json`;

    fetch(url)
        .then(response => {
            if (!response.ok) {
                throw new Error('Error fetching coordinates');
            }
            return response.json();
        })
        .then(data => {
            if (data.length > 0) {
                const { lat, lon, boundingbox } = data[0];
                const radius = calculateRadius(boundingbox);
                console.log('Coordinates:', lat, lon);
                console.log('Estimated Radius (meters):', radius);
            } else {
                console.log('No results found for the area.');
            }
        })
        .catch(error => console.error('Error:', error));
}

function handleReport() {
    alert('Report clicked. You can add your reporting logic here.');
}

function showAnalytics() {
    alert('Analytics clicked. You can add your analytics logic here.');
}



var currentMarker = null
var map = L.map('map', {
    doubleClickZoom: false
}).setView([28.7041, 77.1025], 12);

function removeMarker() {
    if (currentMarker) {
        map.removeLayer(currentMarker); // Remove the marker from the map
        currentMarker = null; // Reset the marker variable
    }
}

L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
    attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
}).addTo(map);
fetch('/api/areas')
    .then(response => response.json())
    .then(data => {
        data.forEach(function(area) {
            var circle = L.circle([area.lat, area.lng], {
                color: area.color,
                fillColor: area.color,
                fillOpacity: 0.5,
                radius: area.radius
            }).addTo(map);
            circle.bindPopup("Reports: " + area.reportCount);
        });
    })
    .catch(error => console.error('Error fetching areas:', error));

map.on('dblclick', function(e) {
    var latlng = e.latlng; // Get latitude and longitude of the clicked point

    if (currentMarker) {
        map.removeLayer(currentMarker);
    }

    // Create a marker
    currentMarker = L.marker([latlng.lat, latlng.lng]).addTo(map);

    // Create an options menu as a popup with options like Report, Analytics, etc.
    var popupContent = `
        <strong>Options</strong><br>
        <button onclick="handleReport()">Report</button><br>
        <button onclick="showAnalytics()">Analytics</button>
    `;

    currentMarker.bindPopup(popupContent).openPopup();
});