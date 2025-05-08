import React from 'react';
import ReactDOM from 'react-dom/client';
import {
  BrowserRouter,
  Routes,
  Route
} from 'react-router-dom';
import App from './App';
import LocationPage from './pages/LocationPage';
import TransportationPage from './pages/TransportationPage';
import RouteFinderPage from './pages/RouteFinderPage';

ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<App />}>
          <Route path="locations" element={<LocationPage />} />
          <Route path="transportations" element={<TransportationPage />} />
          <Route path="routes" element={<RouteFinderPage />} />
        </Route>
      </Routes>
    </BrowserRouter>
  </React.StrictMode>
);
