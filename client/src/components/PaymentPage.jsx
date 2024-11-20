import React, { useState, useEffect } from "react";
import axios from "axios";
import { useLocation } from "react-router-dom";

const PaymentPage = () => {
  const location = useLocation();
  const { tripId, clientId, price } = location.state || {}; // Get tripId and clientId from the state passed by ReservationForm

  const [amount, setAmount] = useState(price);
  const [currency, setCurrency] = useState("USD");
  const [receiverWalletId, setReceiverWalletId] = useState("673537b0be3490452075a977");
  const [description, setDescription] = useState("");
  const [lifespan, setLifespan] = useState(30); // Default lifespan 30 minutes

  // Handle form submission
  const handleSubmit = async (e) => {
    e.preventDefault();
    const amount = Math.floor(price); 
    const paymentRequest = {
      amount: parseFloat(amount),
      receiverWalletId: receiverWalletId,
      description: description,
      lifespan: lifespan,
    };

    try {
      // Send request to backend API to initiate the payment
      const response = await axios.post("http://localhost:8080/api/payments/initiate", paymentRequest);

      // Redirect to the payment URL
      
      const payUrl = response.data.payUrl; // Extract the payUrl from the response
      if (payUrl) {
        window.location.href = payUrl; // Redirect to the payment gateway
      } else {
        throw new Error("Payment URL not found in the response.");
      }

      // Assuming response contains the payment URL
    } catch (error) {
      console.error("Error initiating payment:", error);
      alert("Error initiating payment. Please try again.");
    }
  };

  return (
    <div>
      <h2>Initiate Payment for Trip {tripId} - Client {clientId}</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Amount</label>
          <input
            type="number"
            value={price}
            onChange={(e) => setAmount(e.target.value)}
            required
          />
        </div>
        <div>
          <label>Description</label>
          <input
            type="text"
            value={description}
            onChange={(e) => setDescription(e.target.value)}
            required
          />
        </div>
        <button type="submit">Initiate Payment</button>
      </form>
    </div>
  );
};

export default PaymentPage;
