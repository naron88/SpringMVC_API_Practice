# REST API - 사용자, 권한, 채널, 메시지, 파일 관리
사용자 관리, 권한 관리, 채널 관리, 메시지 관리, 메시지 수신 정보 관리 및 바이너리 파일 다운로드 기능을 포함한 웹 API를 구현했습니다.

## 기능 목록

### **사용자 관리** 

#### 📝 1. 사용자 등록
**URL**: POST/api/users

**Request Headers**:
```http
Content-Type: multipart/form-data
```
**Request Body(form-data)**:


| Key | Type | Value |
| --- | --- | --- |
| userCreateRequest | Text (application/json) | {"username": "user1", "email": "test@gmail.com", "phoneNumber": "01011112222", "password": "qwer123!"} |
| profile | File | 이미지 |

**Response (201 Created)**:
```json
{
    "userId": "b30bae31-e357-4f38-945d-d4e3c4cc870c",
    "username": "user1",
    "email": "test@gmail.com",
    "phoneNumber": "01011112222",
    "profileId": "f42c4028-6477-47de-9c7c-26e41cbff436",
    "isOnline": true,
    "createdAt": "2025-02-20T08:33:45.147799200Z",
    "updatedAt": null
}
```
---
#### 📝 2. 사용자 정보 수정
**URL**: PATCH/api/users/{userId}

**Request Headers**:
```http
Content-Type: multipart/form-data
```
**Request Body(form-data)**:


| Key | Type | Value |
| --- | --- | --- |
| userCreateRequest | Text (application/json) | {"newUsername": "updatedUser1", "newEmail": "update@gmail.com", "newPhoneNumber": "01033334444", "newPassword": "asdf456&"} |
| profile | File | 이미지 |

**Response (200 OK)**:
```json
{
    "userId": "b30bae31-e357-4f38-945d-d4e3c4cc870c",
    "username": "updatedUser1",
    "email": "update@gmail.com",
    "phoneNumber": "01033334444",
    "profileId": "f2cc29cf-9884-41db-a5f4-ca2a5789e6da",
    "isOnline": true,
    "createdAt": "2025-02-20T08:33:45.147799200Z",
    "updatedAt": "2025-02-20T08:42:17.215294400Z"
}
```
---
#### 📝 3. 사용자 삭제
**URL**: DELETE/api/users

**Request Headers**:

**Request Body**:


**Response (204 No Content)**:

---
#### 📝 4. 모든 사용자 조회
**URL**: GET/api/users

**Request Headers**:

**Request Body**:

**Response (200 OK)**:
```json
[
    {
        "userId": "81f11520-9fa5-421a-a51b-c76de8cefaa2",
        "username": "user1",
        "email": "test@gmail.com",
        "phoneNumber": "01011112222",
        "profileId": "2dbbd70a-8d7b-4786-a187-bc7f61bfccf0",
        "isOnline": true,
        "createdAt": "2025-02-20T08:43:29.991133Z",
        "updatedAt": null
    }
]
```
---
#### 📝 5. 사용자 온라인 상태로 업데이트
**URL**: PUT/api/users/{userId}

**Request Headers**:
```http
Content-Type: application/json
```
**Request Body(form-data)**:


| Key | Type | Value |
| --- | --- | --- |
| userStatusUpdateRequest| Text| {"newLastActiveAt": "2025-02-14T16:00:00Z"} |


**Response (200 OK)**:
```json
{
    "userStatusId": "dca4cd8b-255f-4e72-bccf-4200186df80c",
    "userId": "81f11520-9fa5-421a-a51b-c76de8cefaa2",
    "lastActiveTime": "2025-02-20T08:43:29.992132200Z",
    "createdAt": "2025-02-20T08:43:29.992132200Z",
    "updatedAt": null
}
```
---
### **권한 관리** (

#### 📝 1. 사용자 로그인
**URL**: POST/api/auth/login

**Request Headers**:
```http
Content-Type: application/json
```
**Request Body**:

```json
{
    "username": "user1",
    "password": "qwer123!"
}
```

