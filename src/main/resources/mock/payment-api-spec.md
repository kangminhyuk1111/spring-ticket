# 결제 API 명세서

## 엔드포인트

### 1. 결제

- **URL:** `/payment`
- **메소드:** POST
- **요청 본문:**
  ```json
  {
    "amount": 1000.0,
    "cardNumber": "1234-5678-1234-5678"
  }
  ```
  - `amount`: 결제 금액
  - `cardNumber`: 카드 번호(4자리의 숫자가 `-` 으로 구분 된 총 16자의 문자열)
- **응답:**
  ```json
  {
    "statusCode": 200,
    "data": {
      "message": "결제 성공 했습니다.",
      "transactionId": "id1"
    }
  }
  ```
  - `statusCode`: 응답 상태 코드
  - `data.message`: 응답 메시지
  - `data.transactionId`: 결제 ID

- **오류 응답:**
  ```json
  {
    "statusCode": 400,
    "data": "잔액이 부족합니다."
  }
  ```

### 2. 환불 처리

- **URL:** `/refund`
- **메소드:** POST
- **요청 본문:**
  ```json
  {
    "transactionId": "id1",
    "cardNumber": "1234-5678-1234-5678"
  }
  ```
  - `cardNumber`: 결제에 사용 된 카드 번호
  - `transactionId`: 취소 하고자 하는 결제 ID
  
- **응답:**
  ```json
  {
    "statusCode": 200,
    "data": "환불 성공 했습니다."
  }
  ```
  - `statusCode`: 응답 상태 코드
  - `message`: 응답 메시지

- **오류 응답:**
  ```json
  {
    "statusCode": 500,
    "data": "예외 메시지"
  }
  ```
  
## 주의사항

1. 카드 번호는 반드시 "xxxx-xxxx-xxxx-xxxx" 형식이어야 합니다.
2. 환불은 원래 거래에 사용된 카드 번호와 일치하는 경우에만 가능합니다.
