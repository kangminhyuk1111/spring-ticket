# 결제 API 명세서

## 기본 URL
`/`

## 엔드포인트

### 1. 결제 처리

- **URL:** `/payment`
- **메소드:** POST
- **요청 본문:**
  ```json
  {
    "amount": 숫자,
    "cardNumber": 문자열
  }
  ```
- **응답:**
  ```json
  {
    "statusCode": 숫자,
    "data": {
      "message": 문자열,
      "transactionId": 문자열
    }
  }
  ```
- **오류 응답:**
  - 400 Bad Request: 카드 번호 형식이 잘못되었거나 잔액이 부족한 경우
  - 500 Internal Server Error: API 오류 발생 시

### 2. 환불 처리

- **URL:** `/refund`
- **메소드:** POST
- **요청 본문:**
  ```json
  {
    "transactionId": 문자열,
    "cardNumber": 문자열
  }
  ```
- **응답:**
  ```json
  {
    "statusCode": 숫자,
    "data": 문자열
  }
  ```
- **오류 응답:**
  - 400 Bad Request: 카드 번호가 거래와 일치하지 않는 경우
  - 500 Internal Server Error: 예상치 못한 오류 발생 시

## 주의사항

1. 카드 번호는 반드시 "xxxx-xxxx-xxxx-xxxx" 형식이어야 합니다.
2. 결제 처리에는 30ms에서 10000ms 사이의 무작위 지연이 발생합니다.
3. 결제는 무작위로 성공하거나, 잔액 부족으로 실패하거나, API 오류가 발생할 수 있습니다.
4. 환불은 원래 거래에 사용된 카드 번호와 일치하는 경우에만 가능합니다.