**Response (200 OK)**:
```json
{
    "userId": "81f11520-9fa5-421a-a51b-c76de8cefaa2",
    "username": "user1",
    "email": "test@gmail.com",
    "phoneNumber": "01011112222",
    "profileId": "2dbbd70a-8d7b-4786-a187-bc7f61bfccf0",
    "isOnline": true,
    "createdAt": "2025-02-20T08:43:29.991133Z",
    "updatedAt": null
}
```
---
### **채널 관리** (

#### 📝 1. 공개 채널 생성
**URL**: POST/api/channels/public

**Request Headers**:
```http
Content-Type: application/json
```
**Request Body**:

```json
{
    "name": "코드잇",
    "description": "sprint mssion 4"
}
```

**Response (201 Created)**:
```json
{
    "channelId": "cb4fdd91-4fa3-4c76-bbd8-542fd2c99cdd",
    "name": "코드잇",
    "description": "sprint mssion 4",
    "channelType": "PUBLIC",
    "participantIds": [],
    "lastMessageAt": "-1000000000-01-01T00:00:00Z",
    "createdAt": "2025-02-20T08:46:33.779144300Z",
    "updatedAt": null
}
```
---
#### 📝 2. 비공개 채널 생성
**URL**: POST/api/channels/private

**Request Headers**:
```http
Content-Type: application/json
```
**Request Body**:

```json
{
    "participants": [
        "81f11520-9fa5-421a-a51b-c76de8cefaa2"
    ]
}
```

**Response (201 Created)**:
```json
{
    "channelId": "84724fee-ee30-4f9a-8f65-92ba159aa016",
    "name": null,
    "description": null,
    "channelType": "PRIVATE",
    "participantIds": [
        "81f11520-9fa5-421a-a51b-c76de8cefaa2"
    ],
    "lastMessageAt": "-1000000000-01-01T00:00:00Z",
    "createdAt": "2025-02-20T08:50:28.005665900Z",
    "updatedAt": null
}
```
---
#### 📝 3. 공개 채널 정보 수정
**URL**: PUT/api/channels/{channelId}

**Request Headers**:
```http
Content-Type: application/json
```
**Request Body**:

```json
{
    "newName": "updated 코드잇",
    "newDescription": "updated sprint mssion 4"
}
```

**Response (200 OK)**:
```json
{
    "channelId": "cb4fdd91-4fa3-4c76-bbd8-542fd2c99cdd",
    "name": "updated 코드잇",
    "description": "updated sprint mssion 4",
    "channelType": "PUBLIC",
    "participantIds": [],
    "lastMessageAt": "-1000000000-01-01T00:00:00Z",
    "createdAt": "2025-02-20T08:46:33.779144300Z",
    "updatedAt": "2025-02-20T08:48:28.223132500Z"
}
```
---
#### 📝 4. 채널 삭제
**URL**: DELETE/api/channels/{channelId}

**Request Headers**:

**Request Body**:

**Response (204 No Content)**:

---
#### 📝 5. 특정 사용자의 모든 채널 조회
**URL**: GET/api/channels/users/{userId}

**Request Headers**:
**Request Body**:


**Response (200 OK)**:
```json
[
    {
        "channelId": "35508447-21bb-4501-811e-060810f81305",
        "name": "코드잇",
        "description": "sprint mssion 4",
        "channelType": "PUBLIC",
        "participantIds": [],
        "lastMessageAt": "-1000000000-01-01T00:00:00Z",
        "createdAt": "2025-02-20T08:49:16.138559600Z",
        "updatedAt": null
    },
    {
        "channelId": "84724fee-ee30-4f9a-8f65-92ba159aa016",
        "name": null,
        "description": null,
        "channelType": "PRIVATE",
        "participantIds": [
            "81f11520-9fa5-421a-a51b-c76de8cefaa2"
        ],
        "lastMessageAt": "-1000000000-01-01T00:00:00Z",
        "createdAt": "2025-02-20T08:50:28.005665900Z",
        "updatedAt": null
    }
]
```
---

#### 나머지
[Sprint mission 4.postman_collection.json](https://github.com/user-attachments/files/18884477/Sprint.mission.4.postman_collection.json)
