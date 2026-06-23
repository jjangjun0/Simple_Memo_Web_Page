# Simple Memo Web Page

간단한 메모장 기능을 제공하는 Spring Boot 기반 프로젝트입니다.
메모를 생성, 조회, 수정, 삭제할 수 있는 REST API를 제공합니다.

---

## 메모장 기능 설계

### 1. 메모 전체 목록 조회하기

* 접속하자마자 메모 전체 목록을 조회합니다.
* `GET` API를 사용하여 메모 목록을 불러옵니다.

### 2. 메모 생성하기

* `POST` API를 사용하여 새로운 메모를 생성합니다.
* 생성된 메모 정보를 반환합니다.

### 3. 메모 변경하기

* `PUT` API를 사용하여 기존 메모 내용을 변경합니다.
* 사용자가 클릭한 메모가 DB에 존재하는지 확인합니다.
* 해당 메모의 내용을 변경합니다.

### 4. 메모 삭제하기

* `DELETE` API를 사용하여 메모를 삭제합니다.
* 사용자가 삭제하려는 메모가 DB에 존재하는지 확인합니다.
* DB에서 해당 메모를 삭제합니다.

---

## API 명세

### 메모장 API

| 기능      | Method   | URL               | Return                  |
| ------- | -------- | ----------------- | ----------------------- |
| 메모 생성하기 | `POST`   | `/api/memos`      | `MemoResponseDto`       |
| 메모 조회하기 | `GET`    | `/api/memos`      | `List<MemoResponseDto>` |
| 메모 변경하기 | `PUT`    | `/api/memos/{id}` | `Long`                  |
| 메모 삭제하기 | `DELETE` | `/api/memos/{id}` | `Long`                  |

---

## 프로젝트 구조

```text
src/main/java/com/example/memo
├─ MemoApplication.java
├─ controller
│  └─ MemoController.java
├─ dto
│  ├─ MemoRequestDto.java
│  └─ MemoResponseDto.java
├─ entity
│  └─ Memo.java
└─ repository
   └─ MemoRepository.java
```

---

## 주요 클래스 설명

### MemoController

클라이언트의 요청을 받는 Controller 클래스입니다.
`GET`, `POST`, `PUT`, `DELETE` API 주소를 정의합니다.

### MemoRequestDto

클라이언트가 서버로 보내는 요청 데이터를 담는 DTO입니다.

```json
{
  "username": "anonymous",
  "contents": "hello memo"
}
```

### MemoResponseDto

서버가 클라이언트에게 반환하는 응답 데이터를 담는 DTO입니다.

```json
{
  "id": 1,
  "username": "anonymous",
  "contents": "This is a test memo.",
  "modifiedAt": "2026-06-16"
}
```

### Memo

서버 내부에서 관리하는 실제 메모 데이터 객체입니다.

주요 필드:

* `id`
* `username`
* `contents`
* `createdAt`
* `modifiedAt`

### MemoRepository

메모를 저장하고 조회하는 역할을 담당합니다.
현재는 서버 내부 저장소 역할을 하며, 이후 DB와 연결될 수 있습니다.
