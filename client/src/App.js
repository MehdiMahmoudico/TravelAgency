import React from 'react';
import { Route, Routes } from 'react-router-dom';
import TripList from './components/TripList';
import ReservationForm from './components/ReservationForm';
import PaymentPage from './components/PaymentPage';

function App() {
  return (
    <Routes>
      <Route path="/trips" element={<TripList />} />
      <Route path="/reserve/:tripId" element={<ReservationForm />} />
      <Route path="/payment" element={<PaymentPage />} />

    </Routes>
  );
}

export default App;
