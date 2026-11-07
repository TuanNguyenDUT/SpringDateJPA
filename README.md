# Practice 04 - Student Management System

Ứng dụng REST API quản lý khóa học và sinh viên bằng Java Spring Boot, Spring Data JPA và MySQL.

## 1. Mục tiêu

- Có tối thiểu 2 model/entity: `Course` và `Student`.
- Có quan hệ giữa các bảng: `Course` 1-n `Student`.
- Thực hiện đầy đủ CRUD cho cả Course và Student.
- Tổ chức source code theo mô hình Controller - Service - Repository.

## 2. Công nghệ

- Java 17
- Spring Boot 3.4.5
- Spring Web
- Spring Data JPA / Hibernate
- MySQL 8+
- Maven

## 3. Quan hệ database

- Một `Course` có nhiều `Student`: `@OneToMany`.
- Một `Student` thuộc một `Course`: `@ManyToOne`.
- Khóa ngoại `course_id` nằm trong bảng `students`.

## 4. Cài đặt và chạy

### Yêu cầu

Cài Java 17+, Maven (hoặc dùng Maven Wrapper nếu tự thêm) và MySQL 8+.

### Tạo database (nếu không dùng tùy chọn tự tạo database)

```sql
CREATE DATABASE student_management;
```

### Cấu hình kết nối

Mở `src/main/resources/application.properties`. Mặc định:

- URL: `jdbc:mysql://localhost:3306/student_management`
- Username: `root`
- Password: để trống

Có thể cấu hình bằng biến môi trường `DB_URL`, `DB_USERNAME`, `DB_PASSWORD`.

### Chạy ứng dụng

Tại thư mục có `pom.xml`:

```bash
mvn spring-boot:run
```

Ứng dụng chạy tại `http://localhost:8080`.

## 5. API CRUD

### Course

| Method | Endpoint | Chức năng |
|---|---|---|
| GET | `/api/courses` | Lấy tất cả khóa học |
| GET | `/api/courses/{id}` | Lấy khóa học theo ID |
| POST | `/api/courses` | Thêm khóa học |
| PUT | `/api/courses/{id}` | Cập nhật khóa học |
| DELETE | `/api/courses/{id}` | Xóa khóa học |

Ví dụ POST `/api/courses`:

```json
{
  "courseName": "Java OOP",
  "description": "Object-Oriented Programming with Java"
}
```

### Student

| Method | Endpoint | Chức năng |
|---|---|---|
| GET | `/api/students` | Lấy tất cả sinh viên |
| GET | `/api/students/{id}` | Lấy sinh viên theo ID |
| GET | `/api/students/by-course/{courseId}` | Lấy sinh viên theo khóa học |
| POST | `/api/students` | Thêm sinh viên |
| PUT | `/api/students/{id}` | Cập nhật sinh viên |
| DELETE | `/api/students/{id}` | Xóa sinh viên |

Ví dụ POST `/api/students`:

```json
{
  "name": "Nguyen Van A",
  "email": "a@example.com",
  "age": 20,
  "courseId": 1
}
```

Lưu ý: phải tạo Course trước, sau đó dùng `courseId` của Course khi tạo Student.

## 6. Test nhanh bằng Postman

1. Chạy ứng dụng và mở Postman.
2. Gửi `POST http://localhost:8080/api/courses` để tạo khóa học.
3. Gửi `POST http://localhost:8080/api/students` với `courseId` vừa nhận.
4. Dùng GET để xem dữ liệu.
5. Dùng PUT để cập nhật.
6. Dùng DELETE để xóa.

## 7. Push project lên GitHub

Tạo một repository mới trên GitHub, sau đó mở terminal tại thư mục project và chạy:

```bash
git init
git add .
git commit -m "Complete Practice 04 Student Management CRUD"
git branch -M main
git remote add origin https://github.com/<YOUR_USERNAME>/<YOUR_REPOSITORY>.git
git push -u origin main
```

Thay `<YOUR_USERNAME>` và `<YOUR_REPOSITORY>` bằng thông tin repository GitHub của bạn.

## 8. Cấu trúc thư mục

```text
Practice-04-Student-Management/
├── src/
│   ├── main/
│   │   ├── java/com/example/studentmanagement/
│   │   │   ├── controller/
│   │   │   ├── model/
│   │   │   ├── repository/
│   │   │   ├── service/
│   │   │   └── StudentManagementApplication.java
│   │   └── resources/application.properties
│   └── test/
├── .gitignore
├── pom.xml
└── README.md
```
