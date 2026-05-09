import React, { useState } from 'react'
import styles  from './LoginPage.module.css'

function LoginPage() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");

  /*const handleSubmit = (e) => {
    e.preventDefault();

   // Basic validation
    if (!email || !password) {
      setError("Both fields are required!");
      return;
    }

    // Mock authentication (replace with API call)
    if (email === "admin" && password === "admin123") {
      alert("Login successful!");
      setError("");
      // Redirect or set auth state here
    } else {
      setError("Invalid email or password");
    }
  };*/
  return (
    <div style={styles.container}>
      <h2>Flat Management System - Login</h2>
      <form>
        <input
          type="email"
          placeholder="Enter Email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          style={styles.input}
        />
        <input
          type="password"
          placeholder="Enter Password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          style={styles.input}
        />
        {error && <p style={styles.error}>{error}</p>}
        <button type="submit" style={styles.button}>Login</button>
      </form>
    </div>
  );
}
export default LoginPage;
