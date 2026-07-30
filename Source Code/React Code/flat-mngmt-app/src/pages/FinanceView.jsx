import { useState, useEffect } from 'react'
import api from '../api/axios'

export default function FinanceView() {
  const [dues, setDues] = useState([])
  const [expenses, setExpenses] = useState([])
  const [loading, setLoading] = useState(true)

  useEffect(() => {
    const fetchData = async () => {
      try {
        // Flat ID hardcoded for demo purposes since auth is not fully hooked up with profiles
        const duesRes = await api.get('/finance/dues/1') 
        setDues(duesRes.data)
      } catch (err) {
        console.error('Failed to fetch dues from API, falling back to dummy data', err)
        setDues([
          { flat: 'A-101', amount: '₹ 2,500', status: 'Pending', dueDate: '2026-08-01' },
          { flat: 'B-205', amount: '₹ 2,500', status: 'Paid', dueDate: '2026-08-01' },
          { flat: 'C-302', amount: '₹ 5,000', status: 'Overdue', dueDate: '2026-07-01' },
        ])
      }
      
      try {
        // Assume an endpoint exists to fetch all expenses for Committee
        // const expRes = await api.get('/finance/expense')
        // setExpenses(expRes.data)
        throw new Error('Endpoint not implemented yet')
      } catch (err) {
        setExpenses([
          { id: 'EXP-001', merchant: 'CleanCo Services', amount: '₹ 15,000', status: 'RAISED' },
          { id: 'EXP-002', merchant: 'Elevator Maintenance', amount: '₹ 25,000', status: 'APPROVED' },
          { id: 'EXP-003', merchant: 'Security Agency', amount: '₹ 45,000', status: 'PROCESSED' },
        ])
      }
      setLoading(false)
    }
    fetchData()
  }, [])

  return (
    <div className="space-y-8">
      <div className="glass-panel p-6">
        <div className="flex justify-between items-center mb-6">
          <h3 className="text-xl font-bold text-slate-100">Maintenance Dues Collection</h3>
          <button className="btn-secondary">Send Reminders</button>
        </div>
        <div className="overflow-x-auto">
          <table className="w-full text-left text-slate-300">
            <thead className="text-xs uppercase bg-slate-900/50 text-slate-400">
              <tr>
                <th className="px-6 py-4 rounded-tl-lg">Flat No.</th>
                <th className="px-6 py-4">Amount</th>
                <th className="px-6 py-4">Due Date</th>
                <th className="px-6 py-4 rounded-tr-lg">Status</th>
              </tr>
            </thead>
            <tbody>
              {dues.map((due, idx) => (
                <tr key={idx} className="border-b border-slate-700/50 hover:bg-slate-800/50 transition-colors">
                  <td className="px-6 py-4 font-medium text-slate-200">{due.flat || `Flat-${due.flatId}`}</td>
                  <td className="px-6 py-4">{due.amount}</td>
                  <td className="px-6 py-4">{due.dueDate}</td>
                  <td className="px-6 py-4">
                    <span className={`px-2.5 py-1 text-xs font-semibold rounded-full ${
                      due.status === 'Paid' || due.status === 'PAID' ? 'bg-emerald-500/20 text-emerald-400' :
                      due.status === 'Pending' || due.status === 'PENDING' ? 'bg-amber-500/20 text-amber-400' :
                      'bg-rose-500/20 text-rose-400'
                    }`}>
                      {due.status}
                    </span>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>

      <div className="glass-panel p-6">
        <div className="flex justify-between items-center mb-6">
          <h3 className="text-xl font-bold text-slate-100">Merchant Expense Approvals</h3>
          <button className="btn-primary py-2 text-sm">Raise Expense</button>
        </div>
        <div className="grid grid-cols-1 md:grid-cols-3 gap-6">
          {expenses.map((exp, idx) => (
            <div key={idx} className="bg-slate-900/50 border border-slate-700 rounded-xl p-5 relative">
              <div className="flex justify-between items-start mb-4">
                <div>
                  <span className="text-xs text-slate-500 font-mono">{exp.id || `EXP-${idx}`}</span>
                  <h4 className="text-lg font-semibold text-slate-200 mt-1">{exp.merchant || exp.merchantName}</h4>
                </div>
                <span className={`px-2 py-1 text-xs font-bold rounded ${
                  exp.status === 'RAISED' ? 'bg-blue-500/20 text-blue-400' :
                  exp.status === 'APPROVED' ? 'bg-amber-500/20 text-amber-400' :
                  'bg-emerald-500/20 text-emerald-400'
                }`}>
                  {exp.status}
                </span>
              </div>
              <p className="text-2xl font-bold text-slate-100 mb-6">{exp.amount}</p>
              
              <div className="flex gap-2">
                {exp.status === 'RAISED' && (
                  <button className="w-full btn-secondary text-sm">Approve (Pres.)</button>
                )}
                {exp.status === 'APPROVED' && (
                  <button className="w-full btn-primary py-2 text-sm">Process (Treas.)</button>
                )}
                {exp.status === 'PROCESSED' && (
                  <button className="w-full bg-slate-800 text-slate-500 font-medium py-2 rounded-lg cursor-not-allowed text-sm">Completed</button>
                )}
              </div>
            </div>
          ))}
        </div>
      </div>
    </div>
  )
}
