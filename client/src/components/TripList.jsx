// TripList.jsx
import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

function TripList() {
  const [trips, setTrips] = useState([]);
  const [error, setError] = useState(null);
  const [currentPage, setCurrentPage] = useState(1);
  const tripsPerPage = 6; // Number of trips per page
  const navigate = useNavigate();

  useEffect(() => {
    axios.get('http://localhost:8080/api/trips')
      .then(response => {
        if (Array.isArray(response.data)) {
          setTrips(response.data);
        } else {
          throw new Error('Unexpected response format');
        }
      })
      .catch(error => {
        console.error('Error fetching trips:', error);
        setError(error.message);
      });
  }, []);

  if (error) {
    return <div className="alert alert-danger">Error: {error}</div>;
  }

  // Calculate the index of the first and last trip on the current page
  const indexOfLastTrip = currentPage * tripsPerPage;
  const indexOfFirstTrip = indexOfLastTrip - tripsPerPage;
  const currentTrips = trips.slice(indexOfFirstTrip, indexOfLastTrip);

  // Handle page navigation
  const handlePreviousPage = () => {
    if (currentPage > 1) {
      setCurrentPage(currentPage - 1);
    }
  };

  const handleNextPage = () => {
    if (currentPage < Math.ceil(trips.length / tripsPerPage)) {
      setCurrentPage(currentPage + 1);
    }
  };

  const handleReservationClick = (tripId) => {
    navigate(`/reserve/${tripId}`);
  };

  return (
    <div className="container mt-5">
      <h1 className="mb-4">Trip List</h1>
      <div className="row">
        {currentTrips.map(trip => (
          <div key={trip.id} className="col-md-4 mb-4">
            <div className="card h-100">
              <div className="card-body">
                <h5 className="card-title">{trip.destination}</h5>
                <p className="card-text">
                  <strong>Dates:</strong> {trip.startDate} to {trip.endDate}
                </p>
                <p className="card-text">
                  <strong>Price:</strong> ${trip.price}
                </p>
                <button
                  onClick={() => handleReservationClick(trip.id)}
                  className="btn btn-primary"
                >
                  Reserve
                </button>
              </div>
            </div>
          </div>
        ))}
      </div>
      
      {/* Pagination Controls */}
      <div className="d-flex justify-content-between mt-4">
        <button
          onClick={handlePreviousPage}
          className="btn btn-secondary"
          disabled={currentPage === 1}
        >
          Previous
        </button>
        <span>Page {currentPage} of {Math.ceil(trips.length / tripsPerPage)}</span>
        <button
          onClick={handleNextPage}
          className="btn btn-secondary"
          disabled={currentPage === Math.ceil(trips.length / tripsPerPage)}
        >
          Next
        </button>
      </div>
    </div>
  );
}

export default TripList;
