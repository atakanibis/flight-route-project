# ✈️ Flight Route Finder

Bu proje, kullanıcıların iki farklı lokasyon arasında geçerli uçuş/ulaşım rotalarını bulmalarını sağlayan full-stack bir web uygulamasıdır.

Uygulama, Java (Spring Boot) ile geliştirilmiş bir backend ve React.js (Vite) tabanlı bir frontend arayüzünden oluşur.
Bonus olarak tarih bazlı rota filtreleme ve çok adımlı geçiş kontrolü gibi gelişmiş kurallar da uygulanmıştır.

---

## 🛠️ Kullanılan Teknolojiler

### 🔹 Backend
- Java 17
- Spring Boot 3.x
- Spring Web (REST)
- Spring Data JPA (Hibernate)
- H2 In-Memory Database
- Lombok
- Swagger (OpenAPI)
- Maven

### 🔹 Frontend
- React 18 (Vite ile)
- Axios
- React Router DOM
- React Select
- React DatePicker

---

## 📁 Proje Yapısı

flight-route-project/
├── backend/
│ └── FlightRouteFinder/ # Spring Boot uygulaması
├── frontend/
│ └── flight-route-ui/ # React (Vite) arayüzü
├── README.md
└── .gitignore


---

## 🚀 Kurulum ve Çalıştırma

### 🧩 Backend (Spring Boot)

```bash
cd backend/FlightRouteFinder
mvn clean install
```
Ardından FlightRouteFinderApplication sınıfını IntelliJ IDEA üzerinden çalıştırabilirsiniz.

Swagger Arayüzü:
👉 http://localhost:8080/swagger-ui/index.html


### 🖥️ Frontend (React)

```bash
cd frontend/flight-route-ui
npm install
npm run dev
```

Arayüz:
👉 http://localhost:5173

---

## ✅ ÖZELLİKLER
- Lokasyon CRUD işlemleri (ekle, listele, güncelle, sil)

- Ulaşım bilgileri yönetimi (UBER, BUS, FLIGHT)

- 1 ila 3 adımda valid rota bulma algoritması

- Rotalarda en az 1 uçuş ve geçerli kurallar kontrolü

- Tarih seçimi ile sadece belirli günlerde çalışan rotaların listelenmesi

- Swagger arayüzü üzerinden tüm API'lerin test edilebilmesi

- React arayüzü ile sade, modern kullanıcı deneyimi


## 📌 EK KURALLAR (Business Logic)
- Aşağıdaki kurallara uymayan bağlantılar geçerli bir rota olarak sayılmaz:

- 3'ten fazla taşıma adımı varsa ❌

- Uçuş içermeyen rotalar ❌

- Birden fazla uçuş varsa ❌

- Uçuş öncesi 1'den fazla aktarma varsa ❌

- Uçuş sonrası 1'den fazla aktarma varsa ❌

- Seçilen tarihte çalışmayan ulaşımlar ❌


## 🧪 Bonus Özellikler
- Rota adımları açıklamalı biçimde listelenir:
    "Uber ride from Taksim to Sabiha Gökçen"

- Tarih filtresiyle sadece o gün çalışan rotalar döner

- Dropdown ve çoklu gün seçimi desteklenir


---

# 👨‍💻 Geliştiren

Developer: Atakan İbiş
Tech Stack: Java, Spring Boot, React, Vite

