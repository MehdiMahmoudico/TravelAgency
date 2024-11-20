import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useParams, useNavigate } from 'react-router-dom';

function ReservationForm() {
  const { tripId } = useParams();
  const navigate = useNavigate();
  const [client, setClient] = useState({
    firstName: '',
    lastName: '',
    email: '',
    phone: '',
  });
  const [error, setError] = useState(null);
  const [trip, setTrip] = useState(null);
  const [isSubmitting, setIsSubmitting] = useState(false);
  useEffect(() => {
    axios.get(`http://localhost:8080/api/trips/${tripId}`)
      .then(response => setTrip(response.data))
      .catch(error => setError(error.message));
  }, [tripId]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setClient({ ...client, [name]: value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    setIsSubmitting(true);
  };

  useEffect(() => {
    if (!isSubmitting) return;

    const createReservation = async () => {
      try {
        // Create client
        const clientResponse = await axios.post('http://localhost:8080/api/clients/create', client);
        const clientId = clientResponse.data.id;

        // Create reservation
        const reservation = {
          client: { id: clientId },
          trip: { id: tripId },
          reservationDate: new Date().toISOString(),
          status: 'PENDING',
        };
        await axios.post('http://localhost:8080/api/reservations', reservation);

        // Redirect to PaymentPage with tripId and clientId
        navigate('/payment', { state: { tripId, clientId, price : trip.price } });
      } catch (error) {
        if (error.response && error.response.data) {
          setError(error.response.data);
        } else {
          setError(error.message);
        }
      } finally {
        setIsSubmitting(false);
      }
    };

    createReservation();
  }, [isSubmitting, client, tripId, navigate]);

  return (
    <div className="container mt-5">
      <h1 className="mb-4">Reservation Form</h1>
      {error && (
        <div className="alert alert-danger">
          {typeof error === 'string' ? error : Object.values(error).map((msg, index) => (
            <div key={index}>{msg}</div>
          ))}
        </div>
      )}
      <form onSubmit={handleSubmit}>
        <div className="mb-3">
          <label className="form-label">First Name:</label>
          <input
            type="text"
            className="form-control"
            name="firstName"
            value={client.firstName}
            onChange={handleChange}
          />
        </div>
        <div className="mb-3">
          <label className="form-label">Last Name:</label>
          <input
            type="text"
            className="form-control"
            name="lastName"
            value={client.lastName}
            onChange={handleChange}
          />
        </div>
        <div className="mb-3">
          <label className="form-label">Email:</label>
          <input
            type="email"
            className="form-control"
            name="email"
            value={client.email}
            onChange={handleChange}
          />
        </div>
        <div className="mb-3">
          <label className="form-label">Phone:</label>
          <input
            type="text"
            className="form-control"
            name="phone"
            value={client.phone}
            onChange={handleChange}
          />
        </div>
        <button type="submit" className="btn btn-primary" disabled={isSubmitting}>
          Submit
        </button>
      </form>
    </div>
  );
}

export default ReservationForm;
