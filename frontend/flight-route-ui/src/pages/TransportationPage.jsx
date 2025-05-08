import { useEffect, useState } from 'react';
import axios from 'axios';
import Select from 'react-select';
import '../App.css';

export default function TransportationPage() {
  const [locations, setLocations] = useState([]);
  const [transportations, setTransportations] = useState([]);
  const [formData, setFormData] = useState({
    id: null,
    originId: '',
    destinationId: '',
    type: 'UBER',
    operatingDays: []
  });

  const daysOfWeek = [
    { value: 1, label: "Pazartesi" },
    { value: 2, label: "Salı" },
    { value: 3, label: "Çarşamba" },
    { value: 4, label: "Perşembe" },
    { value: 5, label: "Cuma" },
    { value: 6, label: "Cumartesi" },
    { value: 7, label: "Pazar" },
  ];

  useEffect(() => {
    fetchLocations();
    fetchTransportations();
  }, []);

  const fetchLocations = () => {
    axios.get('http://localhost:8080/api/locations')
      .then(res => setLocations(res.data));
  };

  const fetchTransportations = () => {
    axios.get('http://localhost:8080/api/transportations')
      .then(res => setTransportations(res.data));
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleDaysChange = (selectedOptions) => {
    const selectedValues = selectedOptions.map(opt => opt.value);
    setFormData({ ...formData, operatingDays: selectedValues });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    const payload = {
      origin: { id: Number(formData.originId) },
      destination: { id: Number(formData.destinationId) },
      type: formData.type,
      operatingDays: formData.operatingDays
    };

    const request = formData.id
      ? axios.put(`http://localhost:8080/api/transportations/${formData.id}`, payload)
      : axios.post('http://localhost:8080/api/transportations', payload);

    request.then(() => {
      fetchTransportations();
      resetForm();
    });
  };

  const handleEdit = (t) => {
    setFormData({
      id: t.id,
      originId: t.origin.id,
      destinationId: t.destination.id,
      type: t.type,
      operatingDays: t.operatingDays || []
    });
  };

  const handleDelete = (id) => {
    axios.delete(`http://localhost:8080/api/transportations/${id}`)
      .then(() => fetchTransportations());
  };

  const resetForm = () => {
    setFormData({
      id: null,
      originId: '',
      destinationId: '',
      type: 'UBER',
      operatingDays: []
    });
  };

  const dayNumbersToNames = (numbers) => {
    return numbers
      .map(num => daysOfWeek.find(day => day.value === num)?.label)
      .filter(Boolean)
      .join(', ');
  };

  return (
    <div>
      <h2>🚌 Ulaşım Bilgileri</h2>
      <ul>
        {transportations.map(t => (
          <li key={t.id}>
            {t.origin.name} ➡ {t.destination.name} | {t.type} | Günler: {dayNumbersToNames(t.operatingDays)}
            <button onClick={() => handleEdit(t)} style={{ marginLeft: '10px' }}>Düzenle</button>
            <button onClick={() => handleDelete(t.id)} style={{ marginLeft: '5px' }}>Sil</button>
          </li>
        ))}
      </ul>

      <h3>{formData.id ? 'Ulaşım Güncelle' : 'Yeni Ulaşım Ekle'}</h3>
      <form onSubmit={handleSubmit}>
        <select name="originId" value={formData.originId} onChange={handleChange} required>
          <option value="">Başlangıç Noktası</option>
          {locations.map(loc => (
            <option key={loc.id} value={loc.id}>{loc.name}</option>
          ))}
        </select>

        <select name="destinationId" value={formData.destinationId} onChange={handleChange} required>
          <option value="">Varış Noktası</option>
          {locations.map(loc => (
            <option key={loc.id} value={loc.id}>{loc.name}</option>
          ))}
        </select>

        <select name="type" value={formData.type} onChange={handleChange}>
          <option value="UBER">UBER</option>
          <option value="FLIGHT">FLIGHT</option>
          <option value="BUS">BUS</option>
        </select>

        <div style={{ width: '300px', margin: '10px 0' }}>
          <Select
            name="operatingDays"
            options={daysOfWeek}
            value={daysOfWeek.filter(day => formData.operatingDays.includes(day.value))}
            onChange={handleDaysChange}
            isMulti
            placeholder="Çalışma Günleri Seç"
          />
        </div>

        <button type="submit">{formData.id ? 'Güncelle' : 'Ekle'}</button>
        {formData.id && <button type="button" onClick={resetForm}>İptal</button>}
      </form>
    </div>
  );
}
