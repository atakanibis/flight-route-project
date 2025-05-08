import { useEffect, useState } from 'react';
import axios from 'axios';
import Select from 'react-select';
import DatePicker from 'react-datepicker';
import 'react-datepicker/dist/react-datepicker.css';

export default function RouteFinderPage() {
  const [locations, setLocations] = useState([]);
  const [origin, setOrigin] = useState(null);
  const [destination, setDestination] = useState(null);
  const [routes, setRoutes] = useState([]);
  const [selectedDate, setSelectedDate] = useState(null);

  useEffect(() => {
    axios.get('http://localhost:8080/api/locations')
      .then(res => {
        const options = res.data.map(loc => ({
          value: loc.id,
          label: `${loc.name} (${loc.city})`
        }));
        setLocations(options);
      });
  }, []);

  const formatDateLocal = (date) => {
    const year = date.getFullYear();
    const month = `${date.getMonth() + 1}`.padStart(2, '0');
    const day = `${date.getDate()}`.padStart(2, '0');
    return `${year}-${month}-${day}`;
  };

  const findRoutes = () => {
    if (!origin || !destination) return;

    const params = new URLSearchParams();
    params.append("origin", origin.value);
    params.append("destination", destination.value);
    if (selectedDate) {
      params.append("date", formatDateLocal(selectedDate));
    }

    axios.get(`http://localhost:8080/api/routes?${params.toString()}`)
      .then(res => setRoutes(res.data))
      .catch(err => {
        console.error('Rota bulunamadı:', err);
        setRoutes([]);
      });
  };

  return (
    <div className="container">
      <div className="card">
        <h2>🧭 Rota Bulucu</h2>
        <div style={{ display: 'flex', gap: '10px', flexWrap: 'wrap' }}>
          <div style={{ flex: 1, minWidth: 200 }}>
            <label>Başlangıç Noktası</label>
            <Select
              options={locations}
              value={origin}
              onChange={setOrigin}
              placeholder="Başlangıç seçin"
            />
          </div>
          <div style={{ flex: 1, minWidth: 200 }}>
            <label>Varış Noktası</label>
            <Select
              options={locations}
              value={destination}
              onChange={setDestination}
              placeholder="Varış seçin"
            />
          </div>
          <div style={{ flex: 1, minWidth: 200 }}>
            <label>Tarih (opsiyonel)</label>
            <DatePicker
              selected={selectedDate}
              onChange={(date) => setSelectedDate(date)}
              placeholderText="Tarih seçin"
              dateFormat="dd/MM/yyyy"
              className="day-select"
            />
          </div>
          <button onClick={findRoutes} style={{ height: '40px', alignSelf: 'flex-end' }}>
            Rota Bul
          </button>
        </div>
      </div>

      {routes.length > 0 && (
        <div className="card">
          <h3>Bulunan Rotalar</h3>
          {routes.map((route, index) => (
            <div
              key={index}
              style={{
                marginBottom: '16px',
                padding: '10px',
                border: '1px solid #ccc',
                borderRadius: '6px',
                backgroundColor: '#fff',
              }}
            >
              <strong>Rota #{index + 1}</strong>
              <ul style={{ marginTop: '8px', paddingLeft: '20px' }}>
                {route.steps.map((step, i) => (
                  <li key={i}>
                    ➡ {transportTypeText(step.type)} from <strong>{step.origin}</strong> to <strong>{step.destination}</strong>
                  </li>
                ))}
              </ul>
            </div>
          ))}
        </div>
      )}

      {routes.length === 0 && origin && destination && (
        <div className="card">
          <p>Rota bulunamadı.</p>
        </div>
      )}
    </div>
  );
}

function transportTypeText(type) {
  switch (type) {
    case "FLIGHT": return "Flight";
    case "BUS": return "Bus ride";
    case "UBER": return "Uber ride";
    case "SUBWAY": return "Subway";
    default: return type;
  }
}
