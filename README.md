# Lingoder API

**Modern dil öğrenme platformu için RESTful API**  
Spring Boot & MySQL ile geliştirilmiş, Docker ile paketlenmiş tam stack çözüm.

![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.2.0-green)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue)
![Docker](https://img.shields.io/badge/Docker-✓-informational)

## 📋 İçindekiler
- [Özellikler](#-özellikler)
- [Kurulum](#-kurulum)
- [API Kullanımı](#-api-kullanımı)
- [Veritabanı](#-veritabanı)
- [Yönetim](#-yönetim)
- [Katkı](#-katkı)
- [Lisans](#-lisans)

## ✨ Özellikler

| Özellik                | Açıklama                                      |
|------------------------|-----------------------------------------------|
| JWT Kimlik Doğrulama   | Secure token-based authentication            |
| Rol Tabanlı Erişim     | `ADMIN`, `USER` rolleri           |



## ⚡ Hızlı Başlangıç

```bash
# 1. Repoyu klonla
git clone https://github.com/Enskc05/Lingoder.git && cd Lingoder/v1

# 2. Docker servislerini başlat
docker-compose up -d --build

# 3. Çalıştığını doğrula
curl http://localhost:8080/

