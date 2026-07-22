export default function DashboardHome() {
  const stats = [
    { label: 'Total Collections', value: '₹ 1,25,000', color: 'from-emerald-500 to-teal-600' },
    { label: 'Pending Dues', value: '₹ 45,000', color: 'from-rose-500 to-red-600' },
    { label: 'Open Complaints', value: '12', color: 'from-amber-500 to-orange-600' },
    { label: 'Total Flats', value: '142', color: 'from-blue-500 to-indigo-600' },
  ]

  return (
    <div className="space-y-6">
      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6">
        {stats.map((stat, i) => (
          <div key={i} className="glass-panel p-6 relative overflow-hidden group hover:-translate-y-1 transition-transform cursor-pointer">
            <div className={`absolute inset-0 bg-gradient-to-br ${stat.color} opacity-10 group-hover:opacity-20 transition-opacity`}></div>
            <p className="text-slate-400 text-sm font-medium">{stat.label}</p>
            <p className="text-3xl font-bold text-slate-100 mt-2">{stat.value}</p>
          </div>
        ))}
      </div>

      <div className="grid grid-cols-1 lg:grid-cols-2 gap-6 mt-8">
        <div className="glass-panel p-6">
          <h3 className="text-lg font-semibold text-slate-200 mb-4">Recent Announcements</h3>
          <div className="space-y-4">
            <div className="p-4 bg-slate-900/50 rounded-xl border border-slate-700">
              <span className="text-xs font-semibold text-blue-400 bg-blue-400/10 px-2 py-1 rounded">Maintenance</span>
              <p className="text-slate-200 mt-2">Water supply will be affected tomorrow from 10 AM to 2 PM due to tank cleaning.</p>
              <p className="text-slate-500 text-sm mt-2">Posted by Secretary • 2 hours ago</p>
            </div>
            <div className="p-4 bg-slate-900/50 rounded-xl border border-slate-700">
              <span className="text-xs font-semibold text-purple-400 bg-purple-400/10 px-2 py-1 rounded">Event</span>
              <p className="text-slate-200 mt-2">Upcoming General Body Meeting scheduled for Sunday. Google Meet link will be shared.</p>
              <p className="text-slate-500 text-sm mt-2">Posted by President • 1 day ago</p>
            </div>
          </div>
        </div>

        <div className="glass-panel p-6">
          <h3 className="text-lg font-semibold text-slate-200 mb-4">Quick Actions</h3>
          <div className="grid grid-cols-2 gap-4">
            <button className="p-4 bg-slate-900/50 hover:bg-slate-800 rounded-xl border border-slate-700 transition-colors flex flex-col items-center justify-center gap-3">
              <div className="w-12 h-12 rounded-full bg-blue-500/20 text-blue-400 flex items-center justify-center text-2xl">+</div>
              <span className="font-medium text-slate-300">Raise Expense</span>
            </button>
            <button className="p-4 bg-slate-900/50 hover:bg-slate-800 rounded-xl border border-slate-700 transition-colors flex flex-col items-center justify-center gap-3">
              <div className="w-12 h-12 rounded-full bg-emerald-500/20 text-emerald-400 flex items-center justify-center text-2xl">₹</div>
              <span className="font-medium text-slate-300">Collect Dues</span>
            </button>
            <button className="p-4 bg-slate-900/50 hover:bg-slate-800 rounded-xl border border-slate-700 transition-colors flex flex-col items-center justify-center gap-3">
              <div className="w-12 h-12 rounded-full bg-rose-500/20 text-rose-400 flex items-center justify-center text-xl">⚠️</div>
              <span className="font-medium text-slate-300">View Complaints</span>
            </button>
            <button className="p-4 bg-slate-900/50 hover:bg-slate-800 rounded-xl border border-slate-700 transition-colors flex flex-col items-center justify-center gap-3">
              <div className="w-12 h-12 rounded-full bg-amber-500/20 text-amber-400 flex items-center justify-center text-xl">📢</div>
              <span className="font-medium text-slate-300">New Notice</span>
            </button>
          </div>
        </div>
      </div>
    </div>
  )
}
