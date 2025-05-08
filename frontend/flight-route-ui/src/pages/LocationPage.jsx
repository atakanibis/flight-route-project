import { useEffect, useState } from 'react';
import axios from 'axios';

export default function LocationPage() {
  const [locations, setLocations] = useState([]);
  const [formData, setFormData] = useState({
    id: null,
    name: '',
    city: '',
    country: '',
    locationCode: ''
  });

  useEffect(() => {
    fetchLocations();
  }, []);

  const fetchLocations = () => {
    axios.get('http://localhost:8080/api/locations')
      .then(res => setLocations(res.data))
      .catch(err => console.error('Lokasyonlar alınamadı:', err));
  };

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    if (formData.id) {
      // Güncelle
      axios.put(`http://localhost:8080/api/locations/${formData.id}`, formData)
        .then(() => {
          fetchLocations();
          resetForm();
        })
        .catch(err => console.error('Güncelleme hatası:', err));
    } else {
      // Yeni ekleme
      axios.post('http://localhost:8080/api/locations', formData)
        .then(() => {
          fetchLocations();
          resetForm();
        })
        .catch(err => console.error('Ekleme hatası:', err));
    }
  };

  const handleEdit = (loc) => {
    setFormData(loc);
  };

  const handleDelete = (id) => {
    axios.delete(`http://localhost:8080/api/locations/${id}`)
      .then(() => fetchLocations())
      .catch(err => console.error('Silme hatası:', err));
  };

  const resetForm = () => {
    setFormData({ id: null, name: '', city: '', country: '', locationCode: '' });
  };

  return (
    <div>
      <h2>📍 Lokasyonlar</h2>
      <ul>
        {locations.map(loc => (
          <li key={loc.id}>
            <strong>{loc.name}</strong> - {loc.city}, {loc.country} ({loc.locationCode})
            <button onClick={() => handleEdit(loc)} style={{ marginLeft: '10px' }}>Düzenle</button>
            <button onClick={() => handleDelete(loc.id)} style={{ marginLeft: '5px' }}>Sil</button>
          </li>
        ))}
      </ul>

      <h3>{formData.id ? 'Lokasyon Güncelle' : 'Yeni Lokasyon Ekle'}</h3>
      <form onSubmit={handleSubmit}>
        <input
          type="text"
          name="name"
          placeholder="Ad"
          value={formData.name}
          onChange={handleChange}
          required
        />
        <input
          type="text"
          name="city"
          placeholder="Şehir"
          value={formData.city}
          onChange={handleChange}
          required
        />
        <input
          type="text"
          name="country"
          placeholder="Ülke"
          value={formData.country}
          onChange={handleChange}
          required
        />
        <input
          type="text"
          name="locationCode"
          placeholder="Kod"
          value={formData.locationCode}
          onChange={handleChange}
          required
        />
        <button type="submit">{formData.id ? 'Güncelle' : 'Ekle'}</button>
        {formData.id && <button type="button" onClick={resetForm}>İptal</button>}
      </form>
    </div>
  );
}
