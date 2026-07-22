import { Routes, Route, Navigate } from 'react-router-dom'
import Login from './pages/Login'
import DashboardLayout from './pages/DashboardLayout'
import DashboardHome from './pages/DashboardHome'
import FinanceView from './pages/FinanceView'
import SocietyView from './pages/SocietyView'

function App() {
  return (
    <Routes>
      <Route path="/login" element={<Login />} />
      <Route path="/" element={<DashboardLayout />}>
        <Route index element={<DashboardHome />} />
        <Route path="finance" element={<FinanceView />} />
        <Route path="society" element={<SocietyView />} />
      </Route>
      <Route path="*" element={<Navigate to="/" replace />} />
    </Routes>
  )
}

export default App
