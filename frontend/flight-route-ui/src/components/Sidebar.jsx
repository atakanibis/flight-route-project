import { Link } from 'react-router-dom';

export default function Sidebar() {
  return (
    <div style={{ width: '200px', padding: '20px', borderRight: '1px solid #ccc' }}>
      <h3>Flight Route</h3>
      <ul style={{ listStyle: 'none', padding: 0 }}>
        <li><Link to="/locations">Locations</Link></li>
        <li><Link to="/transportations">Transportations</Link></li>
        <li><Link to="/routes">Find Route</Link></li>
      </ul>
    </div>
  );
}
