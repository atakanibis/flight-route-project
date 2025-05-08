import { Outlet } from 'react-router-dom';
import Sidebar from './components/Sidebar';

export default function App() {
  return (
    <div style={{ display: 'flex' }}>
      <Sidebar />
      <div style={{ padding: '20px', flexGrow: 1 }}>
        <Outlet />
      </div>
    </div>
  );
}
