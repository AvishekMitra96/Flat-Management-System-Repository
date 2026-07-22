import { Outlet, Link, useLocation, useNavigate } from 'react-router-dom'

export default function DashboardLayout() {
  const location = useLocation()
  const navigate = useNavigate()

  const navItems = [
    { name: 'Overview', path: '/' },
    { name: 'Finance & Dues', path: '/finance' },
    { name: 'Society Management', path: '/society' },
  ]

  return (
    <div className="min-h-screen flex bg-slate-900">
      {/* Sidebar */}
      <aside className="w-64 glass-panel m-4 flex flex-col justify-between hidden md:flex">
        <div>
          <div className="p-6 border-b border-slate-700/50 flex items-center gap-3">
            <div className="w-10 h-10 bg-blue-600 rounded-xl flex items-center justify-center shadow-lg shadow-blue-500/20">
               <svg className="w-5 h-5 text-white" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M19 21V5a2 2 0 00-2-2H7a2 2 0 00-2 2v16m14 0h2m-2 0h-5m-9 0H3m2 0h5M9 7h1m-1 4h1m4-4h1m-1 4h1m-5 10v-5a1 1 0 011-1h2a1 1 0 011 1v5m-4 0h4" />
                </svg>
            </div>
            <h1 className="text-xl font-bold bg-clip-text text-transparent bg-gradient-to-r from-blue-400 to-indigo-400">FMS</h1>
          </div>
          <nav className="p-4 space-y-2">
            {navItems.map((item) => (
              <Link
                key={item.path}
                to={item.path}
                className={`block px-4 py-3 rounded-xl transition-all ${
                  location.pathname === item.path
                    ? 'bg-blue-600/20 text-blue-400 font-medium border border-blue-500/30'
                    : 'text-slate-400 hover:bg-slate-800 hover:text-slate-200'
                }`}
              >
                {item.name}
              </Link>
            ))}
          </nav>
        </div>
        <div className="p-4 border-t border-slate-700/50">
          <button onClick={() => navigate('/login')} className="w-full text-left px-4 py-3 text-slate-400 hover:text-red-400 transition-colors">
            Logout
          </button>
        </div>
      </aside>

      {/* Main Content */}
      <main className="flex-1 p-4 md:p-8 overflow-y-auto">
        <header className="flex items-center justify-between mb-8 glass-panel px-6 py-4">
          <h2 className="text-2xl font-semibold text-slate-100 capitalize">
            {navItems.find(n => n.path === location.pathname)?.name || 'Dashboard'}
          </h2>
          <div className="flex items-center gap-4">
             <div className="w-10 h-10 rounded-full bg-slate-700 border-2 border-blue-500 overflow-hidden">
                <img src="https://i.pravatar.cc/150?u=admin" alt="Profile" />
             </div>
          </div>
        </header>

        <div className="animate-in slide-in-from-bottom-4 duration-500">
          <Outlet />
        </div>
      </main>
    </div>
  )
}
