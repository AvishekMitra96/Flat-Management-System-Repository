export default function SocietyView() {
  return (
    <div className="glass-panel p-6 min-h-[60vh] flex flex-col items-center justify-center text-center">
      <div className="w-20 h-20 bg-blue-600/20 rounded-full flex items-center justify-center mb-6 border border-blue-500/30">
        <svg className="w-10 h-10 text-blue-400" fill="none" stroke="currentColor" viewBox="0 0 24 24">
          <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M19 21V5a2 2 0 00-2-2H7a2 2 0 00-2 2v16m14 0h2m-2 0h-5m-9 0H3m2 0h5M9 7h1m-1 4h1m4-4h1m-1 4h1m-5 10v-5a1 1 0 011-1h2a1 1 0 011 1v5m-4 0h4" />
        </svg>
      </div>
      <h2 className="text-2xl font-bold text-slate-100 mb-2">Society Management</h2>
      <p className="text-slate-400 max-w-md mx-auto mb-8">
        Register new flat holders, manage committee members, and upload bulk residents via Excel.
      </p>
      
      <div className="flex gap-4">
        <button className="btn-primary">Onboard Flat Holder</button>
        <button className="btn-secondary">Bulk Upload (Excel)</button>
      </div>
    </div>
  )
}
