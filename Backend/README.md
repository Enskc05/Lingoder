# Lingoder API

**Lingoder**, modern ve kişiselleştirilmiş bir dil öğrenme platformudur.  
Kullanıcıların kendi kelimelerini ekleyip tekrar edebildiği, yapay zeka destekli hikaye ve görsel oluşturma özellikleri ile dil öğrenme sürecini daha etkili ve eğlenceli hâle getirmeyi amaçlamaktadır.

![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.2.0-green)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue)
![Docker](https://img.shields.io/badge/Docker-✓-informational)
![OpenAI API](https://img.shields.io/badge/OpenAI-API-blueviolet)

---

## 🚀 Projenin Temel Amacı

Geleneksel ezber tabanlı dil öğrenme yaklaşımlarını modern teknolojilerle birleştirerek:
- **Kullanıcının kendi oluşturduğu kelime havuzu** üzerinden öğrenme,
- **Kişiye özel quiz ve tekrar algoritması** (6 doğru cevap prensibi ve zamanlama mantığı),
- **OpenAI GPT** ile otomatik hikaye oluşturma,
- **OpenAI DALL·E** ile kelime bazlı görsel üretme,
- **Öğrenme ilerleme raporları** sunma,
- Mobil ve Web uyumlu **API-first mimari** oluşturma.

Bu sayede öğrenme sürecini:
✅ Kişiselleştirilmiş  
✅ Görsel ve yaratıcı  
✅ Oyunlaştırılmış bir hâle getirmeyi hedeflemektedir.

---

## 🛠️ Kullanılan Teknolojiler

| Teknoloji              | Açıklama                           |
|------------------------|-----------------------------------|
| **Spring Boot**        | Backend REST API                   |
| **MySQL**              | Veritabanı                         |
| **Docker**             | Uygulamanın containerize edilmesi  |
| **OpenAI GPT / DALL·E**| AI tabanlı hikaye ve görsel üretimi |
| **JWT**                | Kimlik doğrulama                   |
| **Kotlin**             | Mobil uygulama tarafında frontend  |
| **Gradle / Maven**     | Proje bağımlılık yönetimi          |

---

## ⚡ Kurulum

### Geliştirme ortamı için:

```bash
# 1. Repoyu klonla
git clone https://github.com/Enskc05/Lingoder.git
cd Lingoder/v1

# 2. Docker servislerini başlat
docker-compose up -d --build
