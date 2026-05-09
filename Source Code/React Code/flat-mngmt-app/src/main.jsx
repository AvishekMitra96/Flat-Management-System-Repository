import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import LoginPage from './LoginPage/LoginPage.jsx'

createRoot(document.getElementById('root')).render(
  //<h1>Hello React!</h1>
  <StrictMode>
    <LoginPage/>
  </StrictMode>
)
